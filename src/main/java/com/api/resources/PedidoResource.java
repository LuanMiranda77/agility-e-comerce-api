package com.api.resources;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Pedido;
import com.api.repository.PedidoRepository;
import com.api.services.DashboardService;
import com.api.services.PedidoService;
import com.api.services.filter.Dashboard;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource implements ResourceBase<Pedido, Long>{

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private DashboardService dashboardService;
	

//	Salvar Pedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pedido> save(@Valid Pedido pEntity, HttpServletResponse response) {
		Pedido pedidoSalvo = pedidoService.save(pEntity);
		return ResponseEntity.ok(pedidoSalvo);
	}

//	Atualizar pedido
	@PutMapping
	public ResponseEntity<Pedido> update(@Valid Long pID, Pedido pEntity) {
		Pedido pedidoSalvo = pedidoService.update(pEntity);
		return ResponseEntity.ok(pedidoSalvo);
	}

//	Deletar Pedido
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		pedidoRepository.deleteById(pID);
	}

//	Filtro por ID
	public ResponseEntity<Pedido> findById(Long pID) {
		return  ResponseEntity.ok(pedidoRepository.findById(pID).get());
	}


//	Listar Pedido
	@GetMapping
	public List<Pedido> findAllList() {
		return pedidoRepository.findAll();
	}


	public Page<Pedido> findAllPage(Pedido pFilter, Pageable pPage) {
		return null;
	}
	
	@PostMapping("/dashboard")
	public Dashboard findDashboard(@RequestBody Pedido pedido) {
		return dashboardService.findDesthboard(pedido.getDataDeCriacao(), pedido.getDataFechamento());
	}

	
	
	
}
