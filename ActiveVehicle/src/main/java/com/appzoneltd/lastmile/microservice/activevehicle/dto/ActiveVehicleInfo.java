package com.appzoneltd.lastmile.microservice.activevehicle.dto;

import java.util.Date;

public class ActiveVehicleInfo extends ActiveVehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3209848636382073763L;
	private String driverUsername;
	private String deviceModel;
//	private String calendar;
	private Date from ;
	private Date to ;
	private String vehicleType;
	private String buildingName ;

	public ActiveVehicleInfo() {
	}

	public String getDriverUsername() {
		return driverUsername;
	}

	public void setDriverUsername(String driverUsername) {
		this.driverUsername = driverUsername;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

//	public String getCalendar() {
//		return calendar;
//	}
//
//	public void setCalendar(String calendar) {
//		this.calendar = calendar;
//	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
