package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckDriverArrivedToCustomerDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println(" > >  " + execution.getCurrentActivityName());
		System.out.println("start CheckDriverArrivedToCustomerDelegate");
		System.out.println("execution "+execution.getVariable("packageId"));
		Long driverId=(Long) execution.getVariable("driverId");
		Long packageId=(Long) execution.getVariable("packageId");
		System.out.println("driverId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+driverId);
		System.out.println("packageId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+packageId);
	} 
}
