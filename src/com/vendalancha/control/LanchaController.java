package com.vendalancha.control;

import com.vendalancha.dao.LanchaDAO;
import com.vendalancha.model.Lancha;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.sql.SQLException;

public class LanchaController{
    public static int cadastrarLancha(String nome, String str_nPoltronas){
        /*
        Códigos de retorno
        1 - lancha cadastrada com sucesso
        2 - campo obrigatório não inserido
        3 - numero de poltronas invalido
        4 - lancha com mesmo nome já cadastrada
        5 - erro inesperado
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome, str_nPoltronas)) return 2;
        if(!ValidacaoEntrada.isPossivelConverterInteiro(str_nPoltronas)) return 3;
        int nPoltronas = Integer.parseInt(str_nPoltronas);
        if(!ValidacaoEntrada.isPositive(nPoltronas)) return 3;
        
        nome = "Lancha " + nome;
        Lancha lancha = new Lancha(nome, nPoltronas);
        try{
            if(LanchaDAO.existe(nome)) return 4;
            LanchaDAO.salvar(lancha);
        } catch(SQLException e){
            e.printStackTrace();
            return 5;
        }
        return 1;
    }
    
    public static boolean deletar(String nome){
        /*
        Código de retorno
        True se deletar
        False se um erro inesperado acontecer
        */
        
        try{
            LanchaDAO.deletarPorNome(nome);
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static int editarLancha(String nome, String str_nPoltronas){
        /*
        Códigos de retorno
        1 - editado com sucesso
        2 - str_nPoltronas inválido
        3 - erro inesperado
        */
        
        if(!ValidacaoEntrada.isPossivelConverterInteiro(str_nPoltronas)) return 2;
        int nPoltronas = Integer.parseInt(str_nPoltronas);
        if(!ValidacaoEntrada.isPositive(nPoltronas)) return 2;
                
        try {
            LanchaDAO.updateNPoltronas(nome, str_nPoltronas);
        } catch(Exception e) {
            e.printStackTrace();
            return 3;
        }
        
        return 1;
    }
}
