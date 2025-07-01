package com.vendalancha.control;

import com.vendalancha.dao.PassageiroDAO;
import com.vendalancha.dao.PassagemDAO;
import com.vendalancha.model.Passageiro;
import com.vendalancha.util.ConversorData;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.sql.SQLException;

public class PassagemController {
    public static int venderPassagemIndividual(String nome, String cpf, String data, int idViagem){
        /*
        códigos de retorno
        1 - sucesso
        2 - entrada incompleta
        3 - data incorreta
        4 - erro inesperado
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome, cpf, data)) return 2;
        if(!ValidacaoEntrada.validarData(data)) return 3;
        
        Passageiro passageiro = new Passageiro(nome, ConversorData.strParaLocalDateTime(data), cpf);
        
        try{
            int idPassageiro = PassageiroDAO.cadastrarPassageiro(passageiro);
            PassagemDAO.salvar(idPassageiro, idViagem);
            
        } catch(SQLException e){
            e.printStackTrace();
            return 4;
        }
        return 1;
    }
}
