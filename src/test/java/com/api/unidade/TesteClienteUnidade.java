package com.api.unidade;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
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

import com.api.domain.Cliente;
import com.api.domain.Endereco;
import com.api.domain.Usuario;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.TipoCliente;
import com.api.domain.enuns.UF;
import com.api.repository.ClienteRepository;
import com.api.resources.ClienteResource;
import com.api.services.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.lang.Arrays;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ClienteResource.class)
@AutoConfigureMockMvc
public class TesteClienteUnidade {
	
	static final String ENDPOINT_API = "/api/cliente";
	
	@Autowired
	private ClienteResource clienteResource;
	
	
	@MockBean
	private ClienteService clienteService;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	private Cliente cliente;
	
	private Usuario user;
	
	private MockHttpServletRequestBuilder request;

	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.clienteResource);
		
		user = new Usuario();
		user.setEmail("agilityecommerce@gmail.com");
		user.setPassword("123456");
		user.setLogin("admin");
		user.setNome("ADMIN");
		user.setRole(Roles.MASTER);
		
		cliente = new Cliente();
		cliente.setId(1L);
		cliente.setUsuario(user);
		cliente.setCpfCnpj("12345678910");
		cliente.setTipo(TipoCliente.ATACADO);
		
		Endereco endereco = new Endereco();
		endereco.setCep("58500-000");
		endereco.setLogradouro("rua da doidera");
		endereco.setNumero("32a");
		endereco.setCidade("São paulo");
		endereco.setBairro("Chibate");
		endereco.setUf(UF.PB);
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);
		
		cliente.setEnderecos(enderecos);
	}
	
	
	@Test
	@DisplayName("Deve retornar um cliente")
	public void deveRetornarSucesso_QuandoBuscarCliente() throws Exception {
		
		 
	        BDDMockito.given(clienteService.findById(Mockito.anyLong())).willReturn(cliente);

	        String json = new ObjectMapper().writeValueAsString(cliente);
	        
	        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/1"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isOk());

	        Mockito.verify(clienteService, Mockito.times(1)).findById(cliente.getId());
	}
	
	@Test
    @DisplayName("Deve salvar um cliente")
    public void deveRetornarSucesso_SaveCliente() throws Exception {

      
        String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class)) ).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isCreated());
        
    }
	
	@Test
    @DisplayName("Deve retornar erro ao tentar trazer um cliente que  inexistente.")
    public void deveRetornarErro_ClienteExiste() throws  Exception{

        String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.findById(Mockito.anyLong())).willReturn(cliente);

        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/5"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect( status().isBadRequest() )
        ;
    }
	
	@Test
    @DisplayName("Deve retornar a listagem de clientes")
    public void deveRetornarLista() throws  Exception{

		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
		
        BDDMockito.given(clienteResource.findAllList())
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
    @DisplayName("Deve da um erro ao seta tipo para null")
    public void deveRetornarErro_saveCliente() throws  Exception{
		
		cliente.setTipo(null);

		String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve da um erro ao seta cpf/cnpj para vazio")
    public void deveRetornarErro_saveClienteCPF() throws  Exception{
		
		cliente.setCpfCnpj("");

		String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve da um erro ao seta cpf/cnpj para Null")
    public void deveRetornarErro_saveClienteCPFNull() throws  Exception{
		
		cliente.setCpfCnpj(null);

		String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve da um erro ao seta cpf/cnpj para menor que 11")
    public void deveRetornarErro_saveClienteCPFErro() throws  Exception{
		
		cliente.setCpfCnpj("5458");

		String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve da um erro ao seta user para null")
    public void deveRetornarErro_saveClienteUserNull() throws  Exception{
		
		cliente.setUsuario(null);

		String json = new ObjectMapper().writeValueAsString(cliente);

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	
	
	

}
