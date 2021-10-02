package com.api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.domain.Categoria;
import com.api.domain.ImagemProduto;
import com.api.domain.Produto;
import com.api.domain.Usuario;
import com.api.repository.CategoriaRepository;
import com.api.repository.ImagemProdutoRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.UserRepository;

@Configuration
@Profile("dev")
public class ConfigAmbienteDev {
	
	@Transient
	private int quantDeLoop=10;
	
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ImagemProdutoRepository imagemProdutoRepository;
	
	
	
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
		
		Random gerador = new Random();
		
		user = new Usuario("test@gmail.com", "123456");
//		new BCryptPasswordEncoder().encode("123456")
		users.add(user);
		
		for(int i=0;i<quantDeLoop;i++ ) {
			categoria = new Categoria(i+1l,"categoria-test"+i);
			categorias.add(categoria);
			
			BigDecimal b = new BigDecimal(1.8);
			produto = new Produto();
			produto.setCodigoBarras("154587878"+i+1);
			produto.setNome("item-"+i+1);
			produto.setPrecoAtacado(b);
			produto.setPrecoVarejo(b);
			produto.setQuantidade(i);
			produto.setDescricao("Descrição"+i+1);
			produto.setEstrelas(gerador.nextInt(5));
			produto.getImagens().add(new ImagemProduto(null,"https://a-static.mlcdn.com.br/1500x1500/relogio-binbond-de-luxo-moda-esporte-ouro-relogios-pulso-relogio-casual-cronografo-sem-genero/classicosrelogioseacessorios/crabinbondprata/b2ba92809443451bf0d1e16d28003a1c.jpg"));
			produto.getImagens().add(new ImagemProduto(null,"https://images-americanas.b2w.io/produtos/01/00/img/79597/8/79597872_1GG.jpg"));
			produto.getImagens().add(new ImagemProduto(null,"https://d3ugyf2ht6aenh.cloudfront.net/stores/386/761/products/dsc08819-edit-gold11-07a7861b1e1cf702ec16186076742287-480-0.jpg"));
			produto.getImagens().add(new ImagemProduto(null,"https://m.media-amazon.com/images/I/61QHCYJIDsL._AC_SX522_.jpg"));
			produto.getImagens().add(new ImagemProduto(null,"https://images-soubarato.b2w.io/produtos/3029006799/imagens/2020-moda-masculina-minimalista-ultra-fino-relogios-simples-aco-homens-de-negocios-inoxidavel-mesh-belt-relogio-de-quartzo-relogio-masculino/3029006799_1_large.jpg"));
			produto.getCategorias().add(categoria);
			produtos.add(produto);
			produto.setId(i+1l);
		}
		
// 		salvando dados			
		categoriaRepository.saveAll(categorias);
		userRepository.saveAll(users);
		produtoRepository.saveAll(produtos);

		
		
	}

}
