package com.dominio.springradio1.model;

import java.math.BigDecimal;
import java.util.List;

public class Vendedor extends User{

    private int id_vendedor;
    private String telefone;
    private BigDecimal salarioComComissao;
    private static float comissaoPorcentual = 0.2f;
    private List<Cliente> clientes;
    private double comissao;
    private BigDecimal totalVendido;
    private int idUser;

    public BigDecimal getSalarioComComissao() {
        return salarioComComissao;
    }

    public void setSalarioComComissao(BigDecimal salarioComComissao) {
        this.salarioComComissao = salarioComComissao;
    }


    @Override
    public String toString() {
        return "Vendedor {" +
                "id_vendedor=" + id_vendedor +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + telefone + '\'' +
                ", salarioBase=" + getSalario() +
                ", salarioComComissao=" + salarioComComissao +
                ", comissao=" + comissao +
                ", totalVendido=" + totalVendido +
                ", idUser=" + idUser +
                '}';
    }


    public Vendedor(String nome, String telefone, int idUser) {
        super();
        this.setNome(nome);
        this.telefone = telefone;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public BigDecimal getTotalVendido() {
        return totalVendido;
    }

    public Vendedor(int idUser, BigDecimal salario, String nome, int id_vendedor, BigDecimal salarioComComissao, double comissao, BigDecimal totalVendido, String telefone) {
        super(idUser, salario, nome);
        this.id_vendedor = id_vendedor;
        this.salarioComComissao = salarioComComissao;
        this.comissao = comissao;
        this.totalVendido = totalVendido;
        this.telefone = telefone;
    }


    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Vendedor(){

    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

}
