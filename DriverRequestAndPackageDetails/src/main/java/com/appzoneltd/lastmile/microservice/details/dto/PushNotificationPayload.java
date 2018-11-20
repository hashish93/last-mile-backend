package com.appzoneltd.lastmile.microservice.details.dto;

public class PushNotificationPayload {
    private Long vehicleId;
    private Long hubId;
    private Long driverId;
    private String pickupLatitude;
    private String pickupLongitude;
    private String driverName;
    private String driverPhoneNumber;
    private Long driverRating;
    private Long driverImageId;
    private String vehicleModel;
    private String vehiclePlateNumber;
    private Long requestId;
    private String requestType;

    public PushNotificationPayload(Long vehicleId, Long hubId, Long driverId, String pickupLatitude,
                                   String pickupLongitude, String driverName, String driverPhoneNumber, 
                                   Long driverRating, Long driverImageId,
                                   String vehicleModel, String vehiclePlateNumber, Long requestId, String requestType) {
        super();
        this.vehicleId = vehicleId;
        this.hubId = hubId;
        this.driverId = driverId;
        this.pickupLatitude = pickupLatitude;
        this.pickupLongitude = pickupLongitude;
        this.driverName = driverName;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverRating = driverRating;
        this.driverImageId = driverImageId;
        this.vehicleModel = vehicleModel;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.requestId = requestId;
        this.requestType = requestType;
    }

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(String pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public String getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(String pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}

	public Long getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(Long driverRating) {
		this.driverRating = driverRating;
	}

	public Long getDriverImageId() {
		return driverImageId;
	}

	public void setDriverImageId(Long driverImageId) {
		this.driverImageId = driverImageId;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehiclePlateNumber() {
		return vehiclePlateNumber;
	}

	public void setVehiclePlateNumber(String vehiclePlateNumber) {
		this.vehiclePlateNumber = vehiclePlateNumber;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


}
