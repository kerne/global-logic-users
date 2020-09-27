package com.globallogic.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GLogicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2618881130545371201L;
	/**
	 * 
	 */
	private HttpStatus status;
	private String message;

	public GLogicException() {
	}

	public GLogicException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public GLogicException(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
