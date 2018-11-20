package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.util.List;

public class ActiveVehicleOrderDto {

	private String driverName;
	private String deviceNumber;
	private String licenseId;
	private Long buildingId;
	private Long vehicleId;
	private String vehicleBrand;
	private String vehicleColor;
	private String vehicleModel;
	private String vehiclePlateNo;
	private Long driverImg;
	private List<ActionOrderDto> actionOrderDtos;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehiclePlateNo() {
		return vehiclePlateNo;
	}

	public void setVehiclePlateNo(String vehiclePlateNo) {
		this.vehiclePlateNo = vehiclePlateNo;
	}

	public Long getDriverImg() {
		return driverImg;
	}

	public void setDriverImg(Long driverImg) {
		this.driverImg = driverImg;
	}

	public List<ActionOrderDto> getActionOrderDtos() {
		return actionOrderDtos;
	}

	public void setActionOrderDtos(List<ActionOrderDto> actionOrderDtos) {
		this.actionOrderDtos = actionOrderDtos;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

}
