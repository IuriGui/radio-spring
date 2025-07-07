package com.dominio.springradio1.dao;




import com.dominio.springradio1.model.Contrato;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDao {


    public boolean createContrato(Contrato contrato) {
        String sql = "INSERT INTO contrato (data_inicial, data_final, descricao, valor, id_cliente, id_vendedor, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConectarBancoDeDados.conectarPostgres()) {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, contrato.getDataInicio());
            ps.setDate(2, contrato.getDataFim());
            ps.setString(3, contrato.getDescricao());
            ps.setBigDecimal(4, contrato.getPreco());
            ps.setInt(5, contrato.getId_cliente());
            ps.setInt(6, contrato.getId_vendedor());
            ps.setBoolean(7, true);


            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public BigDecimal somaDosContratos() {
        BigDecimal soma = new BigDecimal(0);
        String sql = "SELECT SUM(valor) FROM contrato WHERE ativo = true";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal valor = rs.getBigDecimal(1);
                if (valor != null) {
                    soma = soma.add(valor);
                }
            }

            return soma;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public int countContratos() {
        int count = 0;
        String sql = "SELECT count(*) FROM contrato WHERE ativo = true";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            return count;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Contrato> listarContratos(){
        String sql = "SELECT " +
                "teste.nome AS nome_cliente, " +
                "v.nome AS nome_vendedor, " +
                "c.data_inicial, " +
                "c.data_final, " +
                "c.descricao, " +
                "c.valor, " +
                "c.ativo " + // <- espaço no final
                "FROM contrato AS c " +  // <- espaço no final
                "JOIN vendedor AS v ON v.id = c.id_vendedor " +  // <- espaço no final
                "JOIN cliente AS teste ON teste.id = c.id_cliente";


        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Contrato> lista = new ArrayList<Contrato>();

            while(rs.next()){
                Contrato c = new Contrato(
                        rs.getDate("data_inicial"), rs.getDate("data_final"),
                        rs.getString("descricao"), rs.getBigDecimal("valor"),
                        rs.getString("nome_cliente"), rs.getString("nome_vendedor"),
                        rs.getBoolean("ativo")
                );

                if(lista.add(c)){
                    System.out.printf("Adicionado com sucesso!");
                }
            }

            if(lista.isEmpty()){
                return null;
            }
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
