package com.vendalancha.control;

import com.vendalancha.dao.BarcoDAO;
import com.vendalancha.dao.GerenteDAO;
import com.vendalancha.dao.LanchaDAO;
import com.vendalancha.dao.VendedorDAO;
import com.vendalancha.util.IconeUtil;
import com.vendalancha.view.TelaLogin;
import com.vendalancha.view.cadastro.TelaCadastroGerente;
import javax.swing.JOptionPane;

public class Sistema {
    public static void main(String args[]){  
        GerenteDAO.criarTabelaSeNaoExistir();
        VendedorDAO.criarTabelaSeNaoExistir();
        BarcoDAO.criarTabelaSeNaoExistir();
        LanchaDAO.criarTabelaSeNaoExistir();
        
        if(GerenteDAO.existeGerente()){
            new TelaLogin().setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null, "Olá, seja bem-vindo! Aparentemente é a primeira vez que este sistema é acessado.\nDesta forma, é necessário que primeiramente você faça o cadastro de um gerente.\nVamos começar!"
                    , "Bem-vindo!", JOptionPane.INFORMATION_MESSAGE, IconeUtil.getIconeInfo());
            new TelaCadastroGerente().setVisible(true);
        }
    }
}
