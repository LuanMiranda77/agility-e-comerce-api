package com.api.unidade;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
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

import com.api.domain.Pagamento;
import com.api.domain.Pagamento;
import com.api.domain.enuns.EstatusPagamento;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.TipoPagamento;
import com.api.repository.PagamentoRepository;
import com.api.resources.PagamentoResource;
import com.api.resources.PagamentoResource;
import com.api.services.PagamentoService;
import com.api.services.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = PagamentoResource.class)
@AutoConfigureMockMvc
public class TestePagamentoUnidade {
	
	static final String ENDPOINT_API = "/api/pagamento";
	
	@Autowired
	private PagamentoResource pagamentoResource;
	
	
	@MockBean
	private PagamentoService pagamentoService;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PagamentoRepository pagamentoRepository;
	
	private Pagamento pagamento;
	
	private MockHttpServletRequestBuilder request;

	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pagamentoResource);
		
		pagamento = new Pagamento();
		pagamento.setId(1L);
		pagamento.setNumeroDeParcelas(1);
		pagamento.setTipo(TipoPagamento.BOLETO);
		pagamento.setEstatus(EstatusPagamento.APROVADO);
		pagamento.setDataPagamento(new Date());
        pagamento.setDataVencimento(new Date());
		
	}
	
	
	@Test
	@DisplayName("Deve retornar um pagamento")
	public void deveRetornarSucesso_QuandoBuscarpagamento() throws Exception {
		
		 
	        BDDMockito.given(pagamentoService.findById(Mockito.anyLong())).willReturn(pagamento);

	        String json = new ObjectMapper().writeValueAsString(pagamento);
	        
	        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/1"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isOk());

	        Mockito.verify(pagamentoService, Mockito.times(1)).findById(pagamento.getId());
	}
	
	@Test
    @DisplayName("Deve salvar um pagamento")
    public void deveRetornarSucesso_Savepagamento() throws Exception {

      
        String json = new ObjectMapper().writeValueAsString(pagamento);

        BDDMockito.given(pagamentoService.save(Mockito.any(Pagamento.class))).willReturn(pagamento);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isOk());
    }
	
	@Test
    @DisplayName("Deve retornar erro ao tentar trazer um pagamento que  inexistente.")
    public void deveRetornarErro_pagamentoExiste() throws  Exception{

        String json = new ObjectMapper().writeValueAsString(pagamento);

        BDDMockito.given(pagamentoService.findById(Mockito.anyLong())).willReturn(pagamento);

        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/5"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect( status().isBadRequest() )
        ;
    }
	
	@Test
    @DisplayName("Deve retornar a listagem de pagamentos")
    public void deveRetornarLista() throws  Exception{

		ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
		lista.add(pagamento);
		
        BDDMockito.given(pagamentoResource.findAllList())
        .willReturn(lista);

        request = MockMvcRequestBuilders.get(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
        .perform( request )
        .andExpect( status().isOk() )
        ;
    }
	
	@Test
    @DisplayName("Deve da um erro ao set tipo null null")
    public void deveRetornarErro_savepagamento() throws  Exception{
		
		pagamento.setTipo(null);

		String json = new ObjectMapper().writeValueAsString(pagamento);

        BDDMockito.given(pagamentoService.save(Mockito.any(Pagamento.class))).willReturn(pagamento);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Teste pagamento sem status")
    public void deveRetornarErro_savePagamento() throws  Exception{
		
		pagamento.setEstatus(null);

		String json = new ObjectMapper().writeValueAsString(pagamento);

        BDDMockito.given(pagamentoService.save(Mockito.any(Pagamento.class))).willReturn(pagamento);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	
	

}
