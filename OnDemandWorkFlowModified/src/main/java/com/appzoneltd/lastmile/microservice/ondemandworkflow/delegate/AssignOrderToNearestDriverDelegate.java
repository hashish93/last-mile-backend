package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class AssignOrderToNearestDriverDelegate implements JavaDelegate {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<Long> nearByVehicles = (List<Long>) execution.getVariable("nearByVehicles");
		List<Long> assignedDrivers = new ArrayList<Long>();
		assignedDrivers.add(nearByVehicles.get(0));
		execution.setVariable("nearByVehicles", assignedDrivers);
	}
}
