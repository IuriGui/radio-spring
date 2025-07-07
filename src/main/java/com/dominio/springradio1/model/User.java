package com.dominio.springradio1.model;

import java.math.BigDecimal;

public class User  {
    private int id;
    private String email;
    private String senha;
    private BigDecimal salario;
    private String nome;

    public User(){
    }


    public User(String email, String password) {
        this.email = email;
        this.senha = password;
    }

    public User(int id, BigDecimal salario, String nome) {
        this.id = id;
        this.salario = salario;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
