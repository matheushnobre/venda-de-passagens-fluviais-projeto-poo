package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Lancha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Comparator;

public class LanchaDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS lancha";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS lancha (" +
                     "nome_embarcacao VARCHAR(255) PRIMARY KEY NOT NULL, " +
                     "nPoltronas INTEGER NOT NULL)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void salvar(Lancha b) throws SQLException{
        String sql = "INSERT INTO lancha (nome_embarcacao, nPoltronas) "
                + "VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getNome());
            stmt.setInt(2, b.getnPoltronas());
            stmt.executeUpdate();
        } 
    }
    
    public static Lancha buscarLancha(String n) throws SQLException{
        String sql = "SELECT * FROM lancha WHERE nome_embarcacao = (?)";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, n);
            ResultSet consulta = stmt.executeQuery();
            
            if(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nPoltronas = consulta.getInt("nPoltronas");
                return new Lancha(nome, nPoltronas);
            }
        }
        
        return null;
    }
    
    public static boolean existe(String nome) throws SQLException{
        String sql = "SELECT * FROM lancha WHERE nome_embarcacao = (?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            
            return (resultado.next());
        }
    }
    
    public static ArrayList<Lancha> carregarEmbarcacoes(){
        ArrayList<Lancha> lista = new ArrayList<>();
        String sql = "SELECT * FROM lancha";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet consulta = stmt.executeQuery();
            
            while(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nPoltronas = consulta.getInt("nPoltronas");
                lista.add(new Lancha(nome, nPoltronas));
            }
                        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        lista.sort(Comparator.comparing(Lancha::getNome, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }
    
    public static ArrayList<Lancha> carregarEmbarcacoesPorNome(String n){
        System.out.println("N = "+n);
        ArrayList<Lancha> lista = new ArrayList<>();
        String sql = "SELECT * FROM lancha WHERE nome_embarcacao LIKE (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, n+"%");
            ResultSet consulta = stmt.executeQuery();
            
            while(consulta.next()){
                String nome = consulta.getString("nome_embarcacao");
                int nPoltronas = consulta.getInt("nPoltronas");
                lista.add(new Lancha(nome, nPoltronas));
            }
                        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        lista.sort(Comparator.comparing(Lancha::getNome, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }
       
    public static void deletarPorNome(String nomeBarco) throws SQLException{
        String sql = "DELETE FROM lancha WHERE nome_embarcacao = (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeBarco);
            stmt.execute();
        } 
    }
       
    public static void updateNPoltronas(String usuario, String valor){
        update(usuario, "nPoltronas", valor);
    }
    
    
    public static void update(String usuario, String campo, String valor){
        String sql = "UPDATE lancha SET " + campo + " = (?) WHERE nome_embarcacao = (?)";
        try(Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, valor);
            stmt.setString(2, usuario);
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static int getNPoltronas(String nome) throws SQLException{
        String sql = "SELECT nPoltronas FROM lancha WHERE nome_embarcacao = ?";
        try(Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()) 
               return resultado.getInt(1);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
