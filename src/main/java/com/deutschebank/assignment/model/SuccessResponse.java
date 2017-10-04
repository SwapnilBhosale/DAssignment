package com.deutschebank.assignment.model;

public class SuccessResponse extends Response {

	private String message;
	private Shop data;

	public SuccessResponse(String msg,Shop data) {
		super(true);
		this.message = msg;
		this.data = data;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Shop getData() {
		return data;
	}

	public void setData(Shop data) {
		this.data = data;
	}
	
	
	
	
	
	
	
}
