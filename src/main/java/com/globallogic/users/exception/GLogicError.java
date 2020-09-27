package com.globallogic.users.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GLogicError {
	private HttpStatus errorCode;
	private String message;

	public GLogicError(HttpStatus errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

}

