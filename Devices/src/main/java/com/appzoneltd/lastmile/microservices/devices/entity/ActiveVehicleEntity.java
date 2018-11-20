package com.appzoneltd.lastmile.microservices.devices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
public class ActiveVehicleEntity {

	@Id
	@Column(name = "id", nullable = false)
	private Long id ; 
	
	@Column(name = "device_id", nullable = false)
	private Long deviceId ;
	
	
	
	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
}
