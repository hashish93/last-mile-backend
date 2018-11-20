package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckDriverArrivedToCustomerDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println(" > >  " + execution.getCurrentActivityName());

	}

}