package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageHub;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    	WorkflowPackageHub workflowPackageHub = mapper.readValue(payload, WorkflowPackageHub.class);
        /// Logging Result
        LOGGER.info(">> Receive From Kafka PickHubResponse ='{}'" + workflowPackageHub.toString());
        /// Complete the WorkFlow
        Map<String, Object> workflowData = new HashMap<String, Object>();
        workflowData.put("packageId", workflowPackageHub.getPackageId());
        workflowData.put("requesterId", workflowPackageHub.getRequesterId());
        workflowData.put("adminAssign", false);
        workflowData.put("requestType", workflowPackageHub.getRequestType());
        
        if (workflowPackageHub.getHubId() == null) {
          workflowData.put("relatedToHub", false);
        }else {
          workflowData.put("relatedToHub", true);
        }//end if-else
        
       if (workflowPackageHub.getRequestType().equalsIgnoreCase("ON-DEMAND")){
    	   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ON_DEMAND");
          runTimeService.startProcessInstanceByMessage("startNearByVehicleModule", workflowData);
       }
       else if (workflowPackageHub.getRequestType().equalsIgnoreCase("SCHEDULED")){
    	   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SCHEDULED");
    	  ProcessInstance processInstance=null;
	        List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
	                    variableValueEquals("packageId", workflowPackageHub.getPackageId()).list();
	        
	        if (processInstances != null && !processInstances.isEmpty()){
	        	processInstance = processInstances.get(0);
	        }
	        Execution execution = runTimeService.createExecutionQuery()
                  .processInstanceId(processInstance.getProcessInstanceId()).activityId("WAIT_HUB_RESPONSE").singleResult();
	        
	        workflowData.put("admin",false);
	        runTimeService.signal(execution.getId(), workflowData);
       }else{
    	   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ELSE");
       }    	  

    }

}
