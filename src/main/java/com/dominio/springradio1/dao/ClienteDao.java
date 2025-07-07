package com.dominio.springradio1.dao;



import com.dominio.springradio1.model.Cliente;
import com.dominio.springradio1.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDao {


    public boolean createCliente(Cliente cliente) {


        String sql = "insert into cliente (nome, cpf_cnpj) values(?,  ?)";
        try(
                Connection connection = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {

            preparedStatement.setString(1, cliente.getNomeCliente());
            preparedStatement.setString(2, cliente.getCpf());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e){
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public int createCliente(Cliente cliente, Connection conn) throws SQLException, ClassNotFoundException {
        if (conn != null){

            String sql = "insert into cliente (nome, cpf_cnpj) values(?,  ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNomeCliente());
            preparedStatement.setString(2, cliente.getCpf());

            int affectedRows = preparedStatement.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("A criação do cliente falhou, nenhum registro afetado.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("Registro criado com sucesso!" + generatedKeys.getInt("id"));
                    return generatedKeys.getInt("id");
                }
            }

            return cliente.getId();

        }
        return -1;
    }

    public boolean updateCliente(Cliente cliente) {

        String sql = "UPDATE cliente SET nome = ?, cpf_cnpj = ? WHERE id = ?";

        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){

                ps.setString(1, cliente.getNomeCliente());
                ps.setString(2, cliente.getCpf());
                ps.setInt(3, cliente.getId());
                return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e){
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public boolean deleteCliente(int id) {

        String sql = "UPDATE  cliente SET ativo = false WHERE id = ?";

        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e){
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cliente> getAllClientes() {

        String sql = "select * from cliente where ativo = true";
        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

            ResultSet rs = stmt.executeQuery();
            ArrayList<Cliente> clientes = new ArrayList<>();

            while(rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf_cnpj"));
                clientes.add(c);
            }

            System.out.println("Clientes encontrados: " + clientes.size());

            return clientes;


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao listarClientes: " + e.getMessage(), e);
        }



    }

    public List<Contato> listarContatosCliente(int id) {
        List<Contato> lista = new ArrayList<Contato>();
        String sql = "select contato.nome, email, telefone FROM contato join cliente on contato.id_cliente = ? where cliente.ativo = true";
        try(Connection con = ConectarBancoDeDados.conectarPostgres()){

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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


    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome ILIKE ?"; // PostgreSQL (ILIKE = case insensitive)

        try (Connection conn = ConectarBancoDeDados.conectarPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNomeCliente(rs.getString("nome"));
                lista.add(c);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Cliente> getAllClientesOrderByGasto() {

        String sql = "select cliente.nome, SUM(contrato.valor) as total_contrato" +
                " from cliente join contrato on cliente.id = contrato.id_cliente where cliente.ativo = true " +
                "GROUP BY cliente.nome order by total_contrato DESC limit 10";

        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            ResultSet rs = stmt.executeQuery();
            ArrayList<Cliente> clientes = new ArrayList<>();

            while(rs.next()) {
                Cliente c = new Cliente();
                c.setNomeCliente(rs.getString("nome"));
                c.setGastoTotal(rs.getBigDecimal("total_contrato"));
                clientes.add(c);
            }

            System.out.println("Clientes encontrados: " + clientes.size());

            return clientes;


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao listarClientes: " + e.getMessage(), e);
        }



    }


    public int countNovosClientes() {
        int count = 0;

        String sql = "select count(DISTINCT cliente.id) as cc from cliente join contrato on cliente.id = contrato.id_cliente where EXTRACT(MONTH FROM cliente.data_entrada) = " +
                "EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM cliente.data_entrada) = EXTRACT(YEAR FROM CURRENT_DATE)";

        try(
                Connection conn = ConectarBancoDeDados.conectarPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                count = rs.getInt("cc");
            }

            return count;


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao pegar clientes do mês: " + e.getMessage(), e);
        }



    }


    public List<Cliente> listarClientesComContato() throws SQLException, ClassNotFoundException {

        Map<String, Cliente> clienteMap = new HashMap<>();
        List<Contato> contatos = new ArrayList<>();
        String sql = "Select cliente.id, cliente.nome as cnome, cliente.cpf_cnpj, contato.nome as contnome, contato.email, contato.telefone from cliente join contato on cliente.id = contato.id_cliente" +
                " where cliente.ativo = true";

        Connection conn = ConectarBancoDeDados.conectarPostgres();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idCliente = rs.getInt("id");

            Cliente c = clienteMap.get(String.valueOf(idCliente));
            if (c == null) {
                c = new Cliente();
                c.setId(idCliente);
                c.setNomeCliente(rs.getString("cnome"));
                c.setCpf(rs.getString("cpf_cnpj"));
                c.setContatos(new ArrayList<>());

                clienteMap.put(String.valueOf(idCliente), c);
            }

            Contato contato = new Contato();
            contato.setNomeContato(rs.getString("contnome"));
            contato.setEmail(rs.getString("email"));
            contato.setTelefone(rs.getString("telefone"));

            c.getContatos().add(contato);
        }

        return new ArrayList<>(clienteMap.values());
    }


    public Cliente listarClientePeloId(int id) throws SQLException, ClassNotFoundException {

        String sql = "Select * from cliente where id = ? and ativo = true";
        Connection conn = ConectarBancoDeDados.conectarPostgres();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Cliente cliente = null;
        while (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNomeCliente(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf_cnpj"));
        }
        return cliente;

    }
}
