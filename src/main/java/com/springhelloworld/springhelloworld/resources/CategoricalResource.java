package com.springhelloworld.springhelloworld.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoricalResource {

    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping
    public String listar() {
        return "Rest Funcionando";
    }
}
