package com.api.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String codigoBarras;
	@NotNull
	private String titulo;
	@NotNull
	private BigDecimal precoVarejo;
	@NotNull
	private BigDecimal precoAtacado;
	@NotNull
	private Integer quantidade;
	@NotNull
	private String descricao;
	@NotNull
	private String peso;
	@NotNull
	private String comprimento;
	@NotNull
	private String altura;
	@NotNull
	private String largura;
	
	private Integer estrelas;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="produto_id")
	private List<ImagemProduto> imagens = new ArrayList<>();
	
	
//	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
			   joinColumns = @JoinColumn(name = "produto_id"),
			   inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	


	


	

//	@JsonIgnore
//	public List<Pedido> getPedidos(){
//		List<Pedido> lista = new ArrayList<>();
//		for (ItemPedido x : itens) {
//			lista.add(x.getPedido());
//		}
//		return lista;
//	}
}
