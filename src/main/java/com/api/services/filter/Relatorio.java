package com.api.services.filter;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Relatorio {
	
	private BigDecimal total;
	private Integer quantidade;
	
	public Relatorio(BigDecimal total, Integer quantidade) {
		super();
		this.total = total;
		this.quantidade = quantidade;
	}

	public Relatorio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}//RELATORIOS
