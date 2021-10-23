package com.api.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Pedido;
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
		

		return dashboard;
	}
	
	public Relatorio tiketMedia(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {
		
		Relatorio ticketMedio = new Relatorio();
		CriteriaQuery<Relatorio> query  = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query .from(Pedido.class);
		
		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal"), builder.count(root.<Integer>get("id"))));
		query.where(builder.between(root.get("dataFechamento"),dataInicio, dataFinal));
		ticketMedio =  manager.createQuery(query).getSingleResult();
		
		return ticketMedio;
		
	}

}
