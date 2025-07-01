package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
