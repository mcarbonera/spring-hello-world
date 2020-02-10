package com.springhelloworld.springhelloworld.repositories;

import com.springhelloworld.springhelloworld.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
