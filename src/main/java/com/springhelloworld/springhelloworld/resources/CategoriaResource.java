package com.springhelloworld.springhelloworld.resources;

import com.springhelloworld.springhelloworld.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping
    public List<Categoria> listar() {
        Categoria cat1 = new Categoria(1,"Teste1");
        Categoria cat2 = new Categoria(2,"Teste2");

        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);

        return lista;
    }
}
