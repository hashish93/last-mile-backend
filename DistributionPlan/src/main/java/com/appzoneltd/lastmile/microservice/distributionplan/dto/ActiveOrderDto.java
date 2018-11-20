package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.io.Serializable;

public class ActiveOrderDto implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Long activeVehicleId;
	private Long driverImageId;
	private String driverName;
	private String deviceNumber;
	private String driverRate;
	private String vehicleState;
	private String currentSourceAddress;
	private String currentDestinationAddress;
	private Integer completedPickup;
	private Integer totalPickup;
	private Integer completedDelivery;
	private Integer totalDelivery;
	private Integer completedReturn;
	private Integer totalReturn;
	private String activeVehiclePurpose ;
	private String buildingName;
	private String orderType;

	public Long getActiveVehicleId() {
		return activeVehicleId;
	}

	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}

	public Long getDriverImageId() {
		return driverImageId;
	}

	public void setDriverImageId(Long driverImageId) {
		this.driverImageId = driverImageId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}



	public String getDriverRate() {
		return driverRate;
	}

	public void setDriverRate(String driverRate) {
		this.driverRate = driverRate;
	}

	public String getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}

	public String getCurrentSourceAddress() {
		return currentSourceAddress;
	}

	public void setCurrentSourceAddress(String currentSourceAddress) {
		this.currentSourceAddress = currentSourceAddress;
	}

	public String getCurrentDestinationAddress() {
		return currentDestinationAddress;
	}

	public void setCurrentDestinationAddress(String currentDestinationAddress) {
		this.currentDestinationAddress = currentDestinationAddress;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getCompletedPickup() {
		return completedPickup;
	}

	public Integer getTotalPickup() {
		return totalPickup;
	}
	
	public Integer getCompletedDelivery() {
		return completedDelivery;
	}

	public Integer getTotalDelivery() {
		return totalDelivery;
	}

	public void setCompletedPickup(Integer completedPickup) {
		this.completedPickup = completedPickup;
	}

	public void setTotalPickup(Integer totalPickup) {
		this.totalPickup = totalPickup;
	}

	public void setCompletedDelivery(Integer completedDelivery) {
		this.completedDelivery = completedDelivery;
	}

	public void setTotalDelivery(Integer totalDelivery) {
		this.totalDelivery = totalDelivery;
	}
	
	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Integer getCompletedReturn() {
		return completedReturn;
	}

	public void setCompletedReturn(Integer completedReturn) {
		this.completedReturn = completedReturn;
	}

	public Integer getTotalReturn() {
		return totalReturn;
	}

	public void setTotalReturn(Integer totalReturn) {
		this.totalReturn = totalReturn;
	}

	@Override
	public String toString() {
		return "ActiveOrderDto [activeVehicleId=" + activeVehicleId + ", driverImageId=" + driverImageId
				+ ", driverName=" + driverName + ", driverNumber=" + deviceNumber + ", driverRate=" + driverRate
				+ ", vehicleState=" + vehicleState + ", currentSourceAddress=" + currentSourceAddress
				+ ", currentDestinationAddress=" + currentDestinationAddress + ", completedPickup=" + completedPickup
				+ ", totalPickup=" + totalPickup + ", completedDelivery=" + completedDelivery + ", totalDelivery="
				+ totalDelivery + ", orderType=" + orderType + "]";
	}

	public String getActiveVehiclePurpose() {
		return activeVehiclePurpose;
	}

	public void setActiveVehiclePurpose(String activeVehiclePurpose) {
		this.activeVehiclePurpose = activeVehiclePurpose;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}





}
