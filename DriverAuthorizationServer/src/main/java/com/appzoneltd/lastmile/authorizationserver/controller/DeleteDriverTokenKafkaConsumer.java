package com.appzoneltd.lastmile.authorizationserver.controller;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.authorizationserver.dto.UserDTO;
import com.appzoneltd.lastmile.authorizationserver.service.DeleteDriverTokens;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DeleteDriverTokenKafkaConsumer {

	private final DeleteDriverTokens deleteDriverTokens;
	private final ObjectMapper mapper;

	@Autowired
	public DeleteDriverTokenKafkaConsumer(LogoutController logoutController, DeleteDriverTokens deleteDriverTokens) {
		this.deleteDriverTokens = deleteDriverTokens;
		this.mapper = new ObjectMapper();
	}

	@KafkaListener(topics = "DELETE_DRIVER_TOKEN")
	public void listen(ConsumerRecord<Long, String> record) throws JsonParseException, JsonMappingException, IOException {

		UserDTO user = mapper.readValue(record.value(), UserDTO.class);
		deleteDriverTokens.findLoggedInUserAndDeleteTokens(user);

	}
}
