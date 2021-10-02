package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Usuario;
import com.api.repository.UsuarioRepository;

//@autor Jadson Feitosa #AE-42

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario pEntity) {
		return usuarioRepository.save(pEntity);
		
	}
	
	public Usuario update( Usuario pEntity) {
		Usuario usuarioSalvo = usuarioRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, usuarioSalvo,"id");
		usuarioRepository.save(usuarioSalvo);
		usuarioSalvo.setId(pEntity.getId());
		
		return usuarioSalvo;
	}
	
	public void isAtive(Usuario pEntity) {
		update(pEntity);
	}

}
