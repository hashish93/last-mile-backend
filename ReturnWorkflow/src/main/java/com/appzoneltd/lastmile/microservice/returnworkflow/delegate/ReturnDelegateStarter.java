package com.appzoneltd.lastmile.microservice.returnworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;


@Service
public class ReturnDelegateStarter implements JavaDelegate{
	@Autowired
	private ReturnRequestService requestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long packageId=(Long) execution.getVariable("packageId");
		
		if(packageId!=null){
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>> BEFRE ");
			requestService.createNewReturnRequest(packageId);
			System.out.println(">>>>>>>>>>>>>>>>>>>>> AFTER ");
		}
		
	}
}
