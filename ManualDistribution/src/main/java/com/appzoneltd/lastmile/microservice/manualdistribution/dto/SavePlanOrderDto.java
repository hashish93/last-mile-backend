package com.appzoneltd.lastmile.microservice.manualdistribution.dto;

import java.io.Serializable;
import java.util.List;

import com.appzoneltd.lastmile.microservice.manualdistribution.model.SavePlanOrder;

public class SavePlanOrderDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long activeVehicleId ; 
	
	private List<SavePlanOrder> jobOrders ;

	public Long getActiveVehicleId() {
		return activeVehicleId;
	}

	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}

	public List<SavePlanOrder> getJobOrders() {
		return jobOrders;
	}

	public void setJobOrders(List<SavePlanOrder> jobOrders) {
		this.jobOrders = jobOrders;
	}




	


	

}
