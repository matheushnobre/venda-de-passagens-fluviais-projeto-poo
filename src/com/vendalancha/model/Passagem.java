package com.vendalancha.model;

public class Passagem {
    private Passageiro passageiroN1;
    private Passageiro passageiroN2;
    private Passageiro passageiroN3;
    private TipoPassagem tipoPassagem;
    private RotaViagem rota;

    public Passagem(Passageiro passegeiroN1, TipoPassagem tipoPassagem, RotaViagem rota) {
        this.passageiroN1 = passegeiroN1;
        this.tipoPassagem = tipoPassagem;
        this.rota = rota;
    }

    public Passagem(Passageiro passegeiroN1, Passageiro passageiroN2, RotaViagem rota) {
        this.passageiroN1 = passegeiroN1;
        this.passageiroN2 = passageiroN2;
        this.tipoPassagem = TipoPassagem.COLETIVA;
        this.rota = rota;
    }

    public Passagem(Passageiro passegeiroN1, Passageiro passageiroN2, Passageiro passageiroN3, RotaViagem rota) {
        this.passageiroN1 = passegeiroN1;
        this.passageiroN2 = passageiroN2;
        this.passageiroN3 = passageiroN3;
        this.tipoPassagem = TipoPassagem.COLETIVA;
        this.rota = rota;
    }

    public Passageiro getPassegeiroN1() {
        return passageiroN1;
    }

    public void setPassegeiroN1(Passageiro passegeiroN1) {
        this.passageiroN1 = passegeiroN1;
    }

    public Passageiro getPassageiroN2() {
        return passageiroN2;
    }

    public void setPassageiroN2(Passageiro passageiroN2) {
        this.passageiroN2 = passageiroN2;
    }

    public Passageiro getPassageiroN3() {
        return passageiroN3;
    }

    public void setPassageiroN3(Passageiro passageiroN3) {
        this.passageiroN3 = passageiroN3;
    }

    public RotaViagem getRota() {
        return rota;
    }

    public void setRota(RotaViagem rota) {
        this.rota = rota;
    }

    public TipoPassagem getTipoPassagem() {
        return tipoPassagem;
    }

    public void setTipoPassagem(TipoPassagem tipoPassagem) {
        this.tipoPassagem = tipoPassagem;
    }
    
}
