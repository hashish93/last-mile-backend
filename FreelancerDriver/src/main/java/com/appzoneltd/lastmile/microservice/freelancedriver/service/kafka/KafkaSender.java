package com.appzoneltd.lastmile.microservice.freelancedriver.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaSender {

	 private final KafkaTemplate<Long, String> kafkaTemplate;

	    @Autowired
	    public KafkaSender(KafkaTemplate<Long, String> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	    @Async
	    public ListenableFuture<SendResult<Long, String>> send(String topic, String message) {
	        return kafkaTemplate.send(topic, message);
	    }
	
}
