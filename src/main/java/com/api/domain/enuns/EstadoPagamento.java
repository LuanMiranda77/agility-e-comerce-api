package com.api.domain.enuns;


public enum EstadoPagamento {
	
	PENDENTE(1, "PENDENTE"),
	FINALIZADO(2, "FINALIZADO"),
	CANCELADO(3, "CANCELADO");
	
	private int cod;
	private String descrecao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descrecao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescrecao() {
		return descrecao;
	}

	public void setDescrecao(String descrecao) {
		this.descrecao = descrecao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
