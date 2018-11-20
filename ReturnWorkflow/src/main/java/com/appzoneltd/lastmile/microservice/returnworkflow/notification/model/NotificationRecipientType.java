package com.appzoneltd.lastmile.microservice.returnworkflow.notification.model;

public enum NotificationRecipientType {

	CUSTOMER("CUSTOMER"), DRIVER("DRIVER");

	private final String name;

	private NotificationRecipientType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
