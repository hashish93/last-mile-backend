package com.appzoneltd.lastmile.microservice.activevehicle.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ActiveVehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 879283515709162077L;

	private Long id;

	@NotNull(message = "activevehicle.notnull")
	private Long vehicleId;

	private boolean active;

	private long version;

	@NotNull(message = "activevehicle.notnull")
	private Long driverId;

	@NotNull(message = "activevehicle.notnull")
	private Long deviceId;

//	@NotNull(message = "activevehicle.notnull")
//	private Long calendarId;

	@NotNull(message = "activevehicle.notnull")
	private Long workShiftId;
	
	//@NotNull(message = "activevehicle.notnull")
	private Long hubId ;

	private Date created;

	public ActiveVehicle() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

//	public Long getCalendarId() {
//		return calendarId;
//	}
//
//	public void setCalendarId(Long calendarId) {
//		this.calendarId = calendarId;
//	}

	public Long getWorkShiftId() {
		return workShiftId;
	}

	public void setWorkShiftId(Long workShiftId) {
		this.workShiftId = workShiftId;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
