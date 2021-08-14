package com.api.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	@Id
	private String nomeRole;
	
	@ManyToMany
	 private List<Usuario> usuarios;
	
	@Override
	public String getAuthority() {
		
		return this.nomeRole;
	}

}
