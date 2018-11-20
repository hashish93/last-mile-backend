package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowCancelRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

@Component

public class CancelPickupRequestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RuntimeService runTimeService;

    // private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "CancelPickupRequest")
    public void userCancelPickupRequest(@Payload String payload)
            throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        // Getting WorkflowCancelRequest
        WorkflowCancelRequest workflowCancelRequest = mapper.readValue(payload, WorkflowCancelRequest.class);

        /// Starting Flow Message
        /// generate Map to Complete Flow
        Map<String, Object> workflowData = new HashMap<String, Object>();
        workflowData.put("packageId", workflowCancelRequest.getPackageId());
        workflowData.put("userId", workflowCancelRequest.getRequesterId());
        workflowData.put("requestId", workflowCancelRequest.getRequestId());
        workflowData.put("admin", workflowCancelRequest.isAdmin());
        workflowData.put("isDriver", workflowCancelRequest.getDriverId() != null);
        workflowData.put("driverId", workflowCancelRequest.getDriverId());
        /// Send to Kafka
        runTimeService.startProcessInstanceByMessage("startCancelRequestModule", workflowData);


    }//end receiveMessage Method


}
