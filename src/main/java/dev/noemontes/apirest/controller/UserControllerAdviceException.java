package dev.noemontes.apirest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.noemontes.apirest.exceptions.EmailDuplicatedException;
import dev.noemontes.apirest.exceptions.UserNotFoundException;

/**
 * @author noemontes
 * @since 1.0.0
 * Clase controladora de excepciones
 */
@RestControllerAdvice
public class UserControllerAdviceException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {UserNotFoundException.class})
	protected ResponseEntity<Object> userNotFoundHandler(UserNotFoundException ex, WebRequest request){
		final String responseBody = "El usuario no se ha encontrado en la base de datos";
		
		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = {EmailDuplicatedException.class})
	protected ResponseEntity<Object> emailDuplicatedHandler(EmailDuplicatedException ex, WebRequest request){
		final String responseBody = "El email ya se encuentra registrado en la base de datos";
		
		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT , request);
	}
}
