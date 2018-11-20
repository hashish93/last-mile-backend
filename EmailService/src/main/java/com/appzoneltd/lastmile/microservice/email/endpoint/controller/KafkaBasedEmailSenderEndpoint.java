package com.appzoneltd.lastmile.microservice.email.endpoint.controller;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.email.endpoint.BasicEmailSenderEndpoint;
import com.appzoneltd.lastmile.microservice.email.exception.MailSendingFailureException;
import com.appzoneltd.lastmile.microservice.email.service.basic.BasicEmailSenderService;

@Service
public class KafkaBasedEmailSenderEndpoint implements BasicEmailSenderEndpoint {

	private BasicEmailSenderService emailSender;  
	
	public KafkaBasedEmailSenderEndpoint(BasicEmailSenderService senderService) {
		this.emailSender = senderService;
	}
	
	@KafkaListener(topics = "EMAIL-SERVICE")
	@Override
	public Object handleIncomingEmailRequest(@Payload String emailObjectToSend) {
		
		try {
			
			this.emailSender.sendEmail(emailObjectToSend);
			
		} catch (MailSendingFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
