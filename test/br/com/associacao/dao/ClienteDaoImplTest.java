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
import javax.swing.JOptionPane;
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
    
   

// @Test
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
    

   @Test
    public void testAlterar() throws Exception {
        System.out.println("alterando ");
        //cria o objeto cliente e recebe o valor do ID do cliente
        cliente = clienteDaoImpl.pesquisarPorId(Integer.parseInt(JOptionPane.showInputDialog("Digite o id")));
        // seta o nome do cliente na próxima linha e o logradouro do endereco na linha seguinte
        cliente.setNome("Nome alterado" + UtilGerador.criarNumeroAleatorioEntre2Valores(1, 50));
        cliente.getEndereco().setLogradouro("Log Alterado" + UtilGerador.criarNumeroAleatorioEntre2Valores(1, 100));
        // chama o metodo alterar e passa o objeto para a dao
        clienteDaoImpl.alterar(cliente);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        clienteDaoImpl.excluir(1);
    }

// @Test
    public void testPesquisarId() throws Exception {
        System.out.println("pesquisar por id");
        cliente = clienteDaoImpl.pesquisarPorId(Integer.parseInt(JOptionPane.showInputDialog("Digite o id")));
        System.out.println("id " + cliente.getId());
        System.out.println("Nome " + cliente.getNome());
        System.out.println("E-mail " + cliente.getEmail());
        System.out.println("Telefone " + cliente.getTelefone());
        System.out.println("Salário " + cliente.getSalario());
        // Aqui traz as informações do endereço
        System.out.println("IdEndereco " + cliente.getEndereco().getId());
        System.out.println("Logradouro " + cliente.getEndereco().getLogradouro());
        System.out.println("Numero " + cliente.getEndereco().getNumero());
        System.out.println("CEP " + cliente.getEndereco().getCep());
        System.out.println("Bairro " + cliente.getEndereco().getBairro());
        System.out.println("Cidade " + cliente.getEndereco().getCidade());
        System.out.println("Estado " + cliente.getEndereco().getEstado());
        
        
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
