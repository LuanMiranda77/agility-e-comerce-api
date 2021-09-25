package com.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.ImagemProduto;
import com.api.domain.Produto;
import com.api.repository.ImagemProdutoRepository;
import com.api.repository.ProdutoRepository;

// @autor Jadson Feitosa #29

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;

	public Produto save(Produto pEntity) throws Exception {
		if(produtoRepository.existsByCodigoBarras(pEntity.getCodigoBarras())) {
			throw new Exception("Produto já cadastrado");
		}
		Produto produtoSalvo = produtoRepository.save(pEntity);
		pEntity.getImagens().forEach(item->item.setProduto(produtoSalvo)); 
		imagemProdutoRepository.saveAll(pEntity.getImagens());
		return produtoSalvo;
	}

	public Produto update(Long pID, Produto pEntity) {
		Produto produtoSalvo = produtoRepository.findById(pID).get();
		
		BeanUtils.copyProperties(pEntity, produtoSalvo,"id");
		produtoRepository.save(produtoSalvo);
		produtoSalvo.setId(pEntity.getId());
		
		return produtoSalvo;
	}
	
	public void deleteAll(List<Produto> pList) {
		produtoRepository.deleteAll(pList);
		
	}
		
}
