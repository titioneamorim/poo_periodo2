/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.associacao.dao;

import br.com.associacao.entidade.Endereco;
import br.com.associacao.entidade.Fornecedor;
import br.com.utilitario.UtilGerador;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suporte
 */

public class FornecedorDaoImplTest {
    private Fornecedor fornecedor;
    private FornecedorDaoImpl fornecedorDaoimpl;
    
    
    public FornecedorDaoImplTest() {
        fornecedorDaoimpl = new FornecedorDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("Salvando fornecedor");
        List<Endereco> enderecos = new ArrayList<>();
        fornecedor = new Fornecedor( 
                null,
                UtilGerador.gerarCaracter(10), 
                UtilGerador.gerarCaracter(10), 
                UtilGerador.gerarCaracter(6), 
                UtilGerador.gerarEmail(), 
                UtilGerador.gerarTelefoneFixo()
        );
        
        for (int i = 0; i < 2; i++) {
            enderecos.add(gerarEndereco());
        }
        fornecedor.setEnderecos(enderecos);
        fornecedorDaoimpl.salvar(fornecedor);
        System.out.println("Fornecedor Salvo com sucesso");        
    }
    
    private Endereco gerarEndereco(){
        Endereco endereco = new Endereco(
                null, 
                "Rua " + UtilGerador.gerarCaracter(10), 
                UtilGerador.gerarNumero(3), 
                "Bairro" + UtilGerador.gerarCaracter(10), 
                "Cidade " + UtilGerador.gerarCaracter(5),  
                "Estado " + UtilGerador.gerarCaracter(7),  
                UtilGerador.gerarNumero(5) + "-" + UtilGerador.gerarNumero(3) 
        );
        return endereco;
    }
    
}
