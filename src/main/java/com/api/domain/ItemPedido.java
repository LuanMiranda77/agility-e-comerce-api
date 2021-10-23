package com.api.domain;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;

	private int quantidadeVendida;
	


}
