package com.appzoneltd.lastmile.microservice.email.exception;

public class MissingEmailFieldsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799608965993674233L;

	public MissingEmailFieldsException(String message) {
		super(message);
	}
}
