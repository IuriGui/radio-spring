package com.dominio.springradio1.dao;

import com.dominio.springradio1.model.BlocoComercial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsercaoComercialDao {


    public boolean addInsercaoComercial(BlocoComercial blocoComercial, int contrato_id, int bloco_comercial_id) {
        String sql = "INSERT INTO insercaoComercial (duracao, id_contrato, id_bloco_comercial)\n" +
                "VALUES (?,?,?)";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, blocoComercial.getDuracaoDoBlcoComercial());
            ps.setInt(2, contrato_id);
            ps.setInt(3, bloco_comercial_id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
