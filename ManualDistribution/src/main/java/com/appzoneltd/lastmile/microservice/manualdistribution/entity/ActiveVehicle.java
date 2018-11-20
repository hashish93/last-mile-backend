package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.io.Serializable;


public class ActiveVehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long ActiveVehicleId;
	
	private Long vehicleId;
	
	private Integer capacity;

	private String purpose ;
	
	private Long workShiftFrom;

	private Long workShiftTo;

	private Long hubId;

	public Integer getCapacity() {
		return capacity;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Long getWorkShiftFrom() {
		return workShiftFrom;
	}

	public Long getWorkShiftTo() {
		return workShiftTo;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setWorkShiftFrom(Long workShiftFrom) {
		this.workShiftFrom = workShiftFrom;
	}

	public void setWorkShiftTo(Long workShiftTo) {
		this.workShiftTo = workShiftTo;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Long getActiveVehicleId() {
		return ActiveVehicleId;
	}

	public void setActiveVehicleId(Long activeVehicleId) {
		ActiveVehicleId = activeVehicleId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "ActiveVehicle{" +
				"ActiveVehicleId=" + ActiveVehicleId +
				", vehicleId=" + vehicleId +
				", capacity=" + capacity +
				", purpose='" + purpose + '\'' +
				", workShiftFrom=" + workShiftFrom +
				", workShiftTo=" + workShiftTo +
				", hubId=" + hubId +
				'}';
	}
}
