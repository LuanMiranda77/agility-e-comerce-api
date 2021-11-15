package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.MercadoLivre;
import com.api.repository.MercadoLivreRepository;

@Service
public class MercadoLivreService implements ServiceBase<MercadoLivre, Long> {
	
	@Autowired
	MercadoLivreRepository repository;
	
	@Override
	public MercadoLivre save(MercadoLivre pEntity) {
		MercadoLivre mercadoLivre = repository.save(pEntity); 
		return mercadoLivre;
	}

	@Override
	public MercadoLivre update(Long pID, MercadoLivre pEntity) {
		MercadoLivre mercadoLivre = repository.findById(pEntity.getId()).get();
		BeanUtils.copyProperties(pEntity, mercadoLivre, "id");
		this.save(mercadoLivre);
		mercadoLivre.setId(pEntity.getId());
		return mercadoLivre;
	}
	

}
