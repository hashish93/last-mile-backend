package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;

@Service
public class DeliveryRequestCreatorDelegate implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long packageId=(Long) execution.getVariable("packageId");
		
		if(packageId!=null){
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>> BEFRE ");
			deliveryRequestService.createNewDeliveryRequest(packageId);
			System.out.println(">>>>>>>>>>>>>>>>>>>>> AFTER ");
		}
		
	}

}
