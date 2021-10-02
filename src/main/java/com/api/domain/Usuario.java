package com.api.domain;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.domain.enuns.Status;
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
	
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(
			name = "user_id", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> role;
	
	@Enumerated
	private Status status;
	
	public Usuario() {
		
	}

	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
