package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageHub;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PickUpHubConsumer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(WorkflowConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RuntimeService runTimeService;

    @KafkaListener(topics = "PickupHubResponse")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        // Getting Payload from Kafka
        WorkflowPackageHub workflowPackageHub = mapper.readValue(payload, WorkflowPackageHub.class);
        /// Logging Result
        LOGGER.info(">> Receive From Kafka PickHubResponse ='{}'" + workflowPackageHub.toString());
        /// Complete the WorkFlow
        Map<String, Object> workflowData = new HashMap<String, Object>();
        workflowData.put("packageId", workflowPackageHub.getPackageId());
        workflowData.put("requesterId", workflowPackageHub.getRequesterId());
        workflowData.put("adminAssign", false);
        workflowData.put("requestType", workflowPackageHub.getRequestType());
        // Checking if the Hub id is Found or Not
        if (workflowPackageHub.getHubId() == null) {
            workflowData.put("relatedToHub", false);
        } else {
            /// Send Assign Hub To Package
            workflowData.put("relatedToHub", true);
        }//end if-else
        // Starting WorkFlow
        if (workflowPackageHub.getRequestType().equalsIgnoreCase("ON-DEMAND"))
            runTimeService.startProcessInstanceByMessage("startNearByVehicleModule", workflowData);
        else if (workflowPackageHub.getRequestType().equalsIgnoreCase("SCHEDULED")) {
            ProcessInstance processInstance = null;
            int timer = 0;
            while (timer < 10000) {
                List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
                        variableValueEquals("packageId", workflowPackageHub.getPackageId()).list();
                if (processInstances != null && !processInstances.isEmpty())
                    processInstance = processInstances.get(0);
                if (processInstance != null) {
                    break;
                } else {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer += 100;
                }
            }

            Execution execution = null;
            timer = 0;

            while (timer < 10000) {
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WAIT_HUB_RESPONSE").singleResult();
                if (execution != null) {
                    break;
                } else {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer += 100;
                }
            }
            runTimeService.signal(execution.getId(), workflowData);
        }
    }

}
