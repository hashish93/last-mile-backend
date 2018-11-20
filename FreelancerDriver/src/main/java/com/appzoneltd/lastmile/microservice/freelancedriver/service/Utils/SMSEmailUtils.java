package com.appzoneltd.lastmile.microservice.freelancedriver.service.Utils;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.freelancedriver.dto.EmailTemplate;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.SmsModel;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.UnRegisteredSmsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SMSEmailUtils {

	private final KafkaTemplate<Long, String> kafkaTemplate;
	private final ObjectMapper mapper;
	private final static String SMS_TOPIC = "SMS-NOTIFICATION-UNREGISTERED";
	private final static String EMAIL_SERVICE = "EMAIL-SERVICE";

	public SMSEmailUtils(KafkaTemplate<Long, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.mapper = new ObjectMapper();
	}

	public void sendEmail(EmailTemplate fullMessageTempleteInfo) {
		try {
			kafkaTemplate.send(EMAIL_SERVICE, mapper.writeValueAsString(fullMessageTempleteInfo) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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

	public void sendSmsById(Long id, String message) {
		Long[] ids = {id};
		try {

			kafkaTemplate.send("SMS-NOTIFICATION", mapper.writeValueAsString(new SmsModel(ids, "DRIVER", message)));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
}
