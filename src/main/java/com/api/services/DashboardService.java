package com.api.services;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.api.domain.Pedido;
import com.api.services.filter.DashboardImpl;


public class DashboardService {
	
	@PersistenceContext
	private EntityManager manager;

	public DashboardImpl findDesthboard(Date dataInicio, Date dataFinal) {
		DashboardImpl dasthboard = new DashboardImpl();

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		
		Root<Pedido> root = criteria.from(Pedido.class);
		
		Predicate[] predicates = criarRestricoes(dasthboard ,builder, root );
		
		criteria.where(predicates);
		
		TypedQuery<Pedido> query = manager.createQuery(criteria);
		
//		adicionarPaginacao(query,page);
		
		
		
		return dasthboard;
	}
	
	public 
}
