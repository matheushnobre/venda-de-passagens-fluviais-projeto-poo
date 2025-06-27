package com.vendalancha.control;

import com.vendalancha.dao.BarcoDAO;
import com.vendalancha.dao.LanchaDAO;
import com.vendalancha.dao.RotaViagemDAO;
import com.vendalancha.model.Embarcacao;
import com.vendalancha.model.RotaViagem;
import com.vendalancha.util.ConversorData;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.SQLException;

public class RotaViagemController {
    public static int cadastrarRota(String cidadeOrigem, String cidadeDestino, String nome_embarcacao, 
            String str_data, String str_horaPartida, String str_horas, String str_minutos, String str_precoIndividual, String str_precoColetiva){
        
        /*
        Códigos de retorno
        1 - sucesso
        2 - embarcação não selecionada
        3 - demais campos não selecionados
        4 - data não formatada
        5 - hora de partida inválida
        6 - horas e/ou minutos não podem ser convertidos para inteiros não negativos
        7 - preços não podem ser convertidos para double não negativo
        8 - partida inferior a data/horário atual
        9 - erro inesperado
        */
        
        // validações
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome_embarcacao)) return 2;
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(cidadeOrigem, cidadeDestino, str_data, str_horas, str_minutos, str_precoIndividual, str_precoColetiva)) return 3;
        if(!ValidacaoEntrada.validarData(str_data)) return 4;
        if(!ValidacaoEntrada.validarHorario(str_horaPartida)) return 5;
        if(!ValidacaoEntrada.isPossivelConverterInteiro(str_horas, str_minutos)) return 6;
        
        int horas = Integer.parseInt(str_horas);
        int minutos = Integer.parseInt(str_minutos);
        
        if(!ValidacaoEntrada.isNotNegative(horas, minutos) || (minutos >= 60)) return 6;
        if(!ValidacaoEntrada.isPossivelConverterDouble(str_precoIndividual, str_precoColetiva)) return 7;
        
        double precoIndividual = Double.parseDouble(str_precoIndividual);
        double precoColetiva = Double.parseDouble(str_precoColetiva);
        
        if(!ValidacaoEntrada.isNotNegative(precoIndividual, precoColetiva)) return 7;
        
        // horario de partida nao deve ser inferior ao horario atual
        LocalDateTime horarioPartida = ConversorData.converterParaLocalDateTime(str_data, str_horaPartida);
        if(horarioPartida.isBefore(LocalDateTime.now(ZoneId.of("America/Manaus")))) return 8;
        
        // criando rota
        int duracaoMinutos = horas * 60 + minutos;
        Embarcacao embarcacao;
        
        try{
            if(nome_embarcacao.charAt(0) == 'B') embarcacao = BarcoDAO.buscarBarco(nome_embarcacao);
            else embarcacao = LanchaDAO.buscarLancha(nome_embarcacao);
            RotaViagem rota = new RotaViagem(cidadeOrigem, cidadeDestino, horarioPartida, duracaoMinutos, embarcacao, precoIndividual, precoColetiva);
            RotaViagemDAO.salvar(rota);
        }
        catch(SQLException e){
            e.printStackTrace();
            return 9;
        }
                
        return 1;
    }
}
