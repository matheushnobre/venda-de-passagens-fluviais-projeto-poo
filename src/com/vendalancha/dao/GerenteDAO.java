package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GerenteDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS gerente";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS gerente (" +
                     "nome_completo VARCHAR(255) NOT NULL, " +
                     "nome_usuario VARCHAR(255) PRIMARY KEY NOT NULL, " +
                     "senha VARCHAR(255) NOT NULL)";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void salvar(Gerente g) {
        String sql = "INSERT INTO gerente (nome_completo, nome_usuario, senha) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, g.getNomeCompleto());
            stmt.setString(2, g.getNomeUsuario());
            stmt.setString(3, g.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existeGerente() {
        String sql = "SELECT COUNT(*) FROM gerente";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {
            return resultado.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static Gerente buscarGerente(String nomeUsuario){
        String sql = "SELECT * FROM GERENTE WHERE GERENTE.nome_usuario = (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeUsuario);
            ResultSet consulta = stmt.executeQuery();
            
            if(consulta.next()){
                String nome = consulta.getString("nome_completo");
                String user = consulta.getString("nome_usuario");
                String senha = consulta.getString("senha");
                return new Gerente(nome, user, senha);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
