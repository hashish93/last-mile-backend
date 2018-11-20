package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nearby_vehicle", schema = "lastmile_request")
public class NearByVehicleModel {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "package_id", nullable = true)
	private Long packageId;

	@Column(name = "active_vehicle_id", nullable = true)
	private Long activeVehicleId;

	@Column(name = "request_id", nullable = true)
	private Long requestId;

	@Column(name = "response", nullable = true)
	private String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Long getActiveVehicleId() {
		return activeVehicleId;
	}

	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
