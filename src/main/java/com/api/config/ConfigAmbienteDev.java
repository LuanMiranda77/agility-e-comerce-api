package com.api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.domain.Categoria;
import com.api.domain.Produto;
import com.api.domain.Usuario;
import com.api.repository.CategoriaRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.UserRepository;

@Configuration
@Profile("dev")
public class ConfigAmbienteDev {
	
	@Transient
	private int quantDeLoop=1000;
	
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	
	@Bean
	public void inserindoBanco() {

//		Lista de para teste
		List<Produto>produtos = new ArrayList<>();
		List<Usuario>users = new ArrayList<>();
		List<Categoria>categorias = new ArrayList<>();
		
// 		setando dados dos usuarios		
		Usuario user;
		Categoria categoria;
		Produto produto;
		for(int i=0;i<quantDeLoop;i++ ) {
			user = new Usuario("test"+i+"@gmail.com","123456");
			users.add(user);
			
			categoria = new Categoria("categoria-test"+i);
			categorias.add(categoria);
			
			BigDecimal b = new BigDecimal(1.8);
			produto = new Produto("154587878"+i,"item-"+i,b,b,1,"dercição"+i);
			produtos.add(produto);
		}
		
		
// 		salvando dados			
		userRepository.saveAll(users);
		categoriaRepository.saveAll(categorias);
		produtoRepository.saveAll(produtos);
		
	}

}
