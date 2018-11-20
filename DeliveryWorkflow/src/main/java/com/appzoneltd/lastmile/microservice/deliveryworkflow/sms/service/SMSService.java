package com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.DeliveryWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.model.SMSModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SMSService {

	@Autowired
	private DeliveryWorkFlowProducer deliveryWorkFlowProducer;

	@Autowired
	private ObjectMapper mapper;
	
	private static final String SMS_TOPIC="SMS-NOTIFICATION-UNREGISTERED";
	
	public void sendSMS(SMSModel smsModel){
		
		try {
			deliveryWorkFlowProducer.sendMessage(SMS_TOPIC,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(smsModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
}