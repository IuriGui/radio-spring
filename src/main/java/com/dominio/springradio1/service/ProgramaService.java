package com.dominio.springradio1.service;


import com.dominio.springradio1.dao.BlocoComercialDao;
import com.dominio.springradio1.dao.ProgramaDao;
import com.dominio.springradio1.model.BlocoComercial;
import com.dominio.springradio1.model.Programa;
import com.dominio.springradio1.model.Vendedor;

import java.sql.SQLException;
import java.util.List;

public class ProgramaService {

    private static final ProgramaDao programaDao = new ProgramaDao();
    private static final BlocoComercialDao blocoComercialDao = new BlocoComercialDao();

    public boolean addPrograma(Programa programa) {
        return programaDao.addPrograma(programa);
    }


    public List<Programa> getAllProgramas() throws SQLException, ClassNotFoundException {
        return programaDao.listarProgramas();
    }

    public Programa getPrograma(int id) throws SQLException, ClassNotFoundException {
        Programa p = programaDao.getProgramaById(id);
        if (p != null) {
            p.setBlocos(blocoComercialDao.getBlocoComercials(id));
            System.out.println("O programa n eh nulo");
        }
        return p;
    }

//    public List<BlocoComercial> getAllBlocoComercialById(int id) throws SQLException, ClassNotFoundException {
//        return blocoComercialDao.getBlocoComercials(id);
//    }

}
