package com.dominio.springradio1.service;


import com.dominio.springradio1.dao.UserDao;
import com.dominio.springradio1.model.User;

public class CadastroUserService {

    private static final UserDao u = new UserDao();


    public boolean criarUser(String email, String senha){

        User user = new User(email, senha);

        return u.createUser(user) > 0;

    }
    public int criarUser(String email, String senha, Boolean ativo){
        User user = new User(email, senha);
        return u.createUser(user, ativo);

    }

    public boolean ativarUsuario(int id)
    {
        System.out.println("Ativando usuario...");
        System.out.println("id do usuario: " + id);
        return u.ativarUsuario(id);
    }


}
