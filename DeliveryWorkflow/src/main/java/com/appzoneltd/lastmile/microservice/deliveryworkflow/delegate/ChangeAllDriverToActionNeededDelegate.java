package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;

@Service
public class ChangeAllDriverToActionNeededDelegate implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long driverId=(Long) execution.getVariable("driverId");		
		deliveryRequestService.changeAllDeliveryRequestsToActionNeeded(driverId);
				
	}

}