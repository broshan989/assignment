package com.app.exceptionhandler.error_response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
	String errorMessage;
	String httpStatus;
	List<String> details;
}
