package com.vendalancha.control;

import com.vendalancha.dao.GerenteDAO;
import com.vendalancha.model.Gerente;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import com.vendalancha.util.validacao.ValidacaoSenha;

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
}
