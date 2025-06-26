package com.vendalancha.dao;

import com.vendalancha.db.Conexao;
import com.vendalancha.model.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class VendedorDAO {
    public static void deletarTabela(){
        String sql = "DROP TABLE IF EXISTS vendedor";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void criarTabelaSeNaoExistir(){
        String sql = "CREATE TABLE IF NOT EXISTS vendedor("
                + "nome_completo VARCHAR(255) NOT NULL, "
                + "nome_usuario VARCHAR(255) PRIMARY KEY NOT NULL,"
                + "senha VARCHAR(255) NOT NULL, "
                + "salario DECIMAL NOT NULL)";
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void salvar(Vendedor v) throws SQLException{
        String sql = "INSERT INTO vendedor (nome_completo, nome_usuario, senha, salario) VALUES(?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getNomeCompleto());
            stmt.setString(2, v.getNomeUsuario());
            stmt.setString(3, v.getSenha());
            stmt.setString(4, Double.toString(v.getSalario()));
            stmt.executeUpdate();
            System.out.println("ok");
        } 
    }
    
    public static Vendedor buscarVendedor(String nomeUsuario){
        String sql = "SELECT * FROM vendedor WHERE vendedor.nome_usuario = (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeUsuario);
            ResultSet consulta = stmt.executeQuery();
            
            if(consulta.next()){
                String nome = consulta.getString("nome_completo");
                String user = consulta.getString("nome_usuario");
                String senha = consulta.getString("senha");
                double salario = Double.parseDouble(consulta.getString("salario"));
                return new Vendedor(nome, user, senha, salario);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static ArrayList<Vendedor> carregarVendedores(){
        ArrayList<Vendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet consulta = stmt.executeQuery();
            
            while(consulta.next()){
                String nome = consulta.getString("nome_completo");
                String user = consulta.getString("nome_usuario");
                String senha = consulta.getString("senha");
                double salario = Double.parseDouble(consulta.getString("salario"));
                lista.add(new Vendedor(nome, user, senha, salario));
            }
                        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        lista.sort(Comparator.comparing(Vendedor::getNomeCompleto, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }

    
    public static void deletarPorUsuario(String usuario) throws SQLException{
        String sql = "DELETE FROM vendedor WHERE nome_usuario = (?)";
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.executeUpdate();
        }
    }
    
    public static void updateSalario(String usuario, String valor) throws SQLException{
        update(usuario, "salario", valor);
    }
    
    public static void update(String usuario, String campo, String valor) throws SQLException{
        String sql = "UPDATE vendedor SET " + campo + " = (?) WHERE nome_usuario = (?)";
        try(Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, valor);
            stmt.setString(2, usuario);
            stmt.executeUpdate();
        }
    }
}
