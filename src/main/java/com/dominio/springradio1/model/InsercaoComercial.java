package com.dominio.springradio1.model;

import java.util.List;

public class InsercaoComercial {

    int duracaoEmSegundos;

    List<Contrato> historicoDeContratos;


    //classes

    private Contrato contrato;


    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }



    public InsercaoComercial() {
    }

    public int getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(int duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }


    public List<Contrato> getHistoricoDeContratos() {
        return historicoDeContratos;
    }

    public void setHistoricoDeContratos(List<Contrato> historicoDeContratos) {
        this.historicoDeContratos = historicoDeContratos;
    }
}
