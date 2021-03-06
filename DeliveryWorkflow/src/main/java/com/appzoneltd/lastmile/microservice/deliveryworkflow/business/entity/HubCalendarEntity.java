package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "hub_calendar", schema = "lastmile_core")
public class HubCalendarEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "status", length = 2147483647)
	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name = "created")
	private Date created;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;

	@ManyToOne
	@JoinColumn(name = "calendar_id", referencedColumnName = "id")
	private CalendarEntity calendar;

	@Column(name = "version", length = 2147483647)
	private Long version;

	
}
