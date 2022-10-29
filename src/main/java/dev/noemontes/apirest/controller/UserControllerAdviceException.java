package dev.noemontes.apirest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.noemontes.apirest.exceptions.EmailDuplicatedException;

@RestControllerAdvice
public class UserControllerAdviceException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {EmailDuplicatedException.class})
	public ResponseEntity<>
}
