package com.api.domain;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.domain.enuns.EstatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeCriacao;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private EnderecoDeEntrega enderecoDeEntrega;
	
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> produtos = new ArrayList<ItemPedido>();
	
	private double valorTotal;
	
	private double valorFrete;
	
	@Enumerated(EnumType.STRING)
	private EstatusPedido estatus;
	
	@PrePersist
	public void setDataCriacao() {
		this.dataDeCriacao = new Date();
	}
	
}
