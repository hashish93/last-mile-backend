package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckDriverConfirmationOnPackageDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("start CheckDriverConfirmationOnPackageDelegate");
		System.out.println("execution "+execution.getVariable("driverAcceptance"));
		boolean driverAcceptance=(boolean) execution.getVariable("driverAcceptance");
		System.out.println("driverAcceptance >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+driverAcceptance);
		execution.setVariable("driverAcceptance", driverAcceptance);

	}

}
