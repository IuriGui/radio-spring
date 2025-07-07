package com.dominio.springradio1.service;


import com.dominio.springradio1.dao.UserDao;

public class UserService {

    private static final UserDao userDao = new UserDao();

    public boolean deletarUsuarop(int id){
        return userDao.deleteUser(id);
    }




}
