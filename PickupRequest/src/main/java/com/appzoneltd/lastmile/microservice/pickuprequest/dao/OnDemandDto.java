package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

public class OnDemandDto {
	
	private Long pickupRequestId ;
	
	private String pickupFormatedAddress;
	
	private String packageType;
	
	private String actualWeight;
	
	private String receivedFrom;
	
	private OrderStatus orderStatus ;
	
	private String buildingName ;
	
	private Long hubId ;

	public Long getPickupRequestId() {
		return pickupRequestId;
	}

	public String getPickupFormatedAddress() {
		return pickupFormatedAddress;
	}

	public String getPackageType() {
		return packageType;
	}

	public String getActualWeight() {
		return actualWeight;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public void setPickupFormatedAddress(String pickupFormatedAddress) {
		this.pickupFormatedAddress = pickupFormatedAddress;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public void setActualWeight(String actualWeight) {
		this.actualWeight = actualWeight;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}


	
}
