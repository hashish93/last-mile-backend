package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.util.Date;
import java.util.List;

public class PlanDetailsDto {

	private boolean activeVehicleStatus;
	private String driverName;
	private Date activeVehicleWorkShiftFrom;
	private Date activeVehicleWorkShiftTo;
	private String activeVehicleType;
	private List<JobOrderDto> jobOrders;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public boolean isActiveVehicleStatus() {
		return activeVehicleStatus;
	}

	public Date getActiveVehicleWorkShiftFrom() {
		return activeVehicleWorkShiftFrom;
	}

	public Date getActiveVehicleWorkShiftTo() {
		return activeVehicleWorkShiftTo;
	}

	public String getActiveVehicleType() {
		return activeVehicleType;
	}

	public List<JobOrderDto> getJobOrders() {
		return jobOrders;
	}

	public void setActiveVehicleStatus(boolean activeVehicleStatus) {
		this.activeVehicleStatus = activeVehicleStatus;
	}

	public void setActiveVehicleWorkShiftFrom(Date activeVehicleWorkShiftFrom) {
		this.activeVehicleWorkShiftFrom = activeVehicleWorkShiftFrom;
	}

	public void setActiveVehicleWorkShiftTo(Date activeVehicleWorkShiftTo) {
		this.activeVehicleWorkShiftTo = activeVehicleWorkShiftTo;
	}

	public void setActiveVehicleType(String activeVehicleType) {
		this.activeVehicleType = activeVehicleType;
	}

	public void setJobOrders(List<JobOrderDto> jobOrders) {
		this.jobOrders = jobOrders;
	}

}
