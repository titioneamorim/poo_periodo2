/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Professor;
import br.com.associacao.entidade.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suporte
 */
public class ProfessorDaoImpl {
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    

    public void salvar(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor(nome, cpf, numeroCracha)"
                + " VALUES(?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, professor.getNome());
            preparando.setString(2, professor.getCpf());
            preparando.setString(3, professor.getNumeroCracha());
            preparando.executeUpdate();
            // aqui a variavel resultset recebe o id do professor gerado no banco de dados
            resultSet = preparando.getGeneratedKeys();
            // aqui o .next verifica se há alguma informação no resultset e se for true passa pra linha abaixo
            resultSet.next();
            // aqui a variavel resultset passa o id do professor para o objeto professor .. 
            professor.setId(resultSet.getInt(1));
            TelefoneDaoImpl telefoneDaoImpl = new TelefoneDaoImpl();
            // esse laço FOR verifica se há objeto telefone na lista e salva com o metodo DAO
            for (Telefone telefone : professor.getTelefone()) {
                telefoneDaoImpl.salvarTelProfessor(telefone, conexao, professor.getId());
            }
        } catch (SQLException eSQL) {
            System.out.println("Erro ao salvar professor " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

      
}
