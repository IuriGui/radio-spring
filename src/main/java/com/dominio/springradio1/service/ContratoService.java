package com.dominio.springradio1.service;



import com.dominio.springradio1.dao.ContratoDao;
import com.dominio.springradio1.model.Cliente;
import com.dominio.springradio1.model.Contrato;
import com.dominio.springradio1.model.Vendedor;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class ContratoService {

    private static final VendedorService v = new VendedorService();
    private static final ClienteService c = new ClienteService();
    private static final ContratoDao vd = new ContratoDao();

    public List<Vendedor> listarVendedores() {
        return v.listarVendedores();
    }

    public List<Cliente> listarClientes(){
        return c.listar();
    }

    public boolean criarContrato(Contrato c){
        return vd.createContrato(c);
    }


    public BigDecimal somaDosContratos(){
        return vd.somaDosContratos();
    }

    public BigDecimal somaDosContratos(Month mes){
        return vd.somaDosContratos(mes);
    }




    public int countContratos(){
        return vd.countContratos();
    }

    public List<Contrato> listarContratos(){
        return vd.listarContratos();
    }


}
