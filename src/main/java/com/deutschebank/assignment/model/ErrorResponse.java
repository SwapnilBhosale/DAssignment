package com.deutschebank.assignment.model;

public class ErrorResponse extends Response{

	
	String errorCode;
	String errorMessage;
	
	public ErrorResponse(String errorCode, String errorMessage) {
		super(false);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
