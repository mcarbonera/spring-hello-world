package com.springhelloworld.springhelloworld.repositories;

import com.springhelloworld.springhelloworld.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /*
     * O nome no formato "findByEmail"
     * faz com que o Spring Data implemente
     * este método para nós, precioso.
     */
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
