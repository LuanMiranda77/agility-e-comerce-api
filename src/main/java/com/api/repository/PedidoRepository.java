package com.api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	public List<Pedido> findByDataDeCriacaoBetween(Date dtIni, Date dtFin );

}
