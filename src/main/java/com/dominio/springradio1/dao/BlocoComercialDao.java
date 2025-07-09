package com.dominio.springradio1.dao;

import com.dominio.springradio1.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlocoComercialDao {


    public BlocoComercialDao() {
    }

    public boolean addBlocoComercial(BlocoComercial blocoComercial, int programa_id) {


        String sql = "INSERT INTO blocoComercial (duracao, id_programa) " +
                "VALUES (?, ?)";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, blocoComercial.getDuracaoDoBlcoComercial());
            ps.setInt(2, programa_id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public List<BlocoComercial> getBlocoComercials(int id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT 
            blocoComercial.id AS bloco_id,
            blocoComercial.duracao AS duracao_bloco,
            insercaoComercial.duracao AS duracao_insercao,
            contrato.descricao AS contrato_descricao,
            contrato.data_inicial,
            contrato.data_final,
            cliente.nome AS nome_cliente
        FROM blocoComercial
        LEFT JOIN insercaoComercial 
            ON blocoComercial.id = insercaoComercial.id_bloco_comercial
        LEFT JOIN contrato 
            ON insercaoComercial.id_contrato = contrato.id
        LEFT JOIN cliente 
            ON contrato.id_cliente = cliente.id
        WHERE blocoComercial.id_programa = ?
        ORDER BY blocoComercial.id
        """;

        Map<Integer, BlocoComercial> blocosMap = new LinkedHashMap<>();

        try (
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int blocoId = rs.getInt("bloco_id");
                    BlocoComercial bloco = blocosMap.get(blocoId);

                    if (bloco == null) {
                        bloco = new BlocoComercial();
                        bloco.setId(blocoId);
                        bloco.setDuracaoDoBlcoComercial(rs.getInt("duracao_bloco"));
                        bloco.setInsercoesComerciais(new ArrayList<>());
                        blocosMap.put(blocoId, bloco);
                    }

                    if (rs.getString("contrato_descricao") != null) {
                        InsercaoComercial insercao = new InsercaoComercial();
                        insercao.setDuracaoEmSegundos(rs.getInt("duracao_insercao"));

                        Contrato contrato = new Contrato();
                        contrato.setDescricao(rs.getString("contrato_descricao"));
                        contrato.setDataInicio(rs.getDate("data_inicial"));
                        contrato.setDataFim(rs.getDate("data_final"));

                        Cliente cliente = new Cliente();
                        cliente.setNomeCliente(rs.getString("nome_cliente"));
                        contrato.setCliente(cliente);

                        insercao.setContrato(contrato);
                        bloco.getInsercoesComerciais().add(insercao);
                    }
                }
            }
        }

        return new ArrayList<>(blocosMap.values());
    }


}
