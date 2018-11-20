package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.CustomerAlternativesDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by alaa.nabil on 2/19/2017.
 */
@Component
public class CustomerAlternativesConsumer {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
    private ObjectMapper mapper;

    @KafkaListener(topics = "SEND_ALTERNATIVES_TO_CUSTOMER")
    public void customerAlternativesListener(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

        CustomerAlternativesDTO customerAlternativesDTO = mapper.readValue(payload, CustomerAlternativesDTO.class);

    
        final Map<String, Object> workFlowVariables = new HashMap<>();
        workFlowVariables.put("packageId", customerAlternativesDTO.getPackageId());
        workFlowVariables.put("requestId", customerAlternativesDTO.getRequestId());
        workFlowVariables.put("requesterId", customerAlternativesDTO.getRequesterId());
        workFlowVariables.put("isAdmin", customerAlternativesDTO.isAdmin());
        runtimeService.startProcessInstanceByMessage("startWaitingForCustomerAlternatives", workFlowVariables);
    }
}
