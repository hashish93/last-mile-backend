package com.appzoneltd.lastmile.microservice.createsearchtopic.dao;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Query implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long hubId;

	private Long vehicleId;

	private List<String> vehicleStatus;

	private List<String> orderType;

	private boolean count;

	private List<String> orderStatus;

	private boolean vehicleData;

	private boolean orderData;

	/**
	 * 
	 */
	public Query() {

	}

	public Query(Long hubId, Long vehicleId, List<String> vehicleStatus, List<String> orderType, boolean count,
			List<String> orderStatus, boolean vehicleData, boolean orderData) {
		this.hubId = hubId;
		this.vehicleId = vehicleId;
		this.vehicleStatus = vehicleStatus;
		this.orderType = orderType;
		this.count = count;
		this.orderStatus = orderStatus;
		this.vehicleData = vehicleData;
		this.orderData = orderData;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<String> getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(List<String> vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public List<String> getOrderType() {
		return orderType;
	}

	public void setOrderType(List<String> orderType) {
		this.orderType = orderType;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public List<String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isVehicleData() {
		return vehicleData;
	}

	public void setVehicleData(boolean vehicleData) {
		this.vehicleData = vehicleData;
	}

	public boolean isOrderData() {
		return orderData;
	}

	public void setOrderData(boolean orderData) {
		this.orderData = orderData;
	}

}
