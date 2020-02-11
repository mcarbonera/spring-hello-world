package com.springhelloworld.springhelloworld.resources;

import com.springhelloworld.springhelloworld.domain.Cliente;
import com.springhelloworld.springhelloworld.dto.ClienteDto;
import com.springhelloworld.springhelloworld.dto.ClienteNewDto;
import com.springhelloworld.springhelloworld.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto objDto) {
        Cliente obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto objDto, @PathVariable Integer id) {
        Cliente obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDto> listDto = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/page")
    public ResponseEntity<Page<ClienteDto>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDto> listDto = list.map(obj -> new ClienteDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
