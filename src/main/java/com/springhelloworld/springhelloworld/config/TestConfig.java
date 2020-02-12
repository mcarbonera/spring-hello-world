package com.springhelloworld.springhelloworld.config;

import com.springhelloworld.springhelloworld.domain.*;
import com.springhelloworld.springhelloworld.enums.EstadoPagamento;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dbService.instantiateTestDataBase();
        return true;
    }
}
