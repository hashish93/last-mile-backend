package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;

@Service
public class CheckIfReceipientIsSystemUserDelegate implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("******* CheckIfReceipientIsSystemUserDelegate step ********");
		Long packageId = (Long) execution.getVariable("packageId");
		Long receipientId = deliveryRequestService.getReceipentForPackage(packageId);
		boolean systemRecepient = true;

		if (receipientId == null) {
			System.out.println("RECEIPENT NOT FOUND");
			systemRecepient = false;
		}
		// SET SYSTEM RECEPIENT
		System.out.println("IS RECEIPENT USER "+systemRecepient);
		System.out.println("RECEIPENT ID "+receipientId);
		System.out.println("*******end CheckIfReceipientIsSystemUserDelegate step ********");
		execution.setVariable("systemRecepient", systemRecepient);

	}

}
