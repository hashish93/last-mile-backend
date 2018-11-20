package com.appzoneltd.lastmile.microservice.returnworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;

@Service
public class ChangeAllDriverToActionNeededDelegate implements JavaDelegate {

	@Autowired
	private ReturnRequestService returnRequestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long driverId=(Long) execution.getVariable("driverId");		
		returnRequestService.changeAllDeliveryRequestsToActionNeeded(driverId);
		System.out.println(">>>>>>>>>>>>>>>>driverId"+driverId);
	}

}