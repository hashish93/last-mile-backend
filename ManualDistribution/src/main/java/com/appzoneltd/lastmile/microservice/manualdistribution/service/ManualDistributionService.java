package com.appzoneltd.lastmile.microservice.manualdistribution.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.manualdistribution.model.PlanOutline;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.VehiclePlanDetails;

public interface ManualDistributionService {
	List<VehiclePlanDetails> buildPlanDetails(List<PlanOutline> planOutline) throws Exception;	
	
}
