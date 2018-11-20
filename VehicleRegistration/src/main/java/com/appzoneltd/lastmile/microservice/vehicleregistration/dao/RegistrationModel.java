package com.appzoneltd.lastmile.microservice.vehicleregistration.dao;

import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author alaa.nabil
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
	private String activeVehicleAddress;

	public String getActiveVehicleAddress() {
		return activeVehicleAddress;
	}

	public void setActiveVehicleAddress(String activeVehicleAddress) {
		this.activeVehicleAddress = activeVehicleAddress;
	}

	private String userType;

	private Boolean isWorking;

	/**
	 *
	 */
	public RegistrationModel() {
	}

	public RegistrationModel(Long _ID, Long vehicleId, Long hubId, Long deviceId, String vehicleStatus,
			Location location, Long driverId, String driverName, String driverNumber, int rating, String firebaseToken,
			String purpose, Integer capacity, List<JobOrder> jobOrders, String _class, Long workShiftId,
			Long workShiftFrom, Long workShiftTo, String usertype, Boolean isWorking) {
		super();
		this._ID = _ID;
		this.vehicleId = vehicleId;
		this.hubId = hubId;
		this.deviceId = deviceId;
		this.vehicleStatus = vehicleStatus;
		this.location = location;
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverNumber = driverNumber;
		this.rating = rating;
		this.firebaseToken = firebaseToken;
		this.purpose = purpose;
		this.capacity = capacity;
		this.jobOrders = jobOrders;
		this._class = _class;
		this.workShiftId = workShiftId;
		this.workShiftFrom = workShiftFrom;
		this.workShiftTo = workShiftTo;

		this.userType = usertype;
		this.isWorking = isWorking;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long get_ID() {
		return _ID;
	}

	public void set_ID(Long _ID) {
		this._ID = _ID;
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

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<JobOrder> getJobOrders() {
		return jobOrders;
	}

	public void setJobOrders(List<JobOrder> jobOrders) {
		this.jobOrders = jobOrders;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public Long getWorkShiftId() {
		return workShiftId;
	}

	public void setWorkShiftId(Long workShiftId) {
		this.workShiftId = workShiftId;
	}

	public Long getWorkShiftFrom() {
		return workShiftFrom;
	}

	public void setWorkShiftFrom(Long workShiftFrom) {
		this.workShiftFrom = workShiftFrom;
	}

	public Long getWorkShiftTo() {
		return workShiftTo;
	}

	public void setWorkShiftTo(Long workShiftTo) {
		this.workShiftTo = workShiftTo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

}
