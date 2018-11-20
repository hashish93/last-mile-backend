package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;

@Service
public class PackageTrackingNumberDelegate implements JavaDelegate{

	@Autowired
	private RequestService requestService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long packageId=(Long) execution.getVariable("packageId");		
		
		String trackingNumber = requestService.setPackageTrackingNumber(packageId);
		execution.setVariable("trackingNumber", trackingNumber);
		MyPrinter.workflowStep("SET TRACKING NUMBER "+trackingNumber);
	}
}
	