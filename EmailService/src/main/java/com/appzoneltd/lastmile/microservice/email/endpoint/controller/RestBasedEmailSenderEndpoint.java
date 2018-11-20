package com.appzoneltd.lastmile.microservice.email.endpoint.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appzoneltd.lastmile.microservice.email.endpoint.BasicEmailSenderEndpoint;
import com.appzoneltd.lastmile.microservice.email.exception.MailSendingFailureException;
import com.appzoneltd.lastmile.microservice.email.service.basic.BasicEmailSenderService;

@Service
public class RestBasedEmailSenderEndpoint implements BasicEmailSenderEndpoint {

private BasicEmailSenderService emailSender;  
	
	public RestBasedEmailSenderEndpoint(BasicEmailSenderService senderService) {
		this.emailSender = senderService;
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public Object handleIncomingEmailRequest(@RequestBody String emailObjectToSend) {
		
		try {
			this.emailSender.sendEmail(emailObjectToSend);
			
		} catch (MailSendingFailureException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

}
