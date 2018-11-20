package com.appzoneltd.lastmile.microservice.returnworkflow.notification.model;

public enum NotificationLabel {

	NOTIFICATION_TITLE("notification_title"), NOTIFICATION_BODY("notification_body"), NOTIFICATION_ITEM_TITLE(
			"notification_item_title"), NOTIFICATION_ITEM_BODY(
					"notification_item_body"), TYPE("type"), TIME("time"), PAYLOAD("payload");

	private final String label;

	private NotificationLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
