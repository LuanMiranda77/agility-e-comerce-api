package com.api.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Pedido;
import com.api.domain.TipoCliente;
import com.api.domain.enuns.EstatusPedido;
import com.api.services.filter.Dashboard;
import com.api.services.filter.Relatorio;

@Service
public class DashboardService {

	@PersistenceContext
	private EntityManager manager;

	public Dashboard findDesthboard(Date dataInicio, Date dataFinal) {
		Dashboard dashboard = new Dashboard();

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		dashboard.setTicketMedio(tiketMedia(builder, dataInicio, dataFinal));
		dashboard.setTotalPedidoRealizado(pedidoRealizado(builder, dataInicio, dataFinal));
		dashboard.setTotalPedidoPago(pedidoPagos(builder, dataInicio, dataFinal));
		dashboard.setTotalPedidoCancelado(pedidoCancelados(builder, dataInicio, dataFinal));
		dashboard.setTotalGeralMes(dashboard.getTotalPedidoPago().getTotal());
		dashboard.setTotalVarejo(totalVarejo(builder, dataInicio, dataFinal));

		return dashboard;
	}

	public Relatorio tiketMedia(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal));
		
		relatorio = manager.createQuery(query).getSingleResult();
		if(relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}
		
		relatorio.setTotal(relatorio.getTotal().divide(new BigDecimal(relatorio.getQuantidade())));

		return relatorio;

	}

	public Relatorio pedidoRealizado(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataDeCriacao"), dataInicio, dataFinal));
		
		relatorio = manager.createQuery(query).getSingleResult();
		if(relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}
		

		return relatorio;

	}
	
	public Relatorio pedidoPagos(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO)));
		
		relatorio =  manager.createQuery(query).getSingleResult();
		if(relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}

		return relatorio;
	}
	
	public Relatorio pedidoCancelados(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), EstatusPedido.CANCELADO)));
		
		relatorio =  manager.createQuery(query).getSingleResult();
		
		if(relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}

		return relatorio;
	}
	
	public BigDecimal totalGeralMesPassado(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {
		
		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);
		
		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), EstatusPedido.CANCELADO)));
		
		total = (BigDecimal) manager.createQuery(query).getSingleResult();
		
		return total;
	}
	
	public BigDecimal totalVarejo(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO),
				builder.and(builder.equal(root.get("cliente").get("tipo"), TipoCliente.VAREJO))
				));
		
		total = (BigDecimal) manager.createQuery(query).getSingleResult();
		if(total == null) {
			total =  new BigDecimal(0);
		}
		
		return total;
	}
	
	
	

}
