package com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao;

import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author alaa.nabil
 */
@Data
public class RegistrationModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long _ID;
	private Long vehicleId;
	private Long hubId;
	private Long deviceId;
	private String vehicleStatus = "";
	private Location location;
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
	private String userType;
	private Boolean isWorking;
	private String activeVehicleAddress;

}
