package com.api;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.domain.Cliente;
import com.api.domain.Endereco;
import com.api.domain.EnderecoDeEntrega;
import com.api.domain.Pagamento;
import com.api.domain.Pedido;
import com.api.domain.Usuario;
import com.api.domain.enuns.EstatusPedido;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.TipoCliente;
import com.api.domain.enuns.UF;
import com.api.repository.PedidoRepository;
import com.api.resources.PedidoResource;
import com.api.services.DashboardService;
import com.api.services.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = PedidoResource.class)
@AutoConfigureMockMvc
public class PedidoTest {

	static final String ENDPOINT_API = "/api/pedido";

	@Autowired
	private PedidoResource pedidoResource;

	@MockBean
	private PedidoService pedidoService;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PedidoRepository pedidoRepository;
	
	@MockBean
	private DashboardService dashboardService;
	
	private Pedido pedido;
	
	private MockHttpServletRequestBuilder request;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pedidoResource);
		
		Usuario user1 = new Usuario();
		user1.setEmail("agilityecommerce@gmail.com");
		user1.setPassword("123456");
		user1.setLogin("admin");
		user1.setNome("ADMIN");
		user1.setRole(Roles.MASTER);

		Cliente cliente = new Cliente();
		cliente.setUsuario(user1);
		cliente.setCpfCnpj("12345678910");
		cliente.setTipo(TipoCliente.ATACADO);
		cliente.setEnderecos(null);

		Endereco endereco = new Endereco();
		endereco.setCep("58500-000");
		endereco.setLogradouro("rua da doidera");
		endereco.setNumero("32a");
		endereco.setCidade("São paulo");
		endereco.setBairro("Chibate");
		endereco.setUf(UF.PB);
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);
		
		EnderecoDeEntrega enderecoDeEntrega = new EnderecoDeEntrega();
		enderecoDeEntrega.setCep("58500-000");
		enderecoDeEntrega.setLogradouro("rua da doidera");
		enderecoDeEntrega.setNumero("32a");
		enderecoDeEntrega.setCidade("São paulo");
		enderecoDeEntrega.setBairro("Chibate");
		enderecoDeEntrega.setUf(UF.PB);
		
		
		pedido = new Pedido();
		pedido.setId(1l);
		pedido.setCliente(cliente);
		pedido.setDataCriacao();
		pedido.setDataFechamento(new Date());
		pedido.setEnderecoDeEntrega(enderecoDeEntrega);
		pedido.setEstatus(EstatusPedido.PENDENTE);
		pedido.setPagamento(new Pagamento());
		pedido.setValorDesconto(new BigDecimal(10));
		pedido.setValorFrete(new BigDecimal(10));
		pedido.setValorTotal(new BigDecimal(10));
		
		}

	
	@Test
	@DisplayName("Retornar um pedido")
	public void deveRetornarSucesso_Pedido() throws Exception {
		
		 BDDMockito.given(pedidoService.findById(Mockito.anyLong())).willReturn(pedido);

	        String json = new ObjectMapper().writeValueAsString(pedido);
	        
	        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/1"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isOk());

	        Mockito.verify(pedidoService, Mockito.times(1)).findById(pedido.getId());
	}

	@Test
	@DisplayName("Deve salvar um pedido")
	public void salvarPedido() throws Exception {

		String json = new ObjectMapper().writeValueAsString(pedido);

		BDDMockito.given(pedidoService.save(Mockito.any(Pedido.class))).willReturn(pedido);
		BDDMockito.given(pedidoService.findById(1L)).willReturn(pedido);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isOk());
//                .andExpect( content().string("1") );
	}
	
	@Test
	@DisplayName("Salvar pedido sem Cliente")
	public void deveRetornarErro_SalvarPedidoSemCliente() throws Exception {
		
		pedido.setCliente(null);
		
		String json = new ObjectMapper().writeValueAsString(pedido);

		BDDMockito.given(pedidoService.save(Mockito.any(Pedido.class))).willReturn(pedido);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}
	
	@Test
	@DisplayName("Salvar pedido sem pagamento")
	public void deveRetornarErro_SalvarPedidoSemPagamento() throws Exception {
		
		pedido.setDataFechamento(null);
		
		String json = new ObjectMapper().writeValueAsString(pedido);

		BDDMockito.given(pedidoService.save(Mockito.any(Pedido.class))).willReturn(pedido);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}
	
	@Test
	@DisplayName("Salvar pedido sem data de fechamento")
	public void deveRetornarErro_SalvarPedidoSemDataDeFechamento() throws Exception {
		
		pedido.setDataFechamento(null);
		
		String json = new ObjectMapper().writeValueAsString(pedido);

		BDDMockito.given(pedidoService.save(Mockito.any(Pedido.class))).willReturn(pedido);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}
	
	@Test
	@DisplayName("Salvar pedido sem data de criação")
	public void deveRetornarErro_SalvarPedidoSemDataDeCriacao() throws Exception {
		
		pedido.setDataDeCriacao(null);;
		
		String json = new ObjectMapper().writeValueAsString(pedido);

		BDDMockito.given(pedidoService.save(Mockito.any(Pedido.class))).willReturn(pedido);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}
	
	
}