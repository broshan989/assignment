package com.app.customexception;

@SuppressWarnings("serial")
public class InvalidEmpoyeeException extends RuntimeException {
	
	public InvalidEmpoyeeException(String msg) {
		super(msg);
	}
}
