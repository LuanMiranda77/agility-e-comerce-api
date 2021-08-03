package com.api.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Produto  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String codigoBarras;
	@NotNull
	private String nome;
	@NotNull
	private BigDecimal precoVarejo;
	@NotNull
	private BigDecimal precoAtacado;
	@NotNull
	private Integer quantidade;
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "produto")
	private List<Imagem> imagens = new ArrayList<>();;
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto() {
	}

	public Produto(String codigoBarras, String nome, BigDecimal precoVarejo, BigDecimal precoAtacado,
			Integer quantidade, String descricao) {
		super();
		this.codigoBarras = codigoBarras;
		this.nome = nome;
		this.precoVarejo = precoVarejo;
		this.precoAtacado = precoAtacado;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}


	

//	@JsonIgnore
//	public List<Pedido> getPedidos(){
//		List<Pedido> lista = new ArrayList<>();
//		for (ItemPedido x : itens) {
//			lista.add(x.getPedido());
//		}
//		return lista;
//	}
}
