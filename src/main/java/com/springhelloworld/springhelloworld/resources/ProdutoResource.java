package com.springhelloworld.springhelloworld.resources;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.domain.Produto;
import com.springhelloworld.springhelloworld.dto.CategoriaDto;
import com.springhelloworld.springhelloworld.dto.ProdutoDto;
import com.springhelloworld.springhelloworld.resources.utils.Url;
import com.springhelloworld.springhelloworld.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findPage(
            @RequestParam(value="nome", defaultValue = "") String nome,
            @RequestParam(value="categorias", defaultValue = "") String categorias,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = Url.decodeParam(nome);
        List<Integer> ids = Url.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
