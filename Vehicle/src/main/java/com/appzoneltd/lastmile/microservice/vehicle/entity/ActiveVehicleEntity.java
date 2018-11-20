package com.appzoneltd.lastmile.microservice.vehicle.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
public class ActiveVehicleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@Column(name = "version", nullable = false)
	private Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
	private VehicleEntity vehicle;

}
