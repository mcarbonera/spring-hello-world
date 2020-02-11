package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.Cidade;
import com.springhelloworld.springhelloworld.domain.Cliente;
import com.springhelloworld.springhelloworld.domain.Endereco;
import com.springhelloworld.springhelloworld.dto.ClienteDto;
import com.springhelloworld.springhelloworld.dto.ClienteNewDto;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.exceptions.DataIntegrityException;
import com.springhelloworld.springhelloworld.exceptions.ObjectNotFoundException;
import com.springhelloworld.springhelloworld.repositories.ClienteRepository;
import com.springhelloworld.springhelloworld.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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

    public Cliente fromDto(ClienteNewDto objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!=null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3()!=null){
            cli.getTelefones().add(objDto.getTelefone3());
        }

        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

