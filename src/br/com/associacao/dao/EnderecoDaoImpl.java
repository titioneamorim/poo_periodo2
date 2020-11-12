/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Endereco;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author suporte
 */
public class EnderecoDaoImpl implements Serializable{
    

    
    private PreparedStatement preparando;
    

    public void salvarCliente(Endereco endereco, int idEstrangeiro, Connection conexao) throws SQLException {
        String sql = "INSERT INTO endereco(logradouro, numero, cep, bairro, cidade, estado, idCliente)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        salvar(conexao, sql, endereco, idEstrangeiro);
    }
    public void salvarFornecedor(Endereco endereco, int idEstrangeiro, Connection conexao) throws SQLException {
        String sql = "INSERT INTO endereco(logradouro, numero, cep, bairro, cidade, estado, idFornecedor)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        salvar(conexao, sql, endereco, idEstrangeiro);
    }

    private void salvar(Connection conexao, String sql, Endereco endereco, int idEstrangeiro) throws SQLException {
        try {
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, endereco.getLogradouro());
            preparando.setString(2, endereco.getNumero());
            preparando.setString(3, endereco.getCep());
            preparando.setString(4, endereco.getBairro());
            preparando.setString(5, endereco.getCidade());
            preparando.setString(6, endereco.getEstado());
            preparando.setInt(7, idEstrangeiro);
            preparando.executeUpdate();
        } catch (SQLException eSQL) {
            System.out.println("Erro ao salvar endereco " + eSQL.getMessage());
            conexao.rollback();
        } 
    }

    public void alterar(Endereco endereco, Connection conexao) throws SQLException {
        String sql = "INSERT INTO endereco(logradouro, numero, cep, bairro, cidade, estado)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, endereco.getLogradouro());
            preparando.setString(2, endereco.getNumero());
            preparando.setString(3, endereco.getCep());
            preparando.setString(4, endereco.getBairro());
            preparando.setString(5, endereco.getCidade());
            preparando.setString(6, endereco.getEstado());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao alterar endereco " + e.getMessage());
        } 
    }
 
}
