package com.appzoneltd.lastmile.microservice.enums;

public enum EntityStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String value;

	private EntityStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
