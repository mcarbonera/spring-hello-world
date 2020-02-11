package com.springhelloworld.springhelloworld;

import com.springhelloworld.springhelloworld.domain.*;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringHelloWorldApplication implements CommandLineRunner {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* RELACIONAMENTO CATEGORIA PRODUTO */
        Categoria cat1 = new Categoria(null, "Teste 1");
        Categoria cat2 = new Categoria(null, "Teste 2");

        Produto p1 = new Produto(null, "Produto 1",2000.00);
        Produto p2 = new Produto(null, "Produto 2",800.00);
        Produto p3 = new Produto(null, "Produto 3",80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

        /* RELACIONAMENTO CIDADE ESTADO */
        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        /* RELACIONAMENTO CLIENTE ENDERECO */
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "1234567890986", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("1234564321","123455432"));

        Endereco e1 = new Endereco(null,"Rua Flores", "300", "Apto 303", "Jardim", "345676434", cli1, c1);
        Endereco e2 = new Endereco(null,"Avenida Matos", "105", "Sala 800", "Centro", "345765335", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
    }
}
