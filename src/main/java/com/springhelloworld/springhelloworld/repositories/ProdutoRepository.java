package com.springhelloworld.springhelloworld.repositories;

import com.springhelloworld.springhelloworld.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
