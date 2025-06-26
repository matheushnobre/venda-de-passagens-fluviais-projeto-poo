package com.vendalancha.model;


public class Lancha extends Embarcacao{
    private int nPoltronas;
    
    public Lancha(){
        
    }
    
    public Lancha(String nome, int nPoltronas) {
        super(nome);
        this.nPoltronas = nPoltronas;
    }

    public int getnPoltronas() {
        return nPoltronas;
    }

    public void setnPoltronas(int nPoltronas) {
        this.nPoltronas = nPoltronas;
    }
    
}
