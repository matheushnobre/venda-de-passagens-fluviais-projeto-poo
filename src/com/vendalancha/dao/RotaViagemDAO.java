package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Barco;
import com.vendalancha.model.Embarcacao;
import com.vendalancha.model.Lancha;
import com.vendalancha.model.RotaViagem;
import com.vendalancha.util.ConversorData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class RotaViagemDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS RotaViagem";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir(){
        String sql = "CREATE TABLE IF NOT EXISTS RotaViagem ("
                + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "cidade_origem VARCHAR(100) NOT NULL,"
                + "cidade_destino VARCHAR(100) NOT NULL,"
                + "horario_partida DATETIME NOT NULL,"
                + "duracaoMinutos INTEGER NOT NULL,"
                + "barcoVinculado VARCHAR(255)," // gambiarra que encontrei
                + "lanchaVinculada VARCHAR(255),"
                + "precoAcomodacaoIndividual DECIMAL(10, 2) NOT NULL,"
                + "precoAcomodacaoColetiva DECIMAL(10, 2) NOT NULL,"
                + "FOREIGN KEY (barcoVinculado) REFERENCES barco(nome_embarcacao),"
                + "FOREIGN KEY (lanchaVinculada) REFERENCES lancha(nome_embarcacao)"
                + ");";
       
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void salvar(RotaViagem r) throws SQLException{
        String sqlBase = "INSERT INTO RotaViagem (cidade_origem, cidade_destino, horario_partida, duracaoMinutos, "
                + "precoAcomodacaoIndividual, precoAcomodacaoColetiva, ";
        
        // verificando se é barco ou lancha
        if(r.getEmbarcacao() instanceof Barco)
            sqlBase += "barcoVinculado)";
        else
            sqlBase += "lanchaVinculada)";
        
        String sql = sqlBase + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getCidadeOrigem());
            stmt.setString(2, r.getCidadeDestino());
            stmt.setString(3, ConversorData.localDateTimeParaStrSQL(r.getHorarioPartida()));
            stmt.setInt(4, r.getDuracaoMinutos());
            stmt.setDouble(5, r.getPrecoAcomodacaoIndividual());
            stmt.setDouble(6, r.getPrecoAcomodacaoColetiva());
            stmt.setString(7, r.getEmbarcacao().getNome());
            stmt.execute();
        } 
    }
    
    public static ArrayList<RotaViagem> carregarRotas(String origem, String destino, String str_data) throws SQLException{
        ArrayList<RotaViagem> rotas = new ArrayList<>();
        String sql = "SELECT * FROM RotaViagem WHERE cidade_origem = ? AND cidade_destino = ? AND horario_partida >= ? AND horario_partida <= ? ORDER BY horario_partida ASC";
        System.out.println("Rotas");
        try(Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            stmt.setString(3, str_data + " 00:00:00");
            stmt.setString(4, str_data + " 23:59:59");
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                int id = resultado.getInt("id");
                String cidade_origem = resultado.getString("cidade_origem");
                String cidade_destino = resultado.getString("cidade_destino");
                LocalDateTime horarioPartida = ConversorData.strDateTimeParaLocalDateTime(resultado.getString("horario_partida"));
                int duracaoMinutos = resultado.getInt("duracaoMinutos");
                Barco barcoVinculado = BarcoDAO.buscarBarco(resultado.getString("barcoVinculado"));
                Lancha lanchaVinculada = LanchaDAO.buscarLancha(resultado.getString("lanchaVinculada"));
                double precoIndividual = resultado.getDouble("precoAcomodacaoIndividual");
                double precoColetiva = resultado.getDouble("precoAcomodacaoColetiva");
                
                Embarcacao embarcacao;
                if(barcoVinculado == null) embarcacao = lanchaVinculada;
                else embarcacao = barcoVinculado;
                
                RotaViagem rota = new RotaViagem(id, cidade_origem, cidade_destino, horarioPartida, duracaoMinutos, embarcacao, precoIndividual, precoColetiva);
                rotas.add(rota);
                System.out.println(rota);
            }
        }
        return rotas;
    }
    
    public static int verificarDisponibilidadeIndividual(int idRota) throws SQLException{
        int retorno = 0;
 
        String barcoVinculado = verificarBarcoVinculado(idRota);
        String lanchaVinculada = verificarLanchaVinculada(idRota);
        
        if(barcoVinculado != null){
            retorno = BarcoDAO.getNRedes(barcoVinculado);
        } else{
            retorno = LanchaDAO.getNPoltronas(lanchaVinculada);
        }
           
        String sql = "SELECT COUNT(*) FROM PASSAGEM WHERE rota_viagem = ? AND categoria = 'INDIVIDUAL'";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idRota);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()) 
                retorno -= resultado.getInt(1);
        }
        return retorno;
    }
    
    public static int verificarDisponibilidadeColetiva(int idRota) throws SQLException{
        int retorno = 0;
 
        String barcoVinculado = verificarBarcoVinculado(idRota);
        String lanchaVinculada = verificarLanchaVinculada(idRota);
        
        if(barcoVinculado != null){
            retorno = BarcoDAO.getNCamarotes(barcoVinculado);
        } else{
            return 0;
        }
           
        String sql = "SELECT COUNT(*) FROM PASSAGEM WHERE rota_viagem = ? AND categoria = 'COLETIVA'";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idRota);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()) 
                retorno -= resultado.getInt(1);
        }
        return retorno;
    }
    
    public static String verificarBarcoVinculado(int idRota) throws SQLException{
        String retorno = "";
        String sql = "SELECT barcovinculado FROM RotaViagem WHERE id = ?";
        try(Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idRota);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next())
                retorno = resultado.getString(1);
        }
        return retorno;
    }
    
    public static String verificarLanchaVinculada(int idRota) throws SQLException{
        String retorno = "";
        String sql = "SELECT lanchaVinculada FROM RotaViagem WHERE id = ?";
        try(Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idRota);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next())
                retorno = resultado.getString(1);
        }
        return retorno;
    }
    
    public static String getDataViagem(int idRota) throws SQLException{
        String retorno = "";
        String sql = "SELECT horario_partida FROM RotaViagem WHERE id = ?";
        try(Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idRota);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next())
                retorno = resultado.getString(1);
        }
        return retorno;
    }
}
