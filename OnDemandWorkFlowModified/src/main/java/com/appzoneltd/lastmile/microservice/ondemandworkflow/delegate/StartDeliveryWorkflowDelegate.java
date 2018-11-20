package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.DeliveryBase;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StartDeliveryWorkflowDelegate implements JavaDelegate {

	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long packageId = (Long) execution.getVariable("packageId");
		DeliveryBase deliveryBase = new DeliveryBase();
		deliveryBase.setPackageId(packageId);
		onDemandWorkFlowProducer.sendMessage("DELIVERY_WORKFLOW_START",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deliveryBase));
	}

}