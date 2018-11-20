package com.appzoneltd.lastmile.microservice.returnworkflow.notification.model;

import lombok.Data;

@Data
public class DriverNotificationModel {

	private Long vehicleId;
	private Long hubId;
	private Long driverId;
	private Long requestId;
	//	private String returnLatitude;
	//	private String returnLongitude;
	private String pickupLatitude;
	private String pickupLongitude;
	private String driverName;
	private String driverPhoneNumber;
	private Long driverRating;
	private Long driverImageId;
	private String vehicleModel;
	private String vehiclePlateNumber;
	private String requestType;

}
