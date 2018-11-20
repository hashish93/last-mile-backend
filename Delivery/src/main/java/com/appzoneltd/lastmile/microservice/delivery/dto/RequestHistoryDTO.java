package com.appzoneltd.lastmile.microservice.delivery.dto;

public class RequestHistoryDTO {

	private long deliveryRequestId;

	private String requestType;

	private String packageType;

	private String deliveryStatus;
	
	private String cancellationReason ;
	
	private String hubName;

	public RequestHistoryDTO(long deliveryRequestId, String requestType, String packageType, String deliveryStatus, String cancellationReason , String hubName) {
		super();
		this.deliveryRequestId = deliveryRequestId;
		this.requestType = requestType;
		this.packageType = packageType;
		this.deliveryStatus = deliveryStatus;
		this.cancellationReason=cancellationReason;
		this.hubName = hubName;
	}

	public RequestHistoryDTO() {
	}

	public long getDeliveryRequestId() {
		return deliveryRequestId;
	}

	public void setDeliveryRequestId(long deliveryRequestId) {
		this.deliveryRequestId = deliveryRequestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public String getHubName() {
		return hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

}
