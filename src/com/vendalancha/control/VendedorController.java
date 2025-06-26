package com.vendalancha.control;

import com.vendalancha.dao.GerenteDAO;
import com.vendalancha.dao.VendedorDAO;
import com.vendalancha.model.Vendedor;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.sql.SQLException;

public class VendedorController {
    
    public static int cadastrarVendedor(String nome, String user, String str_salario){
        /*
        Códigos de retorno
        1 - sucesso: vendedor cadsatrado
        2 - campos obrigatórios não inseridos
        3 - salário inválido
        4 - nome de usuário já utilizado
        5 - erro inesperado
        */
        
        if(!ValidacaoEntrada.validarInsercaoObrigatoria(nome, user, str_salario)) return 2;
        if(!ValidacaoEntrada.isPossivelConverterDouble(str_salario)) return 3;
        
        double salario = Double.parseDouble(str_salario);
        if(!ValidacaoEntrada.isPositive(salario)) return 3;
        
        Vendedor vendedor = new Vendedor(nome, user, salario);
        if(VendedorDAO.buscarVendedor(user) != null || GerenteDAO.buscarGerente(user) != null){
            return 4;
        }
        
        try{
            VendedorDAO.salvar(vendedor);
        } catch(SQLException e){
            e.printStackTrace();
            return 5;
        }
        
        return 1;
    }
    
    public static boolean deletarVendedor(String usuario){
        /*
        Códigos de retorno
        True se o vendedor foi deletado com sucesso
        False se um erro inesperado aconteceu
        */
        
        try{
            VendedorDAO.deletarPorUsuario(usuario); 
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static int alterarSalario(String usuario, String str_salarioAntigo, String str_novoSalario){
        /*
        Códigos de retorno
        1 - salário atualizado com sucesso
        2 - salário digitado não pode ser convertido para double
        3 - salário digitado é inferior ao salário antigo
        4 - erro inesperado
        */
        
        if(!ValidacaoEntrada.isPossivelConverterDouble(str_novoSalario)) return 2;
        double salarioAntigo = Double.parseDouble(str_salarioAntigo);
        double novoSalario = Double.parseDouble(str_novoSalario);
        
        if(novoSalario <= salarioAntigo) return 3;
        try{
            VendedorDAO.updateSalario(usuario, Double.toString(novoSalario));
        } catch(SQLException e){
            e.printStackTrace();
            return 4;
        }
        
        return 1;
    }
    
}