package com.appzoneltd.lastmile.microservice.pushnotification.controller;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.pushnotification.dao.NotificationModel;
import com.appzoneltd.lastmile.microservice.pushnotification.firebase.MulticastResult;
import com.appzoneltd.lastmile.microservice.pushnotification.service.NotificationService;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author alaa.nabil
 *
 */
@Service
public class PushNotificationKafkaListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String PUBLISH_TOPIC = "push-notification-response";
	private final NotificationService notificationService;
	private final KafkaTemplate<Long, String> kafkaProducer;
	private final ObjectMapper mapper;

	@Autowired
	public PushNotificationKafkaListener(NotificationService notificationService,
			KafkaTemplate<Long, String> kafkaProducer) {
		this.notificationService = notificationService;
		this.mapper = new ObjectMapper();
		this.kafkaProducer = kafkaProducer;
	}

	@KafkaListener(topics = "push-notification")
	public void listen(ConsumerRecord<Long, String> record)
			throws JsonParseException, JsonMappingException, IOException {
		
		
		NotificationModel notificationModel = mapper.readValue(record.value(), NotificationModel.class);
		
		logger.warn("NOTIFICATION MODEL :::" + notificationModel.toString());

		try {
			notificationService.sendNotification(notificationModel.getUserIds(), notificationModel.getData(),
					notificationModel.getRecipientType());
			
		} catch (NotificationSendingException e) {
			e.printStackTrace();
		}

//		logger.warn("RESULT MODEL :::" + result.toString());

//		if (result != null)
//			kafkaProducer.send(PUBLISH_TOPIC, notificationModel.getPackageId(), mapper.writeValueAsString(result));

	}

}
