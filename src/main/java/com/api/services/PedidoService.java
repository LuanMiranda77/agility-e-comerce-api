package com.api.services;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Pedido;
import com.api.repository.PedidoRepository;

//@autor Jadson Feitosa #AE-36

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido save(Pedido pEntity) {
		return pedidoRepository.save(pEntity);
	}
	
	public Pedido update(Pedido pEntity) {
		Pedido pedidoSalvo = pedidoRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, pedidoSalvo, "id");
		pedidoRepository.save(pedidoSalvo);
		pedidoSalvo.setId(pEntity.getId());
		return pedidoSalvo;
		
	}
	
	public void isAtive(Pedido pEntity) {
		update(pEntity);
	}
		
}
