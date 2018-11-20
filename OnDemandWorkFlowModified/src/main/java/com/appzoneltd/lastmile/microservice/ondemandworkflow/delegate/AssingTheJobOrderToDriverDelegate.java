package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;

@Service
public class AssingTheJobOrderToDriverDelegate implements JavaDelegate{

	@Autowired
	private RequestService requestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		// Getting PackageId to Process it 
		Long packageId=(Long) execution.getVariable("packageId");
		Long driverId=(Long) execution.getVariable("driverId");
		Long requestId=(Long) execution.getVariable("requestId");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASSINGING THE JOB ORDER TO THE DRIVER  ");
		requestService.assignRequestToDriver(requestId, driverId, packageId);
	}
	
	
	
}
