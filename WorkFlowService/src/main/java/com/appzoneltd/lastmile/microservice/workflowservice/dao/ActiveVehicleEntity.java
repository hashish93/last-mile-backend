package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "ActiveVehicleEntity.countAll", query = "SELECT COUNT(x) FROM ActiveVehicleEntity x") })
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

	
	@Column(name = "driver_id")
	private Long driverId;


}
