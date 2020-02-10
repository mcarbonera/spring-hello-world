package com.springhelloworld.springhelloworld.resources;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaService service;

    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
