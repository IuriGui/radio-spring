package com.dominio.springradio1.dao;




import com.dominio.springradio1.model.User;
import com.dominio.springradio1.model.Vendedor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDao {

    public List<Vendedor> listarVendedores() {
        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            String sql = "select vendedor.id as vendedor_id,usuario.id as usuario_id, vendedor.nome, vendedor.telefone, vendedor.salario as salario_base, vendedor.comissao as taxa_comissao," +
                    "vendedor.total_vendido from vendedor join usuario on vendedor.id_usuario = usuario.id where usuario.ativo = true";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Vendedor> lista = new ArrayList<Vendedor>();

            while(rs.next()){
                Vendedor v = new Vendedor(rs.getInt("vendedor_id"), rs.getBigDecimal("salario_base"),
                        rs.getString("nome"), rs.getInt("vendedor_id"), new BigDecimal(-1), rs.getDouble("taxa_comissao"),
                        rs.getBigDecimal("total_vendido"), rs.getString("telefone"));;

                lista.add(v);
            }

            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean atualizarTotalVendido(BigDecimal valor, int id){
        String sql = "UPDATE vendedor SET total_vendido = total_vendido + ? WHERE id = ?";

        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, valor);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro ao atualizar vendedor" + e);
        }


    }


    public boolean criarVendedor(Vendedor v){
        String sql = "INSERT INTO vendedor (nome, telefone, id_usuario) values (?, ?, ?)";
        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, v.getNome());
            ps.setString(2, v.getTelefone());
            ps.setInt(3, v.getIdUser());

            return ps.executeUpdate() > 0;

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }


    public String AcharNomeVendedor(User u){
        String sql = "Select nome from vendedor where id_usuario = ?";

        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getId());

            ResultSet rs = ps.executeQuery();;

            String name ="";

            while(rs.next()){
                name = rs.getString("nome");
            }

            return name;


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }


    public boolean updateVendedor(Vendedor vendedor) {
        String sql = "UPDATE vendedor SET nome = ?, telefone = ? WHERE id = ?";
        String sqlUser = "UPDATE usuario SET email = ? WHERE id = ?";

        try (Connection conn = ConectarBancoDeDados.conectarPostgres()) {
            PreparedStatement psVendedor = conn.prepareStatement(sql);
            psVendedor.setString(1, vendedor.getNome());
            psVendedor.setString(2, vendedor.getTelefone());
            psVendedor.setInt(3, vendedor.getId());

            PreparedStatement psUsuario = conn.prepareStatement(sqlUser);
            psUsuario.setString(1, vendedor.getEmail());
            psUsuario.setInt(2, vendedor.getIdUser());

            int linhasVendedor = psVendedor.executeUpdate();
            int linhasUsuario = psUsuario.executeUpdate();

            return linhasVendedor > 0 && linhasUsuario > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao editar vendedor: " + e.getMessage(), e);
        }
    }

    public Vendedor buscarPorId(int id) {
        String sql = "Select * from vendedor where id_usuario = ?";

        try(Connection conn = ConectarBancoDeDados.conectarPostgres()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();;

            String name ="";

            while(rs.next()){
                Vendedor v = new Vendedor();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setTelefone(rs.getString("telefone"));
                return v;
            }

           return null;


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
