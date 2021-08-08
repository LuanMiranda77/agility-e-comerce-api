package com.api.services;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Produto;
import com.api.repository.ProdutoRepository;

// @autor Jadson Feitosa #29

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto save(@Valid Produto pEntity) {
		return produtoRepository.save(pEntity);
	}

	public Produto update(@Valid Long pID, Produto pEntity) {
		Produto produtoSalvo = produtoRepository.getById(pID);
		
		BeanUtils.copyProperties(pEntity, produtoSalvo,"id");
		produtoRepository.save(produtoSalvo);
		produtoSalvo.setId(pEntity.getId());
		
		return produtoSalvo;
	}
		
}
