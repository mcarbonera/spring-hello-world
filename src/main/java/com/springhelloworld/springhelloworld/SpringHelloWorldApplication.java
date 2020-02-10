package com.springhelloworld.springhelloworld;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.domain.Cidade;
import com.springhelloworld.springhelloworld.domain.Estado;
import com.springhelloworld.springhelloworld.domain.Produto;
import com.springhelloworld.springhelloworld.repositories.CategoriaRepository;
import com.springhelloworld.springhelloworld.repositories.CidadeRepository;
import com.springhelloworld.springhelloworld.repositories.EstadoRepository;
import com.springhelloworld.springhelloworld.repositories.ProdutoRepository;
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
    }
}
