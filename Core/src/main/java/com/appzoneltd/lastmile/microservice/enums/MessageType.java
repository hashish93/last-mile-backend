package com.appzoneltd.lastmile.microservice.enums;

/**
 * @author alaa.nabil
 *
 */
public enum MessageType {
	SUCCESS("SUCCESS"), ERROR("ERROR"), WARNING("WARNING"), INFO("INFO");

	private final String messageType;

	private MessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageType() {
		return messageType;
	}
}
