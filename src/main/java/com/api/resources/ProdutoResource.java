package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.domain.Produto;
import com.api.services.ProdutoService;

//@autor Jadson Feitosa #29

public class ProdutoResource implements ResourceBase<Produto, Long>{
	
	@Autowired
	private ProdutoService produtoService;

//	Salvar Produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto pEntity, HttpServletResponse response) {
		Produto produtoSalvo = produtoService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

//	Atualizar entidade 
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> update(@Valid Long pID, Produto pEntity) {
		Produto produtoSalvo = produtoService.update(pID, pEntity);
		return ResponseEntity.ok(produtoSalvo);
	}

//	Deletar produto
	@DeleteMapping("/{codigo}")
	public void delete(Long pID) {
		produtoService.delete(pID);
	}

//	Filtro por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(Long pID) {
		return ResponseEntity.ok(produtoService.find(pID)) ;
	}

	
	public Page<Produto> findAllPage(Produto pFilter, Pageable pPage) {
		return null;
	}

	public List<Produto> findAllList() {
		return null;
	}

}
