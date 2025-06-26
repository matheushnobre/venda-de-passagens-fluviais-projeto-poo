package com.vendalancha.util.validacao;

public class ValidacaoSenha {
    
    public static boolean validarSenha(String senha){
        return (senha.length() >= 6);        
    }
}
