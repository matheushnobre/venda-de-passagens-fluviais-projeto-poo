package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
