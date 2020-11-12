/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author suporte
 */
public class TelefoneDaoImpl {

    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    public void salvarTelProfessor(Telefone telefone, Connection conexao, int chaveEstrangeira) throws SQLException {
        String sql = "INSERT INTO telefone(numero, tipo, operadora, id_professor)"
                + " VALUES(?, ?, ?, ?)";
        salvar(sql, telefone, conexao, chaveEstrangeira);

    }

    private void salvar(String sql, Telefone telefone, Connection conexao, int chaveEstrangeira) {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, telefone.getNumero());
            preparando.setString(2, telefone.getTipo());
            preparando.setString(3, telefone.getOperadora());            
            preparando.setInt(4, chaveEstrangeira);            
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            telefone.setId(resultSet.getInt(1));
            
        } catch (SQLException e) {
            System.out.println("Erro ao salvar telefone " + e.getMessage());
        }
    }

}
