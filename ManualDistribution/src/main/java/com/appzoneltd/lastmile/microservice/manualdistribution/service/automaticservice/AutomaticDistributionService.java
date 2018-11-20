package com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice;

import com.appzoneltd.lastmile.microservice.manualdistribution.model.VehiclePlanDetails;

import java.util.List;

public interface AutomaticDistributionService {

	List <VehiclePlanDetails> planToBeGenerated() throws Exception;
	Long savePlan() throws Exception;
}
