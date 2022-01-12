package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Empresa;
import com.api.repository.EmpresaRepository;

@Service
public class EmpresaService implements ServiceBase<Empresa, Long> {
	
	@Autowired
	EmpresaRepository repository;
	
	@Override
	public Empresa save(Empresa pEntity) {
		Empresa empresa = repository.save(pEntity); 
		return empresa;
	}

	@Override
	public Empresa update(Long pID, Empresa pEntity) {
		Empresa empresa = repository.findById(pEntity.getId()).get();
		BeanUtils.copyProperties(pEntity, empresa, "id");
		this.save(empresa);
		empresa.setId(pEntity.getId());
		return empresa;
	}
	

}
