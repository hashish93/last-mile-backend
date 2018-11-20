package com.appzoneltd.lastmile.microservice.modelobjects;

import java.io.Serializable;

import com.appzoneltd.lastmile.microservice.enums.MessageType;

/**
 * @author alaa.nabil
 *
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MessageType messageType;
	private String property;
	private String message;

	public Message() {

	}

	public Message(MessageType messageType, String property, String message) {
		this.messageType = messageType;
		this.property = property;
		this.message = message;
	}

	public Message(MessageType messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
