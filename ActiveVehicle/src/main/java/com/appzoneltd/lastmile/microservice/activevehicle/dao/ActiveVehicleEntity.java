/*
 * Created on 20 Nov 2016 ( Time 10:17:25 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.activevehicle.dao;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * @author 3laa Nabil
 *
 */
@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "ActiveVehicleEntity.countAll", query = "SELECT COUNT(x) FROM ActiveVehicleEntity x") })
public class ActiveVehicleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285611241140505386L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "active", nullable = true)
	private boolean active;

	@Column(name = "version", nullable = true)
	@Version
	private long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created = new Date();

	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
	private VehicleEntity vehicle;

	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private DriverEntity driver;

	@ManyToOne
	@JoinColumn(name = "device_id", referencedColumnName = "device_id")
	private DevicesEntity devices;

//	@ManyToOne
//	@JoinColumn(name = "calendar_id", referencedColumnName = "id")
//	private CalendarEntity calendar;

	@ManyToOne
	@JoinColumn(name = "work_shift_id", referencedColumnName = "id")
	private WorkShiftEntity workShift;

	public ActiveVehicleEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setDriver(DriverEntity driver) {
		this.driver = driver;
	}

	public DriverEntity getDriver() {
		return this.driver;
	}

	public void setDevices(DevicesEntity devices) {
		this.devices = devices;
	}

	public DevicesEntity getDevices() {
		return this.devices;
	}

//	public void setCalendar(CalendarEntity calendar) {
//		this.calendar = calendar;
//	}
//
//	public CalendarEntity getCalendar() {
//		return this.calendar;
//	}

	public void setWorkShift(WorkShiftEntity workShift) {
		this.workShift = workShift;
	}

	public WorkShiftEntity getWorkShift() {
		return this.workShift;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
