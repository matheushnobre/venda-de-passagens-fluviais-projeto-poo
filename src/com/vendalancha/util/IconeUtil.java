package com.vendalancha.util;

import javax.swing.ImageIcon;

public class IconeUtil {
    public static ImageIcon getIconeSucesso(){
        return carregarIcone("/com/vendalancha/imagens/ok64.png");
    }
    
    public static ImageIcon getIconeSenha(){
        return carregarIcone("/com/vendalancha/imagens/password64.png");
    }
    
    public static ImageIcon getIconeInfo(){
        return carregarIcone("/com/vendalancha/imagens/info64.png");
    }
    
    public static ImageIcon getIconeErro(){
        return carregarIcone("/com/vendalancha/imagens/erro64.png");
    }
    
    private static ImageIcon carregarIcone(String caminho){
        return new ImageIcon(IconeUtil.class.getResource(caminho));
    }
}
