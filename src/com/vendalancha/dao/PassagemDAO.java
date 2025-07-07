package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PassagemDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS passagem";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS passagem (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "passageiro1 VARCHAR(11) NOT NULL, " +
                     "passageiro2 VARCHAR(11)," +
                     "passageiro3 VARCHAR(11),"+
                     "categoria VARCHAR(15) NOT NULL,"+
                     "rota_viagem INTEGER NOT NULL," +
                     "FOREIGN KEY (passageiro1) REFERENCES Passageiro(cpf)," +
                     "FOREIGN KEY (passageiro2) REFERENCES Passageiro(cpf)," +
                     "FOREIGN KEY (passageiro3) REFERENCES Passageiro(cpf)" +
                     ")";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int salvar(int idPassageiro, int idViagem, String categoria) throws SQLException{
        String sql = "INSERT INTO passagem(passageiro1, categoria, rota_viagem) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPassageiro);
            stmt.setString(2, categoria);
            stmt.setInt(3, idViagem);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                } else {
                    throw new SQLException("Falha ao obter o ID da passagem inserida.");
                }
            }
        }
    }
    
    public static int salvar(int idP1, int idP2, int idViagem) throws SQLException{
        String sql = "INSERT INTO passagem(passageiro1, passageiro2, categoria, rota_viagem) VALUES (?, ?, 'COLETIVA', ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idP1);
            stmt.setInt(2, idP2);
            stmt.setInt(3, idViagem);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                } else {
                    throw new SQLException("Falha ao obter o ID da passagem inserida.");
                }
            }
        }
    }
    
     public static int salvar(int idP1, int idP2, int idP3, int idViagem) throws SQLException{
        String sql = "INSERT INTO passagem(passageiro1, passageiro2, passageiro3, categoria, rota_viagem) VALUES (?, ?, ?, 'COLETIVA', ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idP1);
            stmt.setInt(2, idP2);
            stmt.setInt(3, idP3);
            stmt.setInt(4, idViagem);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                } else {
                    throw new SQLException("Falha ao obter o ID da passagem inserida.");
                }
            }
        }
    }
     
    
}
