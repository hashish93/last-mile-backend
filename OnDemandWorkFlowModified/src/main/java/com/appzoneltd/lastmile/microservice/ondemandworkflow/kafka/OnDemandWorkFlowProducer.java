package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OnDemandWorkFlowProducer {

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	public void sendMessage(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}

}
