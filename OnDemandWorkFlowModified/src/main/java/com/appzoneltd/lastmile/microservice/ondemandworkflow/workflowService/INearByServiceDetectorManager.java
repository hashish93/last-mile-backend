package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import java.util.List;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.NearByVehicleAssign;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.Vehicle;

public interface INearByServiceDetectorManager {

	public void addServiceDetector(NearByVehicleDetectorService newServiceObject);
	
	public List<Long> getNearByVehicles(Long packageId, Long requestId, boolean workflowCall);
	
	public List<Vehicle> getNearByVehiclesForAssing(NearByVehicleAssign nearByVehicleAssign,
            WorkflowPickupRequestInfo workflowPickupRequestInfo);
}
