package com.dominio.springradio1.service;

import com.dominio.springradio1.dao.BlocoComercialDao;
import com.dominio.springradio1.model.BlocoComercial;

public class BlocoComercialService {


    private static final BlocoComercialDao blocoComercialDao = new BlocoComercialDao();

    public boolean addBlocoComercial(BlocoComercial blocoComercial, int id) {
        return blocoComercialDao.addBlocoComercial(blocoComercial, id);
    }



}


