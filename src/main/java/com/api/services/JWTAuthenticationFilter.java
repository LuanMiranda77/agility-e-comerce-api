package com.api.services;

import java.io.IOException;
import java.security.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.api.services.exceptions.TokenExpiradoException;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		try {
		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest) request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
		    System.out.println(" Token expirado "+e.getMessage());
//		    throw new TokenExpiradoException();
		}  catch(Exception e){
		    System.out.println(" Some other exception in JWT parsing ");
		}
	}

}
