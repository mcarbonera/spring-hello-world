package com.springhelloworld.springhelloworld;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringHelloWorldApplication implements CommandLineRunner {
    @Autowired
    CategoriaRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Teste 1");
        Categoria cat2 = new Categoria(null, "Teste 2");

        repo.saveAll(Arrays.asList(cat1,cat2));
    }
}
