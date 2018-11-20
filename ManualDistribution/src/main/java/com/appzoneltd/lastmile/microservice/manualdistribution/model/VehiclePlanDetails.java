package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.util.List;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ActiveVehicle;

public class VehiclePlanDetails {
	
	private Long activeVehicleId ; 
	
	private ActiveVehicle activeVehicle;
	
	private List<JobOrderDto> jobOrders ;
	
	private Boolean vehicleOverWeight;

	public Long getActiveVehicleId() {
		return activeVehicleId;
	}


	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}


	public ActiveVehicle getActiveVehicle() {
		return activeVehicle;
	}

	public void setActiveVehicle(ActiveVehicle activeVehicle) {
		this.activeVehicle = activeVehicle;
	}


	public List<JobOrderDto> getJobOrders() {
		return jobOrders;
	}


	public void setJobOrders(List<JobOrderDto> jobOrders) {
		this.jobOrders = jobOrders;
	}


	public Boolean getVehicleOverWeight() {
		return vehicleOverWeight;
	}


	public void setVehicleOverWeight(Boolean vehicleOverWeight) {
		this.vehicleOverWeight = vehicleOverWeight;
	}
	
	

}
