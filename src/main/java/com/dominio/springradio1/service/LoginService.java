package com.dominio.springradio1.service;

import com.dominio.springradio1.dao.UserDao;
import com.dominio.springradio1.dao.VendedorDao;
import com.dominio.springradio1.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {

    private UserDao userDao = new UserDao();

    public User autenticar(String email, String senha) {
        User usuario = userDao.buscarUsuarioPorEmail(email);
        usuario.setNome(new VendedorDao().AcharNomeVendedor(usuario));

        String senhaBanco = usuario.getSenha();
        if(senhaBanco != null){
            if(BCrypt.checkpw(senha, senhaBanco)){

                new VendedorDao().AcharNomeVendedor(usuario);

                return usuario;
            }
        }
        return null;
    }

    public String retornarNomeDoUsuario(String email){
        return new VendedorDao().AcharNomeVendedor(userDao.getUserIdByEmail(email));
    }

}
