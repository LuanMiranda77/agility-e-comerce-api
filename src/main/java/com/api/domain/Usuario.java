package com.api.domain;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

//@autor Jadson Feitosa #AE-42

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;

	@NotNull
	private String login;
	
	@NotNull
	private String email;
	
	private Date dataCriacao;
	
	private Date dataAtualizacao;
	
	private Boolean status;
	
	@NotNull
	@Size(min = 6)
	private String password;

	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public void dataInicial() {
		this.dataCriacao = new Timestamp(System.currentTimeMillis());
		this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
	}
	
//	public void isAtive() {
//		
//		if (this.dataAtualizacao >= ) {
//			this.status = false;
//		}
//		else {
//			this.status = true; 
//		}
//	}
	
	
}
