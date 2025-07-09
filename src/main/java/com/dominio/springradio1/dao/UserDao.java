package com.dominio.springradio1.dao;

import com.dominio.springradio1.model.User;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {



    public int createUser(User user) {
        String sqlUser = "INSERT INTO usuario (email, senha) VALUES (?,?)";
        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
        ){


            stmtUser.setString(1, user.getEmail());
            stmtUser.setString(2, BCrypt.hashpw(user.getSenha(), BCrypt.gensalt()));
            stmtUser.executeUpdate();

            ResultSet rs = stmtUser.getGeneratedKeys();
            if(rs.next()) {
                int newid = rs.getInt(1);
                if(newid > 0){
                    return newid;
                } else{
                    return -2;
                                            }
            } else{
                throw new SQLException("Não foi possível criar usuário");
            }


        } catch (RuntimeException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public int createUser(User user, Boolean b) {
        String sqlUser = "INSERT INTO usuario (email, senha, ativo) VALUES (?,?, ?)";
        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
        ){


            stmtUser.setString(1, user.getEmail());
            stmtUser.setString(2, BCrypt.hashpw(user.getSenha(), BCrypt.gensalt()));
            stmtUser.setBoolean(3, b);

            stmtUser.executeUpdate();

            ResultSet rs = stmtUser.getGeneratedKeys();
            if(rs.next()) {
                int newid = rs.getInt(1);
                if(newid > 0){
                    return newid;
                } else{
                    return -2;
                }
            } else{
                throw new SQLException("Não foi possível criar usuário");
            }


        } catch (RuntimeException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteUser(int id) {
        String sqlUser = "UPDATE  usuario SET ativo = false WHERE id = ?";
        try(
                Connection connection = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtDelUser = connection.prepareStatement(sqlUser)
        ) {

            stmtDelUser.setInt(1, id);
            return stmtDelUser.executeUpdate() > 0;

        } catch (RuntimeException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean updateUser(int id, String email, String senha) {
        String sqlUser = "UPDATE usuario SET email = ?, senha = ? WHERE id = ?";

        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtUpdateUser = conn.prepareStatement(sqlUser)
                ){

            stmtUpdateUser.setString(1, email);
            stmtUpdateUser.setString(2, BCrypt.hashpw(senha, BCrypt.gensalt()));
            stmtUpdateUser.setInt(3, id);

            return stmtUpdateUser.executeUpdate() > 0;


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar usuário" + e.getMessage());
        }


    }

    public int getUserIdByVendedor(int ids) {
        String sqlUser = "SELECT * FROM usuario WHERE id = ?";
        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtUser = conn.prepareStatement(sqlUser)
        ){
            stmtUser.setInt(1, ids);
            ResultSet rs = stmtUser.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            } else{
                return -1;
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao buscar ID por email: " + e.getMessage(), e);
        }
    }

    public User getUserIdByEmail(String email) {
        String sqlUser = "SELECT * FROM usuario WHERE email = ?";
        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmtUser = conn.prepareStatement(sqlUser)
                ){
                stmtUser.setString(1, email);

                User u = new User();

                ResultSet rs = stmtUser.executeQuery();
                if(rs.next()) {
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    return u;
                } else{
                    return null;
                }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao buscar ID por email: " + e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() {
        String sqlUser = "SELECT * FROM usuario";
        try(
                Connection connection = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement ps = connection.prepareStatement(sqlUser);
                ){

            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));

                users.add(user);

            }

            return users;

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }

    public User buscarUsuarioPorEmail(String email){

        String sql = "select * from usuario where email = ?";

        try(Connection conn = ConectarBancoDeDados.conectarPostgres()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            User u = new User();

            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                return u;
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public int getIdByEmail(String email){

        String sql = "select senha from usuario where email = ?";

        try(Connection conn = ConectarBancoDeDados.conectarPostgres()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean ativarUsuario(int id){
        String sql = "UPDATE usuario SET ativo = true WHERE id = ?";
        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println("id do usuario: " + id);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }


    }

}
