package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.CustomerAlternativesDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alaa.nabil on 2/19/2017.
 */
@Component
public class CustomerAlternativesConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerAlternativesConsumer.class);
    private final RuntimeService runtimeService;
    private final ObjectMapper mapper;

    @Autowired
    public CustomerAlternativesConsumer(RuntimeService runtimeService, ObjectMapper mapper) {
        this.runtimeService = runtimeService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "SEND_ALTERNATIVES_TO_CUSTOMER")
    public void customerAlternativesListener(@Payload String payload) {

        CustomerAlternativesDTO customerAlternativesDTO = null;
        try {
            customerAlternativesDTO = mapper.readValue(payload, CustomerAlternativesDTO.class);
            LOGGER.info("CONSUMED PAYLOAD: {}", customerAlternativesDTO.toString());
        } catch (IOException e) {
            LOGGER.error("ERROR WITH PARSING CUSTOMER ALTERNATIVES: {}" + payload);
        }
        final Map<String, Object> workFlowVariables = new HashMap<>();
        workFlowVariables.put("packageId", customerAlternativesDTO.getPackageId());
        workFlowVariables.put("requestId", customerAlternativesDTO.getRequestId());
        workFlowVariables.put("requesterId", customerAlternativesDTO.getRequesterId());
        workFlowVariables.put("isWebUser", customerAlternativesDTO.getWebUser());
        runtimeService.startProcessInstanceByMessage("startWaitingForCustomerAlternatives", workFlowVariables);
    }
}
