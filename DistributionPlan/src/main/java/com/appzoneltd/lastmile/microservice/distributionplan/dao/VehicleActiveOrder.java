package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.util.List;

import lombok.Data;

@Data
public class VehicleActiveOrder {

	private Long activeVehicleId;
	private Long driverImageId;
	private String driverName;
	private String deviceNumber;
	private String driverRate;
	private String vehicleState;
	private String location;
	private String ActiveVehiclePurpose;
	private List<VehicleOrder> VehicleOrders;
	private String activeVehicleAddress;



}
