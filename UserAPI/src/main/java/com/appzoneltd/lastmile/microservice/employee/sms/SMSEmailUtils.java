package com.appzoneltd.lastmile.microservice.employee.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SMSEmailUtils {

	private final KafkaTemplate<Integer, String> kafkaTemplate;
	private final ObjectMapper mapper;
	private final static String SMS_TOPIC = "SMS-NOTIFICATION-UNREGISTERED";

	@Autowired
	public SMSEmailUtils(KafkaTemplate<Integer, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.mapper = new ObjectMapper();
	}


	
	public void sendSms(String fullPhoneNumber, String message) {

		try {

			kafkaTemplate.send(SMS_TOPIC,
					mapper.writeValueAsString(new UnRegisteredSmsModel(fullPhoneNumber, message)));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void sendSms(UnRegisteredSmsModel messageModel) {

		try {

			kafkaTemplate.send(SMS_TOPIC,
					mapper.writeValueAsString(messageModel));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}


}
