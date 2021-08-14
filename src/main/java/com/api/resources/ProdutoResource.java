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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Produto;
import com.api.repository.ProdutoRepository;
import com.api.services.ProdutoService;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource implements ResourceBase<Produto, Long>{
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;

//	Salvar Produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto pEntity, HttpServletResponse response) {
		Produto produtoSalvo = produtoService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<Produto> update(@Valid @PathVariable Long pID, @RequestBody Produto pEntity) {
		Produto produtoSalvo = produtoService.update(pID, pEntity);
		return ResponseEntity.ok(produtoSalvo);
	}

//	Deletar produto
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		produtoRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Produto> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(produtoRepository.findById(pID).get());
	}

	
	public Page<Produto> findAllPage(Produto pFilter, Pageable pPage) {
		return null;
	}

	@GetMapping
	public List<Produto> findAllList() {
		return produtoRepository.findAll();
	}

}
