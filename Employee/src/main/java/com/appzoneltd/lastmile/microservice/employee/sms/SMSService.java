package com.appzoneltd.lastmile.microservice.employee.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SMSService {
	
	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

	@Autowired
	private ObjectMapper mapper;
	
	private static final String SMS_TOPIC="SMS-NOTIFICATION-UNREGISTERED";
	
	public void sendSMS(SMSModel smsModel){
		
		try {
			kafkaTemplate.send(SMS_TOPIC,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(smsModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
}