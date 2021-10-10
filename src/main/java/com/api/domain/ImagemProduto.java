package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class ImagemProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String objectURL;
	
	private String hash;

	
	public ImagemProduto() {
		
	}

	public ImagemProduto(Long id, String url, String hash) {
		super();
		this.id = id;
		this.objectURL = url;
		this.hash = hash;
	}
	
	
	
	
	
}
