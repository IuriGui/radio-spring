package com.dominio.springradio1.model;

import java.util.List;

public class BlocoComercial {


    private int id;
    private int idPrograma;
    private List<InsercaoComercial> insercoesComerciais;
    private int duracaoDoBlcoComercial;


    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InsercaoComercial> getInsercoesComerciais() {
        return insercoesComerciais;
    }

    public void setInsercoesComerciais(List<InsercaoComercial> insercoesComerciais) {
        this.insercoesComerciais = insercoesComerciais;
    }

    public int getDuracaoDoBlcoComercial() {
        return duracaoDoBlcoComercial;
    }

    public void setDuracaoDoBlcoComercial(int duracaoDoBlcoComercial) {
        this.duracaoDoBlcoComercial = duracaoDoBlcoComercial;
    }

    public BlocoComercial() {}

    public int tempoDisponivelParaVenda(){

        return 1;
    }

}
