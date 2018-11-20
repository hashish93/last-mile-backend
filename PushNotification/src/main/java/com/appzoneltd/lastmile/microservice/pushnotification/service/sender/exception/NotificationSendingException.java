package com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception;

public class NotificationSendingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7806156080339013680L;
	
	public NotificationSendingException(Throwable causeException) {
		
		super(causeException);
	}

}
