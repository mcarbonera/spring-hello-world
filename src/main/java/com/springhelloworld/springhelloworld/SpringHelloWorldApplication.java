package com.springhelloworld.springhelloworld;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.domain.Produto;
import com.springhelloworld.springhelloworld.repositories.CategoriaRepository;
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

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
    }
}
