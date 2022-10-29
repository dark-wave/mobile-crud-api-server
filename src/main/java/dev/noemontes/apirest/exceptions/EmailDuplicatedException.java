package dev.noemontes.apirest.exceptions;

public class EmailDuplicatedException extends RuntimeException{
	
	public EmailDuplicatedException() {
		super();
	}
	
	public EmailDuplicatedException(String message) {
		super(message);
	}
}
