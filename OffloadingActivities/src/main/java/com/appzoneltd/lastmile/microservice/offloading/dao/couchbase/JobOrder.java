/**
 * Sep 26, 201612:40:36 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.offloading.dao.couchbase;

import java.io.Serializable;

/**
 * @author alaa.nabil
 *
 */
public class JobOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5640983377604419424L;
	private Long jobOrderId;
	private String orderType;
	private String customerName;
	private String customerPhone;
	private Location originalLocation;
	private Location actualLocation;
	private String recepientName;
	private String recepientPhone;
	private String orderStatus;
	private int priority;
	private Integer actualWeight;
	private Boolean currentOrder;
    private String activeVehicleAddress;

	public JobOrder() {
	}

	public JobOrder(Long jobOrderId, String orderType, String customerName, String customerPhone,
			Location originalLocation, Location actualLocation, String recepientName, String recepientPhone,
			String orderStatus, int priority, Integer actualWeight) {
		super();
		this.jobOrderId = jobOrderId;
		this.orderType = orderType;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.originalLocation = originalLocation;
		this.actualLocation = actualLocation;
		this.recepientName = recepientName;
		this.recepientPhone = recepientPhone;
		this.orderStatus = orderStatus;
		this.priority = priority;
		this.actualWeight = actualWeight;
	}

	public Long getJobOrderId() {
		return jobOrderId;
	}

	public void setJobOrderId(Long jobOrderId) {
		this.jobOrderId = jobOrderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Location getOriginalLocation() {
		return originalLocation;
	}

	public void setOriginalLocation(Location originalLocation) {
		this.originalLocation = originalLocation;
	}

	public Location getActualLocation() {
		return actualLocation;
	}

	public void setActualLocation(Location actualLocation) {
		this.actualLocation = actualLocation;
	}

	public String getRecepientName() {
		return recepientName;
	}

	public void setRecepientName(String recepientName) {
		this.recepientName = recepientName;
	}

	public String getRecepientPhone() {
		return recepientPhone;
	}

	public void setRecepientPhone(String recepientPhone) {
		this.recepientPhone = recepientPhone;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Integer getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Integer actualWeight) {
		this.actualWeight = actualWeight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Boolean currentOrder) {
		this.currentOrder = currentOrder;
	}

    public String getActiveVehicleAddress() {
        return activeVehicleAddress;
    }

    public void setActiveVehicleAddress(String activeVehicleAddress) {
        this.activeVehicleAddress = activeVehicleAddress;
    }
}
