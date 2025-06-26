package com.vendalancha.model;

public class Barco extends Embarcacao{
    private int nRedes, nCamarotes, nAndares;;
    
    public Barco(){
        
    }
    
    public Barco(String nome, int nRedes, int nCamarotes, int nAndares) {
        super(nome);
        this.nRedes = nRedes;
        this.nCamarotes = nCamarotes;
        this.nAndares = nAndares;
    }

    public int getnRedes() {
        return nRedes;
    }

    public void setnRedes(int nRedes) {
        this.nRedes = nRedes;
    }

    public int getnCamarotes() {
        return nCamarotes;
    }

    public void setnCamarotes(int nCamarotes) {
        this.nCamarotes = nCamarotes;
    }

    public int getnAndares() {
        return nAndares;
    }

    public void setnAndares(int nAndares) {
        this.nAndares = nAndares;
    } 
}
