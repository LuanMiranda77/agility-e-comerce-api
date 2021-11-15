package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class MercadoLivre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String client_id;
	
	@NotBlank
	private String client_secret;
	

	private String access_token;
	

	private String token_type;
	

	private Integer expires_in;
	

	private String scope;
	

	private Integer user_id;
	

	private String refresh_token;
	

}
