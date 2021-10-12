package com.api.domain.TO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.api.domain.Roles;
import com.api.domain.Usuario;

public class UsuarioTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String email;
	private String password;
	private Date dataCriacao ;
	private Date dataAtualizacao;
	private Boolean status;
	private Roles role;
	
	public UsuarioTO() {
		super();
	}

	public UsuarioTO(Long id, String nome, String login, String email,String password, Date dataCriacao, Date dataAtualizacao,
			Boolean status, Roles role) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.password = password;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.status = status;
		this.role = role;
	}
	
	
	public Usuario converteParaEntidade(UsuarioTO to) {
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(to, usuario,"password");
		return usuario;
	}

	public UsuarioTO converteParaTO(Usuario usuario) {
		UsuarioTO to = new UsuarioTO();
		BeanUtils.copyProperties(usuario, to, "password");
		return to;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
