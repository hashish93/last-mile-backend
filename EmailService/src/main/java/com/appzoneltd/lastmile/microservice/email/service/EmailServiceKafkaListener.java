package com.appzoneltd.lastmile.microservice.email.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.email.exception.MailSendingFailureException;
import com.appzoneltd.lastmile.microservice.email.service.basic.BasicEmailSenderService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class EmailServiceKafkaListener {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceKafkaListener.class);
    private BasicEmailSenderService emailSender;
    private final KafkaTemplate<String, Object> kafkaProducer;

    @Autowired
    public EmailServiceKafkaListener(BasicEmailSenderService emailSenderUtil,KafkaTemplate<String, Object> kafkaProducer) {
    	this.emailSender = emailSenderUtil ;
    	this.kafkaProducer = kafkaProducer;
    }

    @KafkaListener(topics = "EMAIL-SERVICE")
    public void listenForEmailRequests(@Payload String model)
            throws JsonParseException, JsonMappingException, IOException {
    	
        LOGGER.info("EMAIL-SERVICE MODEL :: {}", model);
        
        try {
			sendEmail(model);
		} catch (MailSendingFailureException e) {
			e.printStackTrace();
		}

    }

	private void sendEmail(String model) throws MailSendingFailureException {
		
		this.emailSender.sendEmail(model);
		
	}
}
