package com.appzoneltd.lastmile.microservice.sms.dto;

public class UnRegisteredModel {

	private String phoneNumber;
	private String message;

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
