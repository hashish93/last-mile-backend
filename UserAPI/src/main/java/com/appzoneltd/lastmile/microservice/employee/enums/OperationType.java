package com.appzoneltd.lastmile.microservice.employee.enums;

public enum OperationType {
	SAVE("SAVE"), UPDATE("UPDATE");
	
	private final String value;

	private OperationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
