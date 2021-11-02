package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Entity
@Data
public class EnderecoDeEntrega  {
	
	//@autor Jadson Feitosa #43
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String logradouro;
		
	private String numero;
		
	private String complemento;
		
	private String bairro;
		
	private String cidade;
		
	private String cep;
	
	@Enumerated(EnumType.STRING)
	private UF uf;
	
}
