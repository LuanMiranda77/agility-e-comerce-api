package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Produto;

//@autor Jadson Feitosa #29

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Produto findByCodigoBarras(String codigoBarras);
	
	public boolean existsByCodigoBarras(String codigoBarras);
	
	public List<Produto> findProdutoByTituloContains(String title);

}
