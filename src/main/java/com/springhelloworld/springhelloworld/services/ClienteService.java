package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.Categoria;
import com.springhelloworld.springhelloworld.domain.Cliente;
import com.springhelloworld.springhelloworld.dto.CategoriaDto;
import com.springhelloworld.springhelloworld.dto.ClienteDto;
import com.springhelloworld.springhelloworld.exceptions.DataIntegrityException;
import com.springhelloworld.springhelloworld.exceptions.ObjectNotFoundException;
import com.springhelloworld.springhelloworld.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir pois existem entidades relacionadas. ");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDto objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

