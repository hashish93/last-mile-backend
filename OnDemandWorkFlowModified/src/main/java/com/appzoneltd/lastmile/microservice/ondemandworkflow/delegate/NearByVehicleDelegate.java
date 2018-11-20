package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.HubConfigurationRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.HubConfigurationEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.INearByServiceDetectorManager;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;

@Service
public class NearByVehicleDelegate implements JavaDelegate{
	
	@Autowired
	private INearByServiceDetectorManager nearbyServiceDetectorManager;
	
	@Autowired
	private RequestService requestService;

	@Autowired
	private HubConfigurationRepository hubConfigurationRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId=(Long) execution.getVariable("packageId");		
		Long requestId=(Long) execution.getVariable("requestId");	
		
		MyPrinter.workflowStep("NearByVehicleDetectorService");
		List<Long> nearByVehicles=
				nearbyServiceDetectorManager.getNearByVehicles(packageId, requestId, true);
		
		boolean automatic =false;
		boolean vehicleFound = false;
		
		Long hubId = requestService.getRequestHubId(requestId);
		List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationRepository.findSystemConfigurationForHub(hubId, 2L);
		if(hubConfigurationEntities!=null && hubConfigurationEntities.size()>0){			
			automatic = (hubConfigurationEntities.get(0).getValue().intValue() == 1);
			if(nearByVehicles.size()>0){
				vehicleFound=true;
			}
		}
		execution.setVariable("automatic", automatic);
		execution.setVariable("vehicleFound", vehicleFound);
		execution.setVariable("nearByVehicles", nearByVehicles);
	}
	
}
