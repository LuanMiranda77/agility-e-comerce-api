package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.domain.enuns.UF;

import lombok.Data;

/**
 * @author Luan Miranda
 * @Demanda AE-67
 */

@Entity
@Data
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size( min=18, max = 18)
	private String CNPJ;
	
	private String instEstadual;
	
	private String instMunicipal;
	
	@NotBlank
	private String razaoSocial;
	
	@NotBlank
	private String nomeFantasia;
	
	@NotBlank
    private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String cep;

	@Enumerated(EnumType.STRING)
	private UF uf;
	
//	contatos--------------------------------------
	
	@NotBlank
	private String emailPrincipal;
	
	@NotBlank
	private String emailSegundario;
	
	@NotBlank
	private String celular1;
	
	@NotBlank
	private String celular2;
	
	@NotBlank
	private String foneFixo;
	
	
	
	
	
	
	
	
	
	

}
