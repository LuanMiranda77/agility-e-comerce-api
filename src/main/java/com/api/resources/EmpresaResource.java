package com.api.resources;

import java.awt.print.Pageable;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.Empresa;
import com.api.repository.EmpresaRepository;
import com.api.services.EmpresaService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/empresa")
public class EmpresaResource implements ResourceBase<Empresa, Long>{
	
	@Autowired
	private EmpresaService EmpresaService;
	
	@Autowired
	private EmpresaRepository EmpresaRepository;

//	Salvar Empresa
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Empresa> save(@Valid @RequestBody Empresa pEntity, HttpServletResponse response) {
		Empresa EmpresaSalvo = null;
		EmpresaSalvo = EmpresaService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaSalvo);
	}

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<Empresa> update(@Valid @PathVariable Long pID, @RequestBody Empresa pEntity) {
		Empresa EmpresaSalvo = EmpresaService.update(pID, pEntity);
		return ResponseEntity.ok(EmpresaSalvo);
	}

//	Deletar Empresa
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		EmpresaRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Empresa> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(EmpresaRepository.findById(pID).get());
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<Empresa> pList) {
		EmpresaRepository.deleteAll(pList);
	}

	@GetMapping
	public List<Empresa> findAllList() {
		return EmpresaRepository.findAll();
	}

	@Override
	public Page<Empresa> findAllPage(Empresa pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
