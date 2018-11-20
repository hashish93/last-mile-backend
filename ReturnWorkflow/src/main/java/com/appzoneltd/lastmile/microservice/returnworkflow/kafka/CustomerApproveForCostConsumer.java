package com.appzoneltd.lastmile.microservice.returnworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnParameter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerApproveForCostConsumer {
	
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private RuntimeService runTimeService;
	
	@Transactional
	@KafkaListener(topics = "RETURN_CUSTOMER_COST_APPROVE")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>> RETURN_CUSTOMER_COST_APPROVE WORKFLOW_CUSTOMER_FOUND");
		System.out.println("payload >>>>>>>>>>>>>>>"+payload);
		ReturnParameter returnParameter = mapper.readValue(payload, ReturnParameter.class);
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", returnParameter.getPackageId());
		workflowData.put("customerApproveInvoice", returnParameter.getCustomerApproveInvoice());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> costApproved "+returnParameter.getCustomerApproveInvoice());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> preparing to start cost approving");
		runTimeService.startProcessInstanceByMessage("startCustomerApproveForCost", workflowData);
		
	}

}
