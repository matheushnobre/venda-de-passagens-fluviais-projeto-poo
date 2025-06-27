package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Barco;
import com.vendalancha.model.RotaViagem;
import com.vendalancha.util.ConversorData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            stmt.setString(3, ConversorData.converterParaStringSQL(r.getHorarioPartida()));
            stmt.setInt(4, r.getDuracaoMinutos());
            stmt.setDouble(5, r.getPrecoAcomodacaoIndividual());
            stmt.setDouble(6, r.getPrecoAcomodacaoColetiva());
            stmt.setString(7, r.getEmbarcacao().getNome());
            stmt.execute();
        } 
    }
}
