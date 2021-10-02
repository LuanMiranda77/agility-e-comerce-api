package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Produto;

//@autor Jadson Feitosa #29

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
