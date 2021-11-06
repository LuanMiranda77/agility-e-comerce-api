package com.api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@EnableWebSecurity
@Profile("prod")
public class SecurityConfigJWT {
	/*
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity.csrf().disable().authorizeRequests()
	 * .antMatchers("/home").permitAll() .antMatchers(HttpMethod.POST,
	 * "/tokken").permitAll() .anyRequest().authenticated() .and() .cors() .and()
	 * 
	 * // filtra requisições de login .addFilterBefore(new JWTLoginFilter("/tokken",
	 * authenticationManager()), UsernamePasswordAuthenticationFilter.class)
	 * 
	 * // filtra outras requisições para verificar a presença do JWT no header
	 * .addFilterBefore(new JWTAuthenticationFilter(),
	 * UsernamePasswordAuthenticationFilter.class); }
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // cria uma conta default
	 * 
	 * auth.inMemoryAuthentication() .withUser("admin")
	 * .password("{noop}Ads%$#@!Ads") .roles("ROEL_ADMIN"); }
	 */

}
