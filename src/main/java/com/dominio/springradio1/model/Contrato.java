package com.dominio.springradio1.model;

import java.math.BigDecimal;
import java.util.Date;

public class Contrato {

    //Atributos soltos, mas refatoração exige alterações em muitos lugares

    private int id;
    private java.sql.Date dataInicial;
    private java.sql.Date dataFinal;
    private String descricao;
    private BigDecimal valor;
    private int id_vendedor;
    private int id_cliente;
    private String nomeCliente;
    private String nomeVendedor;
    private boolean ativo;


    //classes

    private Cliente cliente;
    private Contrato contrato;

    public Cliente getCliente() {
        return cliente;
    }


    public java.sql.Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(java.sql.Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public java.sql.Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(java.sql.Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return valor;
    }


    public Contrato(Date dataInicio, Date dataFim, String descricao, BigDecimal preco, int id_vendedor, int id_cliente) {
        this.dataInicial = (java.sql.Date) dataInicio;
        this.dataFinal = (java.sql.Date) dataFim;
        this.descricao = descricao;

        this.valor = preco;
        this.id_vendedor = id_vendedor;
        this.id_cliente = id_cliente;
    }

    public Contrato(java.sql.Date dataInicio, java.sql.Date dataFim, String descricao, BigDecimal valor, String nomeCliente, String nomeVendedor, boolean ativo) {
        this.dataInicial = dataInicio;
        this.dataFinal = dataFim;
        this.descricao = descricao;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.nomeVendedor = nomeVendedor;
        this.ativo = ativo;
    }



    public Contrato() {
    }

    public java.sql.Date getDataInicio() {
        return dataInicial;
    }

    public void setDataInicio(java.sql.Date dataInicio) {
        this.dataInicial = dataInicio;
    }

    public java.sql.Date getDataFim() {
        return dataFinal;
    }

    public void setDataFim(java.sql.Date dataFim) {
        this.dataFinal = dataFim;
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
