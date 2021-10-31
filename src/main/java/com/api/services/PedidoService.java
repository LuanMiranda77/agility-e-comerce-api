package com.api.services;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.ItemPedido;
import com.api.domain.Pedido;
import com.api.repository.ItemPedidoRepository;
import com.api.repository.PedidoRepository;

//@autor Jadson Feitosa #AE-36

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido save(Pedido pEntity) {
		List<ItemPedido>itemPedidos = pEntity.getProdutos();
		
		Pedido pedidoSalvo = pedidoRepository.save(pEntity);
		
		
		for (int i = 0; i < itemPedidos.size(); i++) {
			pEntity.getProdutos().get(i).setPedido(pedidoSalvo);
			pEntity.getProdutos().get(i).setDataVenda(pedidoSalvo.getDataFechamento());
			itemPedidoRepository.save(pEntity.getProdutos().get(i));
		}
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
