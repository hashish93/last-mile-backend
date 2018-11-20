package com.appzoneltd.lastmile.microservice.employee.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
@Data
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
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private DriverEntity driver;

}
