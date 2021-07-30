package com.neosoft.poc1.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
 
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ErrorType {

	private String message;
	private String code;
	private String error;
	private String errorType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();
	
	public ErrorType(String message, String code, String error, String errorType ) {
		super();
		this.message = message;
		this.code = code;
		this.error = error;
		this.errorType = errorType; 
	}
	
}
