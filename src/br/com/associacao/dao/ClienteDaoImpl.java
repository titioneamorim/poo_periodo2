/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Cliente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ClienteDaoImpl implements Serializable {

    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente(nome, email, telefone, salario)"
                + " VALUES(?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, cliente.getNome());
            preparando.setString(2, cliente.getEmail());
            preparando.setString(3, cliente.getTelefone());
            preparando.setDouble(4, cliente.getSalario());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            cliente.setId(resultSet.getInt(1));
            EnderecoDaoImpl enderecoDaoImpl = new EnderecoDaoImpl();
            enderecoDaoImpl.salvarCliente(cliente.getEndereco(), cliente.getId(), conexao);
        } catch (SQLException eSQL) {
            System.out.println("Erro ao salvar cliente " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    public void alterar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, email = ?,"
                + " telefone = ?, salario = ? WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, cliente.getNome());
            preparando.setString(2, cliente.getEmail());
            preparando.setString(3, cliente.getTelefone());
            preparando.setDouble(4, cliente.getSalario());
            preparando.setInt(5, cliente.getId());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao alterar " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.
                    prepareStatement("DELETE FROM cliente WHERE id = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Cliente pesquisarPorId(Integer id) throws SQLException {
        Cliente cliente = null;
        String consulta = "SELECT * FROM cliente WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setSalario(resultSet.getDouble("salario"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return cliente;
    }

    public List<Cliente> pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM cliente WHERE nome LIKE ?";
        Cliente cliente;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, "%" + nome + "%");
            resultSet = preparando.executeQuery();
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setSalario(resultSet.getDouble("salario"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisarPorNome " + e.getMessage());
        }finally{
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return clientes;
    }

}
