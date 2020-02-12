package com.springhelloworld.springhelloworld;

import com.springhelloworld.springhelloworld.domain.*;
import com.springhelloworld.springhelloworld.enums.EstadoPagamento;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringHelloWorldApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
