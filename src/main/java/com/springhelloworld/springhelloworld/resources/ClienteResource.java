package com.springhelloworld.springhelloworld.resources;

import com.springhelloworld.springhelloworld.domain.Cliente;
import com.springhelloworld.springhelloworld.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
