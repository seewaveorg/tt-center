package com.ngs.service;

public class ExceptionService extends Exception {

	private static final long serialVersionUID = -4744430183523721711L;

	private String message = "This is an exception..";

	public ExceptionService(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
