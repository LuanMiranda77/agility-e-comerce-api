package com.api.domain;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Roles role;

	
	public void dataInicial() {
		this.dataCriacao = new Timestamp(System.currentTimeMillis());
		this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
	}
	
	
}
