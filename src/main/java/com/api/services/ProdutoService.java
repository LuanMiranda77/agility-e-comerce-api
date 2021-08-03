package com.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Produto;
import com.api.repository.ProdutoRepository;
import com.api.services.exceptions.ObjectNotFoundException;



@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;

	public Produto find(Long id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		}
		
}
