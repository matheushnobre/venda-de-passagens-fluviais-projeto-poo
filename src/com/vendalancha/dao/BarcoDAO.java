package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Barco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Comparator;

public class BarcoDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS barco";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS barco (" +
                     "nome_embarcacao VARCHAR(255) PRIMARY KEY NOT NULL, " +
                     "nRedes INTEGER NOT NULL, " +
                     "nCamarotes INTEGER NOT NULL, " +
                     "nAndares INTEGER NOT NULL)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void salvar(Barco b) throws SQLException{
        String sql = "INSERT INTO barco (nome_embarcacao, nRedes, nCamarotes, nAndares) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getNome());
            stmt.setInt(2, b.getnRedes());
            stmt.setInt(3, b.getnCamarotes());
            stmt.setInt(4, b.getnAndares());
            stmt.executeUpdate();
        } 
    }
    
    public static Barco buscarBarco(String n) throws SQLException{
        String sql = "SELECT * FROM barco WHERE nome_embarcacao = (?)";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, n);
            ResultSet consulta = stmt.executeQuery();
            
            if(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nRedes = consulta.getInt("nRedes");
                int nCamarotes = consulta.getInt("nCamarotes");
                int nAndares = consulta.getInt("nAndares");
                return new Barco(nome, nRedes, nCamarotes, nAndares);
            }
        }
        
        return null;
    }
    
    public static boolean existeBarco(String nome) throws SQLException{
        String sql = "SELECT * FROM barco WHERE nome_embarcacao = (?)";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome);
            ResultSet consulta = stmt.executeQuery();
            
            return (consulta.next());
        }
    }
    
    public static ArrayList<Barco> carregarEmbarcacoes(){
        ArrayList<Barco> lista = new ArrayList<>();
        String sql = "SELECT * FROM barco";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet consulta = stmt.executeQuery();
            
            while(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nRedes = consulta.getInt("nRedes");
                int nCamarotes = consulta.getInt("nCamarotes");
                int nAndares = consulta.getInt("nAndares");
                lista.add(new Barco(nome, nRedes, nCamarotes, nAndares));
            }
                        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        lista.sort(Comparator.comparing(Barco::getNome, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }
    
    public static ArrayList<Barco> carregarEmbarcacoesPorNome(String n){
        ArrayList<Barco> lista = new ArrayList<>();
        String sql = "SELECT * FROM barco WHERE nome_embarcacao LIKE ?";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, n + "%");
            ResultSet consulta = stmt.executeQuery();
            
            while(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nRedes = consulta.getInt("nRedes");
                int nCamarotes = consulta.getInt("nCamarotes");
                int nAndares = consulta.getInt("nAndares");
                lista.add(new Barco(nome, nRedes, nCamarotes, nAndares));
            }
                        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        lista.sort(Comparator.comparing(Barco::getNome, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }
       
    public static void deletarPorNome(String nomeBarco) throws SQLException{
        String sql = "DELETE FROM barco WHERE nome_embarcacao = (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeBarco);
            stmt.execute();
        }
    }
       
    public static void updateNRedes(String usuario, String valor){
        update(usuario, "nRedes", valor);
    }
    
    public static void updateNCamarotes(String usuario, String valor){
        update(usuario, "nCamarotes", valor);
    }
    
    public static void updateNAndares(String usuario, String valor){
        update(usuario, "nAndares", valor);
    }
    
    public static void update(String usuario, String campo, String valor){
        String sql = "UPDATE barco SET " + campo + " = (?) WHERE nome_embarcacao = (?)";
        try(Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, valor);
            stmt.setString(2, usuario);
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
