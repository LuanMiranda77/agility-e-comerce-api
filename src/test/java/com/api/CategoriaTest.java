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

import com.api.domain.Categoria;
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
import com.api.repository.CategoriaRepository;
import com.api.repository.PedidoRepository;
import com.api.resources.CategoriaResource;
import com.api.resources.PedidoResource;
import com.api.services.CategoriaService;
import com.api.services.DashboardService;
import com.api.services.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CategoriaResource.class)
@AutoConfigureMockMvc
public class CategoriaTest {

	static final String ENDPOINT_API = "/api/categoria";

	@Autowired
	private CategoriaResource categoriaResource;

	@MockBean
	private CategoriaService categoriaService;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoriaRepository categoriaRepository;
	
	private Categoria categoria;
	
	private MockHttpServletRequestBuilder request;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.categoriaResource);
		
		categoria = new Categoria(1l,"categoria-test");
		
		}

	
	@Test
	@DisplayName("Retornar uma Categoria Erro")
	public void deveRetornarErro_CategoriaErro() throws Exception {
		
		 BDDMockito.given(categoriaService.findById(Mockito.anyLong())).willReturn(categoria);

	        String json = new ObjectMapper().writeValueAsString(categoria);
	        
	        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/5"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isBadRequest());

	        Mockito.verify(categoriaService, Mockito.times(1)).findById(categoria.getId());
	}

	@Test
	@DisplayName("Deve salvar Categoria")
	public void salvarCategoria() throws Exception {

		String json = new ObjectMapper().writeValueAsString(categoria);

		BDDMockito.given(categoriaService.save(Mockito.any(Categoria.class))).willReturn(categoria);
		BDDMockito.given(categoriaService.findById(1L)).willReturn(categoria);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isCreated());
//                .andExpect( content().string("1") );
	}

	@Test
	@DisplayName("Deve retornar erro ao salvar categoria vazia")
	public void categoriaVazia() throws Exception {
		
		categoria.setNome(" ");
		
		String json = new ObjectMapper().writeValueAsString(categoria);

		BDDMockito.given(categoriaService.save(Mockito.any(Categoria.class))).willReturn(categoria);
		BDDMockito.given(categoriaService.findById(1L)).willReturn(categoria);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest());
//                .andExpect( content().string("1") );
	}
	
	@Test
	@DisplayName("Deve retornar erro ao salvar categoria nula")
	public void categoriaNula() throws Exception {
		
		categoria.setNome(null);
		
		String json = new ObjectMapper().writeValueAsString(categoria);

		BDDMockito.given(categoriaService.save(Mockito.any(Categoria.class))).willReturn(categoria);
		BDDMockito.given(categoriaService.findById(1L)).willReturn(categoria);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_API)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest());
//                .andExpect( content().string("1") );
	}
	
}