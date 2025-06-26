package com.vendalancha.control;

import com.vendalancha.dao.GerenteDAO;
import com.vendalancha.dao.VendedorDAO;
import com.vendalancha.model.Usuario;
import com.vendalancha.util.validacao.ValidacaoEntrada;

public class LoginController {
    
    public static int autenticar(String login, String senha){
        /*
        Codigos de retorno
        1 - Credenciais corretas: usuário logado
        2 - Campos obrigatórios não inseridos
        3 - Nome de usuário incorreto
        4 - Senha incorreta
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(login, senha)) return 2;
       
        Usuario usuario = GerenteDAO.buscarGerente(login);
        if(usuario == null) usuario = VendedorDAO.buscarVendedor(login);
        if(usuario == null) return 3; 
        if(usuario.getSenha().equals(senha)) return 1; 
        
        return 4; 
    }
    
    // método apenas para retornar o usuário logado, será utilizado somente após o de autenticação
    public static Usuario logar(String login){
        Usuario usuario = GerenteDAO.buscarGerente(login);
        if(usuario == null) usuario = VendedorDAO.buscarVendedor(login);
        return usuario;
    }
}
