package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.PlanOrder;

/**
 * Created by Ahmed Atef  on 2/15/2017.
 */
public interface PlanOrderService {
	List<PlanOrder> getOrdersByPlanId(long planId);

	List<PlanOrder> getOrdersByPlan(Principal principal);

	List<PlanOrder> getOrdersByActiveVehicleIds(List<Long> activeVehicleIds , Principal principal);

	List<ActiveVehicleDto> findAllActiveVehiclesInfo(Principal principal,Long hubId);

}
