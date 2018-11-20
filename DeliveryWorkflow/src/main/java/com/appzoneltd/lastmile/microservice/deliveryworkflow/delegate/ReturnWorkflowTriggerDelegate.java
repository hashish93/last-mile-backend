package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.DeliveryWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models.DeliveryBase;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReturnWorkflowTriggerDelegate implements JavaDelegate {

	@Autowired
    DeliveryWorkFlowProducer deliveryWorkFlowProducer;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
      Long packageId = (Long) execution.getVariable("packageId");
      System.out.println(">>>>>>>>>>>>>>>> ReturnWorkflowTriggerDelegate");
      System.out.println(">>>>>>>>>>>>>>>> packageId "+packageId);
      DeliveryBase deliveryBase = new DeliveryBase();
      deliveryBase.setPackageId(packageId);
      deliveryWorkFlowProducer.sendMessage("RETURN_WORKFLOW_START", new ObjectMapper().writeValueAsString(deliveryBase));
	}

}
