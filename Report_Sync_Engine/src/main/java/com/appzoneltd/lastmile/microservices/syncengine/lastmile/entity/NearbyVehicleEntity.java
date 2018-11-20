package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "nearby_vehicle", schema = "lastmile_request")
public class NearbyVehicleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "package_id")
	private Long packageId;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "active_vehicle_id")
	private Long activeVehicleId;

	@Column(name = "response", length = 100)
	private String response;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

}
