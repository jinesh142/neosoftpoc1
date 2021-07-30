package com.neosoft.poc1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorType> handleUsernotFound(UserNotFoundException e) { 
		return new ResponseEntity<>(new ErrorType(e.getMessage(), "505", "No User Found For The ID", e.getClass().getSimpleName()), HttpStatus.NOT_FOUND);
	}
}
