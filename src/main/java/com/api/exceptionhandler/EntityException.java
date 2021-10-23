package com.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exceptionhandler.GeralExceptionHandler.Erro;
import com.api.resources.exception.LoginException;
import com.api.services.exceptions.EmailNotExistException;
import com.api.services.exceptions.ItemExistException;

@ControllerAdvice
public class EntityException {
	
	@Autowired
	private MessageSource menssageSourse;
	private String mensagemUsuario="";
	private String mensagemDesenvolvedor="";
	
	
	
	@ExceptionHandler(ItemExistException.class)
	public ResponseEntity<Object> handItemExist(ItemExistException ex){
		mensagemUsuario = menssageSourse.getMessage("item.existe", null, LocaleContextHolder.getLocale());
		mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);		
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Object> handItemExist(LoginException ex){
		mensagemUsuario = menssageSourse.getMessage("login.erro", null, LocaleContextHolder.getLocale());
		mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);		
	}
	
	@ExceptionHandler(EmailNotExistException.class)
	public ResponseEntity<Object> handItemExist(EmailNotExistException ex){
		mensagemUsuario = menssageSourse.getMessage("email.erro", null, LocaleContextHolder.getLocale());
		mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);		
	}
	
	

}
