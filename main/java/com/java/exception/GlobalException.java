package com.java.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = InsufficentBalanceException.class)
	public ResponseEntity<ErrorMessage> handleException(InsufficentBalanceException exception, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), LocalDate.now(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TransactionDetailsNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleException(TransactionDetailsNotFoundException exception, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), LocalDate.now(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}

}
