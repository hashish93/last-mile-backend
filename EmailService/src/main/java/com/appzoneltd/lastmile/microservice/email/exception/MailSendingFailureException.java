package com.appzoneltd.lastmile.microservice.email.exception;

public class MailSendingFailureException extends Exception {

	public MailSendingFailureException(Exception subException) {
		super(subException);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8931790410292981933L;

}
