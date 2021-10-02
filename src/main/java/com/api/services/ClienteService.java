package com.api.services;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Cliente;
import com.api.repository.ClienteRepository;

//@autor Jadson Feitosa #AE-40

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente pEntity) {
		return clienteRepository.save(pEntity);
	}
	
	public Cliente update(Cliente pEntity) {
		Cliente clienteSalvo = clienteRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, clienteSalvo, "id");
		clienteRepository.save(clienteSalvo);
		clienteSalvo.setId(pEntity.getId());
		return clienteSalvo;
		
	}
	
	public void isAtive(Cliente pEntity) {
		update(pEntity);
	}
		
}
