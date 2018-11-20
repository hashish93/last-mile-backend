package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckCustomerFoundDelegate implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println(" > >  " + execution.getCurrentActivityName());
		System.out.println("start CheckCustomerFoundDelegate");
	
		Long customerId=(Long) execution.getVariable("customerId");
		Long packageId=(Long) execution.getVariable("packageId");
		Boolean customerFound = (Boolean)execution.getVariable("customerFound");
		System.out.println("customerId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+customerId);
		System.out.println("packageId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+packageId);
		System.out.println("customerFound >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+customerFound);
		execution.setVariable("customerFound", customerFound);
		execution.setVariable("customerId", customerId);
		execution.setVariable("packageId", packageId);
		
	} 
}
