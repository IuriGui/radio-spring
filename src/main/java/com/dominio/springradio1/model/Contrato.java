package com.dominio.springradio1.model;

import java.math.BigDecimal;
import java.util.Date;

public class Contrato {

    private int id;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String descricao;
    private BigDecimal valor;
    private int id_vendedor;
    private int id_cliente;
    private String nomeCliente;
    private String nomeVendedor;
    private boolean ativo;

    public BigDecimal getPreco() {
        return valor;
    }


    public Contrato(Date dataInicio, Date dataFim, String descricao, BigDecimal preco, int id_vendedor, int id_cliente) {
        this.dataInicio = (java.sql.Date) dataInicio;
        this.dataFim = (java.sql.Date) dataFim;
        this.descricao = descricao;
        this.valor = preco;
        this.id_vendedor = id_vendedor;
        this.id_cliente = id_cliente;
    }

    public Contrato(java.sql.Date dataInicio, java.sql.Date dataFim, String descricao, BigDecimal valor, String nomeCliente, String nomeVendedor, boolean ativo) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.nomeVendedor = nomeVendedor;
        this.ativo = ativo;
    }



    public Contrato() {
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(java.sql.Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(java.sql.Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
