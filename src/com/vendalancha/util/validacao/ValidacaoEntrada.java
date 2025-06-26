package com.vendalancha.util.validacao;

public class ValidacaoEntrada {
    
    public static boolean validarInsercaoObrigatoria(String... valores){
        for(String s : valores) 
            if(s == null || s.trim().isEmpty()) return false;
        
        return true;
    }
    
    public static boolean isPossivelConverterDouble(String... valores){
        try{
            double d;
            for(String valor : valores)
                d = Double.parseDouble(valor);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public static boolean isPossivelConverterInteiro(String... valores){
        try{
            int d;
            for(String valor : valores)
                d = Integer.parseInt(valor);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public static boolean isNotNegative(double... valores){
        for(double d : valores){
            if(d < 0) return false;
        }
        return true;
    }
    
    public static boolean isPositive(double... valores){
        for(double d : valores){
            if(d <= 0) return false;
        }
        return true;
    }
    
}
