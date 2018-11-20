package com.appzoneltd.lastmile.microservice.returnworkflow.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class ReturnWorkFlowProducer {

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	@SuppressWarnings("unused")
	public void sendMessage(String topic, String message) {

		ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);

	}

}
