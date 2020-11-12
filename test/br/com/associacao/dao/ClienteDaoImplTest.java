/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.dao;

import br.com.associacao.entidade.Cliente;
import br.com.associacao.entidade.Endereco;
import br.com.utilitario.UtilGerador;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDaoImpl clienteDaoImpl;

    public ClienteDaoImplTest() {
        clienteDaoImpl = new ClienteDaoImpl();
    }

 @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        cliente = new Cliente(
                null,
                "nome " + UtilGerador.gerarCaracter(10),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo(),
                Double.parseDouble(UtilGerador.gerarNumero(3))
        );
        Endereco endereco = new Endereco(null, 
                "Rua " + UtilGerador.gerarCaracter(10), 
                UtilGerador.gerarNumero(3), 
                "Bairro" + UtilGerador.gerarCaracter(10), 
                "Cidade " + UtilGerador.gerarCaracter(5),  
                "Estado " + UtilGerador.gerarCaracter(7),  
                UtilGerador.gerarNumero(5) + "-" + UtilGerador.gerarNumero(3) 
        );
        cliente.setEndereco(endereco);
        clienteDaoImpl.salvar(cliente);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        cliente = new Cliente(
                1,
                "cliente alterado",
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo(),
                Double.parseDouble(UtilGerador.gerarNumero(3))
        );
        clienteDaoImpl.alterar(cliente);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        clienteDaoImpl.excluir(1);
    }

//    @Test
    public void testPesquisarId() throws Exception {
        System.out.println("pesquisar por id");
        cliente = clienteDaoImpl.pesquisarPorId(2);
        System.out.println("id " + cliente.getId());
        System.out.println("Nome " + cliente.getNome());
        System.out.println("E-mail " + cliente.getEmail());
        System.out.println("Telefone " + cliente.getTelefone());
        System.out.println("Salário " + cliente.getSalario());
    }

//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisar por nome");
        List<Cliente> clientes = clienteDaoImpl.pesquisarPorNome("u");
        for (Cliente cli : clientes) {
            System.out.println("id " + cli.getId());
            System.out.println("Nome " + cli.getNome());
            System.out.println("E-mail " + cli.getEmail());
            System.out.println("Telefone " + cli.getTelefone());
            System.out.println("Salário " + cli.getSalario());
            System.out.println("");
        }
    }

}
