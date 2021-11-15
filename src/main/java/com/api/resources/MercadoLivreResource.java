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

import com.api.domain.MercadoLivre;
import com.api.repository.MercadoLivreRepository;
import com.api.services.MercadoLivreService;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/m_livre")
public class MercadoLivreResource implements ResourceBase<MercadoLivre, Long>{
	
	@Autowired
	private MercadoLivreService MercadoLivreService;
	
	@Autowired
	private MercadoLivreRepository MercadoLivreRepository;

//	Salvar MercadoLivre
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MercadoLivre> save(@Valid @RequestBody MercadoLivre pEntity, HttpServletResponse response) {
		MercadoLivre MercadoLivreSalvo = null;
		MercadoLivreSalvo = MercadoLivreService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(MercadoLivreSalvo);
	}

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<MercadoLivre> update(@Valid @PathVariable Long pID, @RequestBody MercadoLivre pEntity) {
		MercadoLivre MercadoLivreSalvo = MercadoLivreService.update(pID, pEntity);
		return ResponseEntity.ok(MercadoLivreSalvo);
	}

//	Deletar MercadoLivre
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		MercadoLivreRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<MercadoLivre> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(MercadoLivreRepository.findById(pID).get());
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<MercadoLivre> pList) {
		MercadoLivreRepository.deleteAll(pList);
	}

	@GetMapping
	public List<MercadoLivre> findAllList() {
		return MercadoLivreRepository.findAll();
	}

	@Override
	public Page<MercadoLivre> findAllPage(MercadoLivre pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
