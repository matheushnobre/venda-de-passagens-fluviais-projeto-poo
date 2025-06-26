package com.vendalancha.model;

public class Embarcacao {
    private String nome;
    
    public Embarcacao(){
        
    }
    
    public Embarcacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
