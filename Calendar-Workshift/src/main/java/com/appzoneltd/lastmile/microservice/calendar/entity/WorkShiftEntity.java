package com.appzoneltd.lastmile.microservice.calendar.entity;

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

@Data
@Entity
@Table(name = "work_shift", schema = "lastmile_core")
public class WorkShiftEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_from", nullable = false)
	private Date dateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_to", nullable = false)
	private Date dateTo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = true)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;

}
