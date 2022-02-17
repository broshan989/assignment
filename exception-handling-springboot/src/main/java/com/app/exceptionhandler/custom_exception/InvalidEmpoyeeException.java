package com.app.exceptionhandler.custom_exception;

@SuppressWarnings("serial")
public class InvalidEmpoyeeException extends RuntimeException {
	
	public InvalidEmpoyeeException(String msg) {
		super(msg);
	}
}
