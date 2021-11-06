package com.api.unidade;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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

import com.api.domain.Usuario;
import com.api.domain.enuns.Roles;
import com.api.repository.UsuarioRepository;
import com.api.resources.UsuarioResource;
import com.api.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = UsuarioResource.class)
@AutoConfigureMockMvc
public class TesteUsuarioUnidade {
	
	static final String ENDPOINT_API = "/api/usuario";
	
	@Autowired
	private UsuarioResource userResource;
	
	
	@MockBean
	private UsuarioService userService;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsuarioRepository userRepository;
	
	private Usuario user;
	
	private MockHttpServletRequestBuilder request;

	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.userResource);
		
		user = new Usuario();
		user.setId(1L);
		user.setEmail("agilityecommerce@gmail.com");
		user.setPassword("123456");
		user.setLogin("admin");
		user.setNome("ADMIN");
		user.setRole(Roles.MASTER);
		
	}
	
	
	@Test
	@DisplayName("Deve retornar um user")
	public void deveRetornarSucesso_QuandoBuscaruser() throws Exception {
		
		 
	        BDDMockito.given(userService.findById(Mockito.anyLong())).willReturn(user);

	        String json = new ObjectMapper().writeValueAsString(user);
	        
	        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/1"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isOk());

	        Mockito.verify(userService, Mockito.times(1)).findById(user.getId());
	}
	
	@Test
    @DisplayName("Deve salvar um user")
    public void deveRetornarSucesso_Saveuser() throws Exception {

      
        String json = new ObjectMapper().writeValueAsString(user);

        BDDMockito.given(userService.save(Mockito.any(Usuario.class)) ).willReturn(user);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isCreated());
    }
	
	@Test
    @DisplayName("Deve retornar erro ao tentar trazer um user que  inexistente.")
    public void deveRetornarErro_userExiste() throws  Exception{

        String json = new ObjectMapper().writeValueAsString(user);

        BDDMockito.given(userService.findById(Mockito.anyLong())).willReturn(user);

        request = MockMvcRequestBuilders.get(ENDPOINT_API.concat("/5"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect( status().isBadRequest() )
        ;
    }
	
	@Test
    @DisplayName("Deve retornar a listagem de users")
    public void deveRetornarLista() throws  Exception{

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		lista.add(user);
		
        BDDMockito.given(userResource.findAllList())
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
    @DisplayName("Deve da um erro ao seta email para null")
    public void deveRetornarErro_saveuser() throws  Exception{
		
		user.setEmail(null);

		String json = new ObjectMapper().writeValueAsString(user);

        BDDMockito.given(userService.save(Mockito.any(Usuario.class))).willReturn(user);
       

        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve salvar um user sem senha")
    public void deveRetornarErro_saveUserSenha() throws Exception {

		user.setPassword(null);
		
        String json = new ObjectMapper().writeValueAsString(user);

        BDDMockito.given(userService.save(Mockito.any(Usuario.class)) ).willReturn(user);
       
        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	@Test
    @DisplayName("Deve salvar um user senha vazia")
    public void deveRetornarErro_saveUserSenhaVazia() throws Exception {

		user.setPassword("");
		
        String json = new ObjectMapper().writeValueAsString(user);

        BDDMockito.given(userService.save(Mockito.any(Usuario.class)) ).willReturn(user);
       
        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest());
    }
	
	

}
