package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Service
public class CheckCustomerFoundDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		boolean customerFound=(boolean)execution.getVariable("customerFound");
		execution.setVariable("customerFound", customerFound);
		
		System.out.println(" > >  " + execution.getCurrentActivityName());

	}

}
