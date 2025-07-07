package com.vendalancha.control;

import com.vendalancha.dao.PassageiroDAO;
import com.vendalancha.dao.PassagemDAO;
import com.vendalancha.model.Passageiro;
import com.vendalancha.util.ConversorData;
import com.vendalancha.util.ParInteiros;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.sql.SQLException;

public class PassagemController {
    public static ParInteiros venderPassagemIndividual(String nome, String cpf, String data, int idViagem){
        /*
        códigos de retorno
        1 - sucesso
        2 - entrada incompleta
        3 - data incorreta
        4 - erro inesperado
        */
                
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome, cpf, data)) return new ParInteiros(2, -1);
        if(!ValidacaoEntrada.validarData(data)) return new ParInteiros(3, -1);
        
        Passageiro passageiro = new Passageiro(nome, ConversorData.strParaLocalDateTime(data), cpf);
        
        try{
            int idPassageiro = PassageiroDAO.cadastrarPassageiro(passageiro);
            int idPassagem = PassagemDAO.salvar(idPassageiro, idViagem, "INDIVIDUAL");
            return new ParInteiros(1, idPassagem);
            
        } catch(SQLException e){
            e.printStackTrace();
            return new ParInteiros(4, -1);
        }
        
    }
    
    public static ParInteiros venderPassagemColetiva(int idViagem, String nome1, String cpf1, String data1, boolean d2, String nome2, String cpf2, String data2, boolean d3, String nome3, String cpf3, String data3){
        /*
        códigos de retorno
        1 - sucesso
        2 - entrada incompleta (p1)
        3 - entrada incompleta (geral)
        4 - data incorreta
        5 - erro inesperado
        */
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome1, cpf1, data1)) return new ParInteiros(2, -1);
        
        if(d2 && !ValidacaoEntrada.validarInsercaoObrigatoria(nome2, cpf2, data2)) return new ParInteiros(3, -1);
        if(d3 && !ValidacaoEntrada.validarInsercaoObrigatoria(nome3, cpf3, data3)) return new ParInteiros(3, -1);
        
        if((!ValidacaoEntrada.validarData(data1)) || (d2 && !ValidacaoEntrada.validarData(data2)) || (d3 && !ValidacaoEntrada.validarData(data3))) return new ParInteiros(4, -1);
        
        Passageiro p1, p2=null, p3=null;
        p1 = new Passageiro(nome1, ConversorData.strParaLocalDateTime(data1), cpf1);
        if(d2) p2 = new Passageiro(nome2, ConversorData.strParaLocalDateTime(data2), cpf2);
        if(d3) p3 = new Passageiro(nome3, ConversorData.strParaLocalDateTime(data3), cpf3);
        
        int idPassagem;
        try{
            int idP1 = PassageiroDAO.cadastrarPassageiro(p1);
            if(d2 && d3){
                System.out.println("todos");
                int idP2 = PassageiroDAO.cadastrarPassageiro(p2);
                int idP3 = PassageiroDAO.cadastrarPassageiro(p3);
                idPassagem = PassagemDAO.salvar(idP1, idP2, idP3, idViagem);
            } else if(d2){
                System.out.println("somente p1 e p2");
                int idP2 = PassageiroDAO.cadastrarPassageiro(p2);
                idPassagem = PassagemDAO.salvar(idP1, idP2, idViagem);
            } else if(d3){
                System.out.println("somente p1 e p3");
                int idP2 = PassageiroDAO.cadastrarPassageiro(p3);
                idPassagem = PassagemDAO.salvar(idP1, idP2, idViagem);
            } else{
                System.out.println("somente p1");
                System.out.println("idviagem= "+idViagem);
                idPassagem = PassagemDAO.salvar(idP1, idViagem, "COLETIVA");
            }
        } catch(SQLException e){
            e.printStackTrace();
            return new ParInteiros(5, -1);
        }
        
        return new ParInteiros(1, idPassagem);
    }
}
