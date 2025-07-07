package com.dominio.springradio1.dao;



import com.dominio.springradio1.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {


    public List<Contato> listar() {
        List<Contato> lista = new ArrayList<Contato>();
        String sql = "select * from contato";
        try(Connection con = ConectarBancoDeDados.conectarPostgres()){

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setEmail(rs.getString("email"));
                contato.setNomeContato(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                lista.add(contato);
            }

            return lista;

        } catch (SQLException  | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public boolean create(Contato contato, int id_cliente, Connection con) throws SQLException {

        System.out.println("Contato - create");
        System.out.println("Nome do contato: " + contato.getNomeContato());

        String sql = "insert into contato (nome, email, telefone, endereco, id_cliente) values (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, contato.getNomeContato());
        ps.setString(2, contato.getEmail());
        ps.setString(3, contato.getTelefone());
        ps.setString(4, contato.getEndereco());
        ps.setInt(5, id_cliente);


        int rows  = ps.executeUpdate();


        return rows > 0;
    }




}
