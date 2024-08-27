package com.gabriel_mello.clients_api.application.exceptions;

public class ErrorResponse {
	private int statusCode;
	private String message;

	public ErrorResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	// Getters e setters
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
