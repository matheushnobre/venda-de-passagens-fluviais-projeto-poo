package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Passageiro;
import com.vendalancha.util.ConversorData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PassageiroDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS passageiro";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS passageiro (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                     "nome VARCHAR(255) NOT NULL, " +
                     "cpf VARCHAR(11) NOT NULL, " +
                     "data_nascimento DATE NOT NULL" +
                     ")";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int cadastrarPassageiro(Passageiro p) throws SQLException{
        String sql = "INSERT INTO PASSAGEIRO(nome, cpf, data_nascimento) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, ConversorData.localDateTimeParaStrSQL(p.getDataNascimento()));
            stmt.executeUpdate();
            
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }
        } 
        
        return -1;
    }
}
