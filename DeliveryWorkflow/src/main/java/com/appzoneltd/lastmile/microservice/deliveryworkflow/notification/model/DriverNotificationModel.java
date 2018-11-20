package com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DriverNotificationModel {

    private Long vehicleId;
    private Long hubId;
    private Long driverId;
    private Long requestId;
    private String pickupLatitude;
    private String pickupLongitude;
    private String driverName;
    private String driverPhoneNumber;
    private BigDecimal driverRating;
    private Long driverImageId;
    private String vehicleModel;
    private String vehiclePlateNumber;
    private String confirmationCode;
    private String requestType;

}
