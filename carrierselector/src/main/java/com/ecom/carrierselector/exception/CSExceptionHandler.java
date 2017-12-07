package com.ecom.carrierselector.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CSExceptionHandler {
	@ExceptionHandler(value = { CSException.class })
	public ResponseEntity<Map<String, Object>> csException(CSException ex, WebRequest req) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", ex.getErrorCode());
		errorResponse.put("message", ex.getMessage());
		return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.valueOf(ex.getErrorCode()));
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> unknownException(Exception ex, WebRequest req) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.put("message", ex.getMessage());
		return errorResponse;
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
			HttpRequestMethodNotSupportedException.class, NoHandlerFoundException.class })
	public ResponseEntity<Map<String, Object>> badRequest(HttpServletRequest req, Exception ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", HttpStatus.NOT_FOUND.value());
		errorResponse.put("message", ex.getMessage());
		return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
