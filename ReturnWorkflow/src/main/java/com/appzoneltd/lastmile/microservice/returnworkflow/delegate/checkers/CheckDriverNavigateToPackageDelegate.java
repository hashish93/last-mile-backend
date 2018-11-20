package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckDriverNavigateToPackageDelegate implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("start CheckDriverNavigateToPackageDelegate");
		System.out.println("packageId "+execution.getVariable("packageId"));
		System.out.println("driverId "+execution.getVariable("driverId"));
		Long packageId=(Long) execution.getVariable("packageId");
		Long driverId=(Long) execution.getVariable("driverId");
		execution.setVariable("packageId", packageId);
		execution.setVariable("driverId", driverId);
		

	}

}
