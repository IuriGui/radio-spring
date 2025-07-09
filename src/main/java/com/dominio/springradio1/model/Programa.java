package com.dominio.springradio1.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Programa {


    private int id;
    private String nome;
    @DateTimeFormat(pattern = "HH:mm")
    private java.time.LocalTime horarioExibicao;
    int duracaoTotalDoPrograma;

    private List<BlocoComercial> blocos;

    public List<BlocoComercial> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<BlocoComercial> blocos) {
        this.blocos = blocos;
    }


    public Programa(String nome, LocalTime horarioExibicao, int duracaoTotalDoPrograma) {
        this.nome = nome;
        this.horarioExibicao = horarioExibicao;
        this.duracaoTotalDoPrograma = duracaoTotalDoPrograma;
    }

    public Programa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioExibicao() {
        return horarioExibicao;
    }

    public void setHorarioExibicao(LocalTime horarioExibicao) {
        this.horarioExibicao = horarioExibicao;
    }

    public int getDuracaoTotalDoPrograma() {
        return duracaoTotalDoPrograma;
    }

    public void setDuracaoTotalDoPrograma(int duracaoTotalDoPrograma) {
        this.duracaoTotalDoPrograma = duracaoTotalDoPrograma;
    }
}
