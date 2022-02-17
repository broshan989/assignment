package com.app.exceptionhandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.exceptionhandler.custom_exception.InvalidEmpoyeeException;
import com.app.exceptionhandler.error_response.ErrorResponse;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidEmpoyeeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidEmployeeException(InvalidEmpoyeeException e) {
		ErrorResponse errorResponse = new ErrorResponse("Employee of this id is not present!","404 Not Found", List.of(e.getMessage()));
		System.out.println(e);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse("Server Error!!","500 Server Error", List.of(e.getMessage()));
		System.out.println(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// handle validation errors
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		List<String> errorList = ex.getAllErrors().stream().map(s -> s.getDefaultMessage())
				.collect(Collectors.toList());
		
		ErrorResponse errorResponse = new ErrorResponse("Validation Error","400 Bad Request", errorList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
