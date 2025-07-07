package com.vendalancha.model;

public abstract class Usuario {
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
        
    public Usuario(String nomeCompleto, String nomeUsuario, String senha){
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
