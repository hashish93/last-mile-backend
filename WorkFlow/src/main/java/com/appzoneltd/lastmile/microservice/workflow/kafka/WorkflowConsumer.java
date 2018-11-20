package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowBase;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WorkflowConsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RuntimeService runTimeService;

    @Transactional
    @KafkaListener(topics = "LastMileRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

        WorkflowBase workflowRequest = mapper.readValue(payload, WorkflowBase.class);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>> Receiving New " + workflowRequest.getRequestType() + " Request");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println(">> Workflow Request " + workflowRequest.toString());
        Map<String, Object> workflowData = new HashMap<String, Object>();
        workflowData.put("packageId", workflowRequest.getPackageId());
        workflowData.put("requestType", workflowRequest.getRequestType());
        // Starting WorkFlow
        System.out.println(">> 1- Starting WorkFlow");
        if (workflowRequest.getRequestType().equalsIgnoreCase("SCHEDULED"))
            runTimeService.startProcessInstanceByKey("ScheduledPickupRequestProcess", workflowData);
        else if (workflowRequest.getRequestType().equalsIgnoreCase("ON-DEMAND"))
            runTimeService.startProcessInstanceByKey("myProcess", workflowData);

    }

}
