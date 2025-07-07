package com.dominio.springradio1.service;



import com.dominio.springradio1.dao.ClienteDao;
import com.dominio.springradio1.dao.ConectarBancoDeDados;
import com.dominio.springradio1.dao.ContatoDao;
import com.dominio.springradio1.model.Cliente;
import com.dominio.springradio1.model.Contato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private ClienteDao clienteDao = new ClienteDao();

    public List<Cliente> listar() {
        return clienteDao.getAllClientes();
    }

    public List<Contato> listarContato(int id) {
        return clienteDao.listarContatosCliente(id);
    }

    public boolean cadastrarClienteComContato(Cliente cliente, Contato contato) throws SQLException, ClassNotFoundException {
        Connection connection = ConectarBancoDeDados.conectarPostgres();

        System.out.println("Contato - create");
        System.out.println("Nome do contato: " + contato.getNomeContato());

        connection.setAutoCommit(false);
        int id_cliente = clienteDao.createCliente(cliente, connection);

        if (id_cliente > 0) {
            ContatoDao contatoDao = new ContatoDao();
            if(contatoDao.create(contato, id_cliente, connection)){
                connection.commit();
                return true;
            } else {
                connection.rollback();
                connection.close();
                return false;
            }
        }
        return false;
    }


    public List<Cliente> listarClienteOrdenado() {
        System.out.println("Executei a listarClienteOrdenad");
        return clienteDao.getAllClientesOrderByGasto();
    }

    public int countClientesNovosMes(){
        System.out.println("Clientes novos Service...");
        return clienteDao.countNovosClientes();
    }

    public List<Cliente> listarClientesComContato() throws SQLException, ClassNotFoundException {
        return clienteDao.listarClientesComContato();
    }

    public Cliente listarClientePeloId(int id) throws SQLException, ClassNotFoundException {
        return clienteDao.listarClientePeloId(id);
    }


    public boolean updateCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
            return clienteDao.updateCliente(cliente);
    }

    public String deleteCliente(int id) throws SQLException, ClassNotFoundException {

        if(clienteDao.deleteCliente(id)){
            return "Cliente deletado com sucesso!";
        } else {
            return "Falha ao deletar cliente!";
        }
    }

}
