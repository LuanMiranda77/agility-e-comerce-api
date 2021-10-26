package com.api.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.FetchProfile.Item;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.ItemPedido;
import com.api.domain.Pedido;
import com.api.domain.enuns.EstatusPedido;
import com.api.domain.enuns.TipoCliente;
import com.api.domain.enuns.TipoPagamento;
import com.api.domain.enuns.TipoPeriodo;
import com.api.services.filter.Categorias;
import com.api.services.filter.Clientes;
import com.api.services.filter.Dashboard;
import com.api.services.filter.Relatorio;
import com.api.utils.UtilsAnoData;
import com.api.utils.UtilsHorasData;
import com.api.utils.UtilsMesData;

@Service
public class DashboardService {

	@PersistenceContext
	private EntityManager manager;

	public Dashboard findDesthboard(Date dataInicio, Date dataFinal) {
		Dashboard dashboard = new Dashboard();

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		dashboard.setTicketMedio(tiketMedia(builder, dataInicio, dataFinal));
		dashboard.setTotalPedidoRealizado(findPedidoRealizado(builder, dataInicio, dataFinal));
		dashboard.setTotalPedidoPago(findPedidosByTipoPedido(builder, dataInicio, dataFinal, EstatusPedido.FINALIZADO));
		dashboard.setTotalPedidoCancelado(findPedidosByTipoPedido(builder, dataInicio, dataFinal, EstatusPedido.CANCELADO));
		dashboard.setTotalGeralMes(dashboard.getTotalPedidoPago().getTotal());
		dashboard.setTotalGeralMesPassado(totalGeralMesPassado(builder, dataInicio, dataFinal));
		dashboard.setTotalVarejo(findTotalPedidoByTipoCliente(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.VAREJO));
		dashboard.setTotalVarejoCredito(findTotalPedidoByTipoClienteByTipoPagamento(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.VAREJO, TipoPagamento.CARTAOCREDITO));
		dashboard.setTotalVarejoBoleto(findTotalPedidoByTipoClienteByTipoPagamento(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.VAREJO, TipoPagamento.BOLETO));
		dashboard.setTotalAtacado(findTotalPedidoByTipoCliente(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.ATACADO));
		dashboard.setTotalAtacadoCredito(findTotalPedidoByTipoClienteByTipoPagamento(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.ATACADO, TipoPagamento.CARTAOCREDITO));
		dashboard.setTotalAtacadoBoleto(findTotalPedidoByTipoClienteByTipoPagamento(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO, TipoCliente.ATACADO, TipoPagamento.BOLETO));
		dashboard.setArrayVendaPorHoras(findTotalPedidoByPeriodo(builder, dataInicio, dataFinal,TipoPeriodo.HORA));
		dashboard.setArrayVendaPorCategorias(findPedidosByTopDezCategoria(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO));
		dashboard.setArrayFormasPagamentos(findTotalPedidoByTipoPagamentos(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO));
		dashboard.setArrayTopClientes(findPedidosByTopDezCliente(builder, dataInicio, dataFinal,EstatusPedido.FINALIZADO));
		dashboard.setArrayFaturamentoAnual(findTotalPedidoByPeriodo(builder, dataInicio, dataFinal,TipoPeriodo.ANO));
		dashboard.setArrayFaturamentoMensal(findTotalPedidoByPeriodo(builder, dataInicio, dataFinal,TipoPeriodo.MES));

		return dashboard;
	}

	public Relatorio tiketMedia(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal));

		relatorio = manager.createQuery(query).getSingleResult();
		if (relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}

		relatorio.setTotal(relatorio.getTotal().divide(new BigDecimal(relatorio.getQuantidade())));

		return relatorio;

	}

	public Relatorio findPedidoRealizado(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataDeCriacao"), dataInicio, dataFinal));

		relatorio = manager.createQuery(query).getSingleResult();
		if (relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}

		return relatorio;

	}

	public Relatorio findPedidosByTipoPedido(CriteriaBuilder builder, Date dataInicio, Date dataFinal,
			EstatusPedido estatusPedido) {

		Relatorio relatorio = new Relatorio();
		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPedido)));

		relatorio = manager.createQuery(query).getSingleResult();
		if (relatorio.getTotal() == null) {
			relatorio.setTotal(new BigDecimal(0));
		}

		return relatorio;
	}

