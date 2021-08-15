package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class ImagemProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String url;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;

	
	
	public ImagemProduto() {
		
	}

	public ImagemProduto(Long id, String url, Produto produto) {
		super();
		this.id = id;
		this.url = url;
		this.produto = produto;
	}
	
	
	
	
	
}
