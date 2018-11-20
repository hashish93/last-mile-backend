package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.util.Date;

public class ActiveVehicleDto {

	private Long activeVehicleId ; 
	private String vehicleType ; 
	private String driverName ; 
	private Date workShiftFrom ; 
	private Date workShiftTo ;
	
	public Long getActiveVehicleId() {
		return activeVehicleId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public String getDriverName() {
		return driverName;
	}
	public Date getWorkShiftFrom() {
		return workShiftFrom;
	}
	public Date getWorkShiftTo() {
		return workShiftTo;
	}
	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public void setWorkShiftFrom(Date workShiftFrom) {
		this.workShiftFrom = workShiftFrom;
	}
	public void setWorkShiftTo(Date workShiftTo) {
		this.workShiftTo = workShiftTo;
	} 
}
