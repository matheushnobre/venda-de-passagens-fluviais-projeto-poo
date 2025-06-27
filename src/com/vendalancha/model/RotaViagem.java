package com.vendalancha.model;

import java.time.LocalDateTime;

public class RotaViagem {
    private int id;
    private String cidadeOrigem; 
    private String cidadeDestino;
    private LocalDateTime horarioPartida;
    private int duracaoMinutos;
    private Embarcacao embarcacao;
    private double precoAcomodacaoIndividual;
    private double precoAcomodacaoColetiva;

    // construtor sem o id
    public RotaViagem(String cidadeOrigem, String cidadeDestino, LocalDateTime horarioPartida, int duracaoMinutos, Embarcacao embarcacao, double precoAcomodacaoIndividual, double precoAcomodacaoColetiva) {
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.horarioPartida = horarioPartida;
        this.duracaoMinutos = duracaoMinutos;
        this.embarcacao = embarcacao;
        this.precoAcomodacaoIndividual = precoAcomodacaoIndividual;
        this.precoAcomodacaoColetiva = precoAcomodacaoColetiva;
    }
    
    // construtor com o id
    public RotaViagem(int id, String cidadeOrigem, String cidadeDestino, LocalDateTime horarioPartida, int duracaoMinutos, Embarcacao embarcacao, double precoAcomodacaoIndividual, double precoAcomodacaoColetiva) {
        this.id = id;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.horarioPartida = horarioPartida;
        this.duracaoMinutos = duracaoMinutos;
        this.embarcacao = embarcacao;
        this.precoAcomodacaoIndividual = precoAcomodacaoIndividual;
        this.precoAcomodacaoColetiva = precoAcomodacaoColetiva;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(LocalDateTime horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
    }

    public double getPrecoAcomodacaoIndividual() {
        return precoAcomodacaoIndividual;
    }

    public void setPrecoAcomodacaoIndividual(double precoAcomodacaoIndividual) {
        this.precoAcomodacaoIndividual = precoAcomodacaoIndividual;
    }

    public double getPrecoAcomodacaoColetiva() {
        return precoAcomodacaoColetiva;
    }

    public void setPrecoAcomodacaoColetiva(double precoAcomodacaoColetiva) {
        this.precoAcomodacaoColetiva = precoAcomodacaoColetiva;
    }
}
