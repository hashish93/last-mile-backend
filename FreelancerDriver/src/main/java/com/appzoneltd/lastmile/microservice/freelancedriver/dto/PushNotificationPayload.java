package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

public class PushNotificationPayload {
    private long vehicleId;
    private long hubId;
    private long driverId;
    private String pickupLatitude;
    private String pickupLongitude;
    private String driverName;
    private String driverPhoneNumber;
    private long driverRating;
    private long driverImageId;
    private String vehicleModel;
    private String vehiclePlateNumber;
    private Long requestId;
    private String requestType;

    public PushNotificationPayload(long vehicleId, long hubId, long driverId, String pickupLatitude,
                                   String pickupLongitude, String driverName, String driverPhoneNumber, long driverRating, long driverImageId,
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getHubId() {
        return hubId;
    }

    public void setHubId(long hubId) {
        this.hubId = hubId;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
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

    public long getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(long driverRating) {
        this.driverRating = driverRating;
    }

    public long getDriverImageId() {
        return driverImageId;
    }

    public void setDriverImageId(long driverImageId) {
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

}
