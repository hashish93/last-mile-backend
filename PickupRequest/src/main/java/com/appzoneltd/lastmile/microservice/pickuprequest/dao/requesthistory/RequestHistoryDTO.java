package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

public class RequestHistoryDTO {

	private long PickupRequestId;

	private String requestType;

	private String packageType;

	private String pickupStatus;
	
	private String cancellationReason ;
	
	private String hubName;

	public RequestHistoryDTO(long pickupRequestId, String requestType, String packageType, String pickupStatus , String cancellationReason , String hubName) {
		super();
		PickupRequestId = pickupRequestId;
		this.requestType = requestType;
		this.packageType = packageType;
		this.pickupStatus = pickupStatus;
		this.cancellationReason=cancellationReason;
		this.hubName = hubName;
	}

	public long getPickupRequestId() {
		return PickupRequestId;
	}

	public void setPickupRequestId(long pickupRequestId) {
		PickupRequestId = pickupRequestId;
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

	public String getPickupStatus() {
		return pickupStatus;
	}

	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
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
