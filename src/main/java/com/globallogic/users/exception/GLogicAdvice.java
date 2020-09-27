package com.globallogic.users.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GLogicAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GLogicException.class)
	public GLogicError handleException(GLogicException mex) {
		return new GLogicError(mex.getStatus(), mex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
		return response(ex, request, HttpStatus.BAD_REQUEST, errorMessage);
	}

	private ResponseEntity<Object> response(Exception ex, WebRequest request, HttpStatus status, String message) {
		return ResponseEntity.badRequest().body(new GLogicError(status, message));
	}
}