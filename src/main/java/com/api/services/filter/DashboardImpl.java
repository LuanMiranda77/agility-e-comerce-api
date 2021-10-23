package com.api.services.filter;

import java.util.List;

import lombok.Data;

@Data
public class DashboardImpl {
	
	private Relatorio ticketMedio;
	private Relatorio totalPedidoRealizado;
	private Relatorio totalPedidoPago;
	private Relatorio totalPedidoCancelado;
	private double totalGeralMes;
	private double totalGeralMesPassado;
	private double totalVarejo;
	private double totalAtacado;
	private double totalVarejoCredito;
	private double totalVarejoBoleto;
	private double totalAtacadoCredito;
	private double totalAtacadoBoleto;
	private List<Double> arrayVendaPorHoras;
	private Categorias arrayVendaPorCategorias;
	private List<Double> arrayFormasPagamentos;
	private Clientes arrayTopClientes;
	private List<Double> arrayFaturamentoAnual;
	private List<Double> arrayFaturamentoMensal; 
    
	
	public class Relatorio {
		
		private double total;
		private int quantidade;
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
		public Relatorio() {
			super();
		}
		public Relatorio(double total, int quantidade) {
			super();
			this.total = total;
			this.quantidade = quantidade;
		}
		
	}//RELATORIOS
   
   public class Categorias {
	   
	private String nome;
	private double valor;
		public Categorias(String nome, double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}
	public Categorias() {
		super();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	   
   }//CATEGORIA
   
   public class Clientes {
	   
	   private String nome;
	   private double valor;
	   public Clientes(String nome, double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	
	   }
		public Clientes() {
			super();
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
	   
   }//CLIENTES

}
