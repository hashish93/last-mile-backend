package com.appzoneltd.lastmile.microservice.email.endpoint;

public interface BasicEmailSenderEndpoint {

	public Object handleIncomingEmailRequest(String emailObjectToSend);
	
	
}
