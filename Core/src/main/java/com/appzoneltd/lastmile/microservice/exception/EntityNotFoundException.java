package com.appzoneltd.lastmile.microservice.exception;

public class EntityNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6366590751550765276L;
	private final String message;

	public EntityNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
