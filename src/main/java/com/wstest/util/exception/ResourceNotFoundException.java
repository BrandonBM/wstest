package com.wstest.util.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public ResourceNotFoundException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
