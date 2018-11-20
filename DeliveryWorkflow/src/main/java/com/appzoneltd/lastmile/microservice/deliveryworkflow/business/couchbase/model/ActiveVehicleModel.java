package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnore;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;

@Data	
public class ActiveVehicleModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	private Long _ID;
	private Long vehicleId;
	private Long hubId;
	private Long deviceId;
	private String vehicleStatus = "";
	private OrderLocation location;
	private Long driverId;
	private String driverName;
	private String driverNumber;
	private int rating;
	private String firebaseToken;
	private String purpose;
	private Integer capacity;
	private List<JobOrder> jobOrders = new LinkedList<>();
	private String _class;
	private Long workShiftId;
	private Long workShiftFrom;
	private Long workShiftTo;
	private String activeVehicleAddress;

	

}
