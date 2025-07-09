package com.dominio.springradio1.dao;

import com.dominio.springradio1.model.Cliente;
import com.dominio.springradio1.model.Contato;
import com.dominio.springradio1.model.Programa;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramaDao {

    public boolean addPrograma(Programa programa) {
        String sql = "INSERT INTO programa (nome, horario_exibicao, duracao_programa)" +
                " VALUES (?, ?, ?);";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, programa.getNome());
            ps.setTime(2, Time.valueOf(programa.getHorarioExibicao()));
            ps.setInt(3, programa.getDuracaoTotalDoPrograma());



            return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Programa> listarProgramas() throws SQLException, ClassNotFoundException {
        List<Programa> programas = new ArrayList<>();

        String sql = "SELECT id, nome, horario_exibicao, duracao_programa FROM programa";

        try (
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Programa p = new Programa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setHorarioExibicao(rs.getTime("horario_exibicao").toLocalTime()); // só horário
                p.setDuracaoTotalDoPrograma(rs.getInt("duracao_programa"));

                programas.add(p);
            }
        }

        return programas;
    }

    public Programa getProgramaById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM programa WHERE id = ?";
        try (Connection conn = ConectarBancoDeDados.conectarPostgres()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Programa p = new Programa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDuracaoTotalDoPrograma(rs.getInt("duracao_programa"));
                p.setHorarioExibicao(rs.getTime("horario_exibicao").toLocalTime());
                return p;
            }

            return null;
        }
    }



}
