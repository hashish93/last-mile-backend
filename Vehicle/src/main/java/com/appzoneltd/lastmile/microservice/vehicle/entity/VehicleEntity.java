package com.appzoneltd.lastmile.microservice.vehicle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "vehicle", schema = "lastmile_core")

public class VehicleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vehicle_id", nullable = false)
	private Long vehicleId;

	@Column(name = "brand", nullable = false, length = 50)
	private String brand;

	@Column(name = "model", nullable = false, length = 50)
	private String model;

	@Column(name = "color", length = 50)
	private String color;

	@Column(name = "plate", nullable = false, length = 50)
	private String plate;

	@Column(name = "chassis", nullable = false, length = 100)
	private String chassis;

	@Column(name = "weight", nullable = false)
	private BigDecimal weight;

	@Column(name = "purpose", length = 50)
	private String purpose;

	@Column(name = "motor", length = 50)
	private String motor;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "building_id", referencedColumnName = "building_id")
	private BuildingEntity building;

	@ManyToOne
	@JoinColumn(name = "vehicle_type_id", referencedColumnName = "vehicle_type_id")
	private VehicleTypeEntity vehicleType;

}
