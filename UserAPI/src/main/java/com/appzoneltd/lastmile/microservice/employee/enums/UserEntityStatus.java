package com.appzoneltd.lastmile.microservice.employee.enums;

public enum UserEntityStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String value;

	private UserEntityStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}