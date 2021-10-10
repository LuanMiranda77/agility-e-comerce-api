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

import com.api.domain.Usuario;
import com.api.repository.UsuarioRepository;
import com.api.services.UsuarioService;

//@autor Jadson Feitosa #AE-42

@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource implements ResourceBase<Usuario, Long> {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	
//	Salvar Usuario
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario pEntity, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
//	Atualizar Usuario
	@PutMapping
	public ResponseEntity<Usuario> update(@Valid Long pID, @RequestBody Usuario pEntity) {
		Usuario usuarioSalvo = usuarioService.update(pEntity);
		return ResponseEntity.ok(usuarioSalvo);
	}

//	Deletar Usuario
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		usuarioRepository.deleteById(pID);
	}

//	Filtro por ID
	public ResponseEntity<Usuario> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(usuarioRepository.findById(pID).get());
	}

//  Listar usuario
	@GetMapping
	public List<Usuario> findAllList() {
		return usuarioRepository.findAll();
	}

	public Page<Usuario> findAllPage(Usuario pFilter, Pageable pPage) {
		return null;
	}

}