//	public Relatorio pedidoCancelados(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {
//
//		Relatorio relatorio = new Relatorio();
//		CriteriaQuery<Relatorio> query = builder.createQuery(Relatorio.class);
//		Root<Pedido> root = query.from(Pedido.class);
//
//		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), builder.count(root));
//		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
//				builder.and(builder.equal(root.get("estatus"), EstatusPedido.CANCELADO)));
//		
//		relatorio =  manager.createQuery(query).getSingleResult();
//		
//		if(relatorio.getTotal() == null) {
//			relatorio.setTotal(new BigDecimal(0));
//		}
//
//		return relatorio;
//	}

	public BigDecimal totalGeralMesPassado(CriteriaBuilder builder, Date dataInicio, Date dataFinal) {

		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);

		Date dtInicio = UtilsMesData.subtrair(dataInicio, 1);

		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dtInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO)));

		total = (BigDecimal) manager.createQuery(query).getSingleResult();

		if (total == null) {
			total = new BigDecimal(0);
		}

		return total;
	}

	public BigDecimal findTotalPedidoByTipoCliente(CriteriaBuilder builder, Date dataInicio, Date dataFinal,
			EstatusPedido estatusPeido, TipoCliente tipoCliente) {

		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPeido),
						builder.and(builder.equal(root.get("cliente").get("tipo"), tipoCliente))));

		total = (BigDecimal) manager.createQuery(query).getSingleResult();

		if (total == null) {
			total = new BigDecimal(0);
		}

		return total;
	}

	public BigDecimal findTotalPedidoByTipoClienteByTipoPagamento(CriteriaBuilder builder, Date dataInicio,
			Date dataFinal, EstatusPedido estatusPeido, TipoCliente tipoCliente, TipoPagamento tipoPagamento) {

		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPeido),
						builder.and(builder.equal(root.get("cliente").get("tipo"), tipoCliente)),
						builder.and(builder.equal(root.get("pagamento").get("tipo"), tipoPagamento))));

		total = (BigDecimal) manager.createQuery(query).getSingleResult();
		if (total == null) {
			total = new BigDecimal(0);
		}

		return total;
	}

	public List<Number> findTotalPedidoByPeriodo(CriteriaBuilder builder, Date dataInicio, Date dataFinal, TipoPeriodo tipoPeriodo) {

		BigDecimal total = new BigDecimal(0);
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);
		List<Number> listaResul =  null;

		if (tipoPeriodo.equals(TipoPeriodo.HORA)) {
			
			listaResul = new ArrayList<Number>();
			
			for (int i = 1; i <= 12; i++) {
				Date dtInicio = UtilsHorasData.subtrair(dataInicio, i);

				query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
				query.where(builder.between(root.get("dataFechamento"), dtInicio, dataFinal),
						builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO)));

				total = (BigDecimal) manager.createQuery(query).getSingleResult();

				if (total == null) {
					total = new BigDecimal(0);
				}

				listaResul.add(total);
			}
		}else if (tipoPeriodo.equals(TipoPeriodo.MES)) {
			
			listaResul = new ArrayList<Number>();
			
			for (int i = 1; i <= 12; i++) {

				Date dtInicio = UtilsMesData.subtrair(dataInicio, i);

				query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
				query.where(builder.between(root.get("dataFechamento"), dtInicio, dataFinal),
						builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO)));

				total = (BigDecimal) manager.createQuery(query).getSingleResult();

				if (total == null) {
					total = new BigDecimal(0);
				}

				listaResul.add(total);
			}

		}else if (tipoPeriodo.equals(TipoPeriodo.ANO)) {
			
			listaResul = new ArrayList<Number>();

			for (int i = 1; i <= 12; i++) {

				Date dtInicio = UtilsAnoData.subtrair(dataInicio, i);

				query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
				query.where(builder.between(root.get("dataFechamento"), dtInicio, dataFinal),
						builder.and(builder.equal(root.get("estatus"), EstatusPedido.FINALIZADO)));

				total = (BigDecimal) manager.createQuery(query).getSingleResult();

				if (total == null) {
					total = new BigDecimal(0);
				}

				listaResul.add(total);
			}

		}
		
		return listaResul;
	}
	
	public List<Categorias> findPedidosByTopDezCategoria(CriteriaBuilder builder, Date dataInicio, Date dataFinal,
			EstatusPedido estatusPedido) {

		Categorias categoria = new Categorias();
		
		List<Categorias> categorias = new ArrayList<>();
		CriteriaQuery<Categorias> query = builder.createQuery(Categorias.class);
		Root<ItemPedido> root = query.from(ItemPedido.class);

		query.multiselect(builder.sum(root.<Integer>get("quantidadeVendida")), root.get("produto").get("titulo"));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPedido))
				).groupBy(root.get("produto").get("titulo"));
		query.orderBy(builder.asc(builder.sum(root.<Integer>get("quantidadeVendida"))));

		categorias = manager.createQuery(query).setMaxResults(10).getResultList();
		
		if (categoria.getValor() == null) {
			categoria.setValor(new BigDecimal(0));
		}

		return categorias;
	}
	
	public List<Number> findTotalPedidoByTipoPagamentos(CriteriaBuilder builder, Date dataInicio,
			Date dataFinal, EstatusPedido estatusPeido) {

		BigDecimal total = new BigDecimal(0);
		List<Number> listaResul =  new ArrayList<Number>();
		CriteriaQuery<Number> query = builder.createQuery(Number.class);
		Root<Pedido> root = query.from(Pedido.class);
		
		

		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPeido),
						builder.and(builder.equal(root.get("pagamento").get("tipo"), TipoPagamento.BOLETO))));

		total = (BigDecimal) manager.createQuery(query).getSingleResult();
		
		if (total == null) {
			total = new BigDecimal(0);
			listaResul.add(total);
		}
		
		query.select(builder.sum(root.<BigDecimal>get("valorTotal")));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPeido),
						builder.and(builder.equal(root.get("pagamento").get("tipo"), TipoPagamento.CARTAOCREDITO))));

		total = (BigDecimal) manager.createQuery(query).getSingleResult();
		
		if (total == null) {
			total = new BigDecimal(0);
			listaResul.add(total);
		}
		
		

		return listaResul;
	}
	
	public List<Clientes> findPedidosByTopDezCliente(CriteriaBuilder builder, Date dataInicio, Date dataFinal,
			EstatusPedido estatusPedido) {

		Categorias categoria = new Categorias();
		
		List<Clientes> clientes = new ArrayList<>();
		CriteriaQuery<Clientes> query = builder.createQuery(Clientes.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.multiselect(builder.sum(root.<BigDecimal>get("valorTotal")), root.get("cliente").get("ususario").get("nome"));
		query.where(builder.between(root.get("dataFechamento"), dataInicio, dataFinal),
				builder.and(builder.equal(root.get("estatus"), estatusPedido)));
		
		query.orderBy(builder.asc(builder.sum(root.<BigDecimal>get("valorTotal"))));

		clientes = manager.createQuery(query).setMaxResults(10).getResultList();
		
		if (categoria.getValor() == null) {
			categoria.setValor(new BigDecimal(0));
		}

		return clientes;
	}


}
