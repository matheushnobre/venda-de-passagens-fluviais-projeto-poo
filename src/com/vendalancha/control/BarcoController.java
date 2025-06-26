package com.vendalancha.control;

import com.vendalancha.dao.BarcoDAO;
import com.vendalancha.model.Barco;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.sql.SQLException;

public class BarcoController{
    public static int cadastrarBarco(String nome, String str_nAndares, String str_nRedes, String str_nCamarotes){
        /*
        Códigos de retorno
        1 - barco cadastrado com sucesso
        2 - campos obrigatórios não inseridos
        3 - campos int não podem ser convertidos ou são negativos
        4 - já existe embarcação com o nome desejado
        5 - erro inesperado
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome, str_nAndares, str_nRedes, str_nCamarotes)) return 2;
        if(!ValidacaoEntrada.isPossivelConverterInteiro(str_nAndares, str_nRedes, str_nCamarotes)) return 3;
        
        int nAndares = Integer.parseInt(str_nAndares);
        int nRedes = Integer.parseInt(str_nRedes);
        int nCamarotes = Integer.parseInt(str_nCamarotes);
        
        if(!ValidacaoEntrada.isNotNegative(nRedes, nCamarotes) || !ValidacaoEntrada.isPositive(nAndares)) return 3;
        
        nome = "Barco " + nome;
        Barco barco = new Barco(nome, nRedes, nCamarotes, nAndares);
        try{
            if(BarcoDAO.existeBarco(nome)) return 4;
            BarcoDAO.salvar(barco);
        } catch(SQLException e){
            e.printStackTrace();
            return 5;
        }
        
        return 1;
    }
    
    public static int editarBarco(String nome, String str_nAndares, String str_nRedes, String str_nCamarotes){
        /*
        Códigos de retorno
        1 - sucesso
        2 - campos obrigatórios não inseridos
        3 - campos int não podem ser convertidos ou são negativos
        4 - erro inesperado
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(str_nAndares, str_nRedes, str_nCamarotes)) return 2;
        if(!ValidacaoEntrada.isPossivelConverterInteiro(str_nAndares, str_nRedes, str_nCamarotes)) return 3;
        
        int nRedes = Integer.parseInt(str_nRedes);
        int nCamarotes = Integer.parseInt(str_nCamarotes);
        int nAndares = Integer.parseInt(str_nAndares);
        
        if(!ValidacaoEntrada.isNotNegative(nRedes, nCamarotes) || !ValidacaoEntrada.isPositive(nAndares)) return 3;
        
        try {
            BarcoDAO.updateNAndares(nome, str_nAndares);
            BarcoDAO.updateNRedes(nome, str_nRedes);
            BarcoDAO.updateNCamarotes(nome, str_nCamarotes);
        } catch(Exception e) {
            e.printStackTrace();
            return 4;
        }
        
        return 1;
    }
    
    public static boolean deletar(String nome){
        try{
            BarcoDAO.deletarPorNome(nome);
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
