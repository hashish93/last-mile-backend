package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

public class UnRegisteredSmsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String phoneNumber;
	private String message;

	public UnRegisteredSmsModel(String phoneNumber, String message) {
		this.phoneNumber = phoneNumber;
		this.message = message;
	}

	public UnRegisteredSmsModel() {

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UnRegisteredModel [phoneNumber=" + phoneNumber + ", message=" + message + "]";
	}

}
