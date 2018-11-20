package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.VehicleActiveOrder;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveVehicleOrderDto;

public interface ActiveOrderService {
	
	ActiveOrderDto getActiveOrderDTO(VehicleActiveOrder vehicleActiveOrder);
	
	List<ActiveOrderDto> getAllActiveOrders(Long hubId , Principal principal);
	
	List<ActiveOrderDto> fillActiveOrders(List<VehicleActiveOrder> vehicleActiveOrders);
	
	ActiveVehicleOrderDto getActionOrderListForScheduledVehicle(Long activeVehicleId);
	
	ActiveVehicleOrderDto getActionOrderListForOndemandVehicle(Long activeVehicleId) ;
	
	boolean chkActiveVehicleRelatedToTodayPlan (Long activeVehicleId);

}
