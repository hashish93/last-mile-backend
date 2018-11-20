package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.NearByVehicleService;

@Service
public class SaveNearbyVehiclesforPackageDelegate implements JavaDelegate{

	@Autowired
	private NearByVehicleService nearByVehicleService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<Long> nearByVehicles = (List<Long>) execution.getVariable("nearByVehicles");
		Long packageId=(Long)execution.getVariable("packageId");
		Long requestId=(Long)execution.getVariable("requestId");			
		nearByVehicleService.saveNearByVehicles(packageId, requestId, nearByVehicles);
	}
	
}
