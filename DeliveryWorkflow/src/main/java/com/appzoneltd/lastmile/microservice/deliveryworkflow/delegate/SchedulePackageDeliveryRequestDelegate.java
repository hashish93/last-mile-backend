package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliverySchedulingService;


@Service
public class SchedulePackageDeliveryRequestDelegate implements JavaDelegate {

	@Autowired
	private DeliverySchedulingService deliverySchedulingService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long packageId = (Long) execution.getVariable("packageId");
		deliverySchedulingService.scheduleDeliveryRequest(packageId);
	}

}