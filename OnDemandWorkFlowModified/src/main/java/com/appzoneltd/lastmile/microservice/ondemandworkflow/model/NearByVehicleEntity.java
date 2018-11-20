package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="nearby_vehicle",schema="lastmile_request")
@Data
public class NearByVehicleEntity {

	@Id
    @Column(name="id")
	private Long id;
	
	@Column(name="package_id",nullable =true)
	private Long packageId;
	
	@Column(name="active_vehicle_id",nullable =true)
	private Long activeVehicleId;
	
	@Column(name="request_id",nullable =true)
	private Long requestId;
	
	@Column(name="response",nullable =true)
	private String response;
	
	
}
