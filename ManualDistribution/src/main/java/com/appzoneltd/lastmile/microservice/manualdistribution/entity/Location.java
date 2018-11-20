package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "building", schema = "lastmile_core")
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long hubId;
	
	private String longitude;

	private String latitude;
	

	@Id
	@Column(name = "building_id")
	public Long getHubId() {
		return hubId;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
