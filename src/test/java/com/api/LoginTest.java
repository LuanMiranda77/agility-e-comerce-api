package com.api;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
import com.api.domain.enuns.EstatusUsuario;
import com.api.domain.enuns.Roles;
import com.api.repository.UsuarioRepository;
import com.api.resources.UsuarioResource;
import com.api.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = UsuarioResource.class)
@AutoConfigureMockMvc
public class LoginTest {

	static final String ENDPOINT_API = "/api/usuario";

	@Autowired
	private UsuarioResource usuarioResource;

	@MockBean
	private UsuarioService usuarioService;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	
	private MockHttpServletRequestBuilder request;
	
	@BeforeEach
	public void setup() throws Exception {
		standaloneSetup(this.usuarioResource);
		
		Usuario user1 = new Usuario();
		user1.setId(1l);
		user1.setEmail("agilityecommerce@gmail.com");
		user1.setLogin("adimn");
		user1.setPassword("123456");
		user1.setLogin("admin");
		user1.setNome("ADMIN");
		user1.setDataCriacao(new Date());
		user1.setDataAtualizacao(new Date());
		user1.setRole(Roles.MASTER);
		user1.setStatus(EstatusUsuario.ATIVO);
		
		BDDMockito.given(usuarioService.save(usuario)).willReturn(usuario);
		
		String json = new ObjectMapper().writeValueAsString(usuario);
        
        request = MockMvcRequestBuilders.post(ENDPOINT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
        	.andExpect(status().isCreated());
		}

	
	@Test
	@DisplayName("Retornar login inválido")
	public void deveRetornarSucesso_LoginInvalido() throws Exception {
		
		
			 
		 BDDMockito.given(usuarioService.login(usuario)).willReturn(usuario);
		 
	        String json = new ObjectMapper().writeValueAsString(usuario);
	        
	        request = MockMvcRequestBuilders.post(ENDPOINT_API.concat("/login"))
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json);

	        mvc.perform(request)
	        	.andExpect(status().isAccepted());
	}

//	@Test
//	@DisplayName("Deve salvar um usuario")
//	public void salvarUsuario() throws Exception {
//
//		String json = new ObjectMapper().writeValueAsString(usuario);
//
//		BDDMockito.given(usuarioService.save(Mockito.any(Usuario.class))).willReturn(usuario);
//		BDDMockito.given(usuarioService.findById(1L)).willReturn(usuario);
//
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_API)
//				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);
//
//		mvc.perform(request).andExpect(status().isOk());
////                .andExpect( content().string("1") );
//	}
//	
//	@Test
//	@DisplayName("Salvar pedido sem Usuario")
//	public void deveRetornarErro_SalvarSemUsuario() throws Exception {
//		
//		usuario.setLogin(null);
//		
//		String json = new ObjectMapper().writeValueAsString(usuario);
//
//		BDDMockito.given(usuarioService.save(Mockito.any(Usuario.class))).willReturn(usuario);
//
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//				.post(ENDPOINT_API)
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json);
//
//		mvc.perform(request).andExpect(status().isBadRequest());
//
//	}
//	
//	@Test
//	@DisplayName("Salvar pedido sem pagamento")
//	public void deveRetornarErro_SalvarPedidoSemUsuario() throws Exception {
//		
//		usuario.setLogin(null);
//		
//		String json = new ObjectMapper().writeValueAsString(usuario);
//
//		BDDMockito.given(usuarioService.save(Mockito.any(Usuario.class))).willReturn(usuario);
//
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//				.post(ENDPOINT_API)
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json);
//
//		mvc.perform(request).andExpect(status().isBadRequest());
//
//	}
//	
//	@Test
//	@DisplayName("Salvar pedido sem data de fechamento")
//	public void deveRetornarErro_SalvarPedidoSemDataDeFechamento() throws Exception {
//		
//		usuario.setLogin(null);
//		
//		String json = new ObjectMapper().writeValueAsString(usuario);
//
//		BDDMockito.given(usuarioService.save(Mockito.any(Usuario.class))).willReturn(usuario);
//
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//				.post(ENDPOINT_API)
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json);
//
//		mvc.perform(request).andExpect(status().isBadRequest());
//
//	}
//	
//	@Test
//	@DisplayName("Salvar pedido sem data de criação")
//	public void deveRetornarErro_SalvarPedidoSemDataDeCriacao() throws Exception {
//		
//		usuario.setLogin(null);;
//		
//		String json = new ObjectMapper().writeValueAsString(usuario);
//
//		BDDMockito.given(usuarioService.save(Mockito.any(Usuario.class))).willReturn(usuario);
//
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//				.post(ENDPOINT_API)
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json);
//
//		mvc.perform(request).andExpect(status().isBadRequest());
//
//	}
}