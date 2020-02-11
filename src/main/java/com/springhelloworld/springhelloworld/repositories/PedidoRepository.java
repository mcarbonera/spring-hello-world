package com.springhelloworld.springhelloworld.repositories;

import com.springhelloworld.springhelloworld.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
