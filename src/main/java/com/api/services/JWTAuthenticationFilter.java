package com.api.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		/*
		 * Authentication authentication = TokenAuthenticationService
		 * .getAuthentication((HttpServletRequest) request);
		 * 
		 * SecurityContextHolder.getContext().setAuthentication(authentication);
		 * filterChain.doFilter(request, response);
		 */
	}

}
