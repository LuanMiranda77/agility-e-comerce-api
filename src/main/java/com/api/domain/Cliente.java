package com.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.TipoCliente;

import lombok.Builder;
import lombok.Data;

//@autor Jadson Feitosa #40

@Entity
@Data
//@Builder
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@NotBlank
	@Size(min=11)
	private String cpfCnpj;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCliente tipo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	
	private String telefone;
	
	private String celular;

}
