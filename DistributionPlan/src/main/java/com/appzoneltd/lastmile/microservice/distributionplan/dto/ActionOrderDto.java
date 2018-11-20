package com.appzoneltd.lastmile.microservice.distributionplan.dto;

public class ActionOrderDto {
	private Long orderId;
	private String orderType;
	private String orderTimeFrom;
	private String orderTimeTo;
	private String packageType;
	private String address;

	public Long getOrderId() {
		return orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getPackageType() {
		return packageType;
	}

	public String getAddress() {
		return address;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderTimeFrom() {
		return orderTimeFrom;
	}

	public void setOrderTimeFrom(String orderTimeFrom) {
		this.orderTimeFrom = orderTimeFrom;
	}

	public String getOrderTimeTo() {
		return orderTimeTo;
	}

	public void setOrderTimeTo(String orderTimeTo) {
		this.orderTimeTo = orderTimeTo;
	}

}
