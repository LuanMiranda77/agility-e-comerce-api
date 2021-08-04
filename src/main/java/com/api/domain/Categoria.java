package com.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;


@Entity
@Data
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>();


	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	
	
	
}
