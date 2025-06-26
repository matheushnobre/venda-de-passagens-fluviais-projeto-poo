package com.vendalancha.model;

public class Vendedor extends Usuario{
    private double salario;
    
    public Vendedor(String nomeCompleto, String nomeUsuario, String senha, double salario) {
        super(nomeCompleto, nomeUsuario, senha);
        this.salario = salario;
    }
    
    public Vendedor(String nomeCompleto, String nomeUsuario, double salario){
        super(nomeCompleto, nomeUsuario, "0000");
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
   
}
