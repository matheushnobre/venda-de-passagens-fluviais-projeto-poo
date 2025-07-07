package com.vendalancha.control;

import com.vendalancha.dao.GerenteDAO;
import com.vendalancha.model.Gerente;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import com.vendalancha.util.validacao.ValidacaoSenha;
import java.sql.SQLException;

public class GerenteController {
    
    public static int cadastrarGerente(String nomeCompleto, String nomeUsuario, String senha, String confirmacaoSenha){
        /* 
        codigos de retorno
        1 - sucesso
        2 - campos não preenchidos
        3 - senha inválida (< 6 caracteres)
        4 - senhas diferentes
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nomeCompleto, nomeUsuario, senha, confirmacaoSenha)) return 2;
        if(!ValidacaoSenha.validarSenha(senha)) return 3;
        if(!senha.equals(confirmacaoSenha)) return 4;
        
        Gerente gerente = new Gerente(nomeCompleto, nomeUsuario, senha);
        GerenteDAO.salvar(gerente);
 
        return 1;
    }
    
    public static int alterarSenha(String usuario, String senhaAtual, String senhaNova){
        /*
        Códigos de retorno
        1 - sucesso
        2 - senha atual incorreta
        3 - senha nova inválida
        4 - erro inesperado
        */
        
        Gerente vendedor = GerenteDAO.buscarGerente(usuario);
        if(!vendedor.getSenha().equals(senhaAtual)) return 2;
        if(!ValidacaoSenha.validarSenha(senhaNova)) return 3;
        
        try{
            GerenteDAO.updateSenha(usuario, senhaNova);
        } catch(SQLException e){
            e.printStackTrace();
            return 4;
        }
        return 1;
    }
}
