/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Professor;
import br.com.associacao.entidade.Telefone;
import br.com.utilitario.UtilGerador;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suporte
 */
public class ProfessorDaoImplTest {

    public ProfessorDaoImplTest() {
    }

    @Test
    public void testSalvar() throws Exception {
        
            
        System.out.println("Salvando professor");
        List<Telefone> telefones = new ArrayList<>();
        Professor professor = new Professor(
                null,
                "nome " + UtilGerador.gerarNome(),
                "CPF " + UtilGerador.gerarNumero(9),
                "Cracha  " + UtilGerador.gerarNumero(6)
        );
        
        // Aqui criamos o objeto telefone com o laço FOR 
        for (int i = 0; i < 3; i++) {
            Telefone telefone = new Telefone(
                    null,
                    UtilGerador.gerarTelefoneFixo(),
                    UtilGerador.gerarCaracter(6),
                    UtilGerador.gerarNome()
            );
            // Aqui passamos os objetos "telefone" criados para a lista com nome "telefones" do objeto Professor
            telefones.add(telefone);

        }
        // Aqui passamos a lista de telefones pela DAO
        professor.setTelefone(telefones);
        ProfessorDaoImpl professorDaoImpl = new ProfessorDaoImpl();
        professorDaoImpl.salvar(professor);

    }

}
