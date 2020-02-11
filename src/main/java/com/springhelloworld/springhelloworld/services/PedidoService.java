package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.Pedido;
import com.springhelloworld.springhelloworld.exceptions.ObjectNotFoundException;
import com.springhelloworld.springhelloworld.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}

