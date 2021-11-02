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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.EstatusUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao ;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private EstatusUsuario status = EstatusUsuario.ATIVO;
	
//	@JsonIgnore
	@NotNull
	@Size(min = 6)
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Roles role;

	@PrePersist
	public void dataInicial() {
		this.dataCriacao = new Timestamp(System.currentTimeMillis());
	}
	
	@PreUpdate
	public void dataAtualizacao() {
		this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
	}
	
	
}
