package com.appzoneltd.lastmile.microservice.distributionplan.dao;

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

	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private DriverEntity driver;

	@OneToMany(mappedBy = "activeVehicle", targetEntity = PlanOrderEntity.class, fetch = FetchType.EAGER)
	private List<PlanOrderEntity> listOfPlanOrders;

	@ManyToOne
	@JoinColumn(name = "device_id", referencedColumnName = "device_id")
	private DevicesEntity devices;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
	private VehicleEntity vehicle;

	@ManyToOne
	@JoinColumn(name = "work_shift_id", referencedColumnName = "id")
	private WorkShiftEntity workShift;

	public ActiveVehicleEntity() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PlanOrderEntity> getListOfPlanOrders() {
		return this.listOfPlanOrders;
	}

	public void setListOfPlanOrders(List<PlanOrderEntity> listOfPlanOrders) {
		this.listOfPlanOrders = listOfPlanOrders;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public DriverEntity getDriver() {
		return driver;
	}

	public void setDriver(DriverEntity driver) {
		this.driver = driver;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

	public WorkShiftEntity getWorkShift() {
		return workShift;
	}

	public void setWorkShift(WorkShiftEntity workShift) {
		this.workShift = workShift;
	}

	public DevicesEntity getDevices() {
		return devices;
	}

	public void setDevices(DevicesEntity devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "ActiveVehicleEntity [id=" + id + ", active=" + active + ", version=" + version + ", created=" + created
				+ ", driver=" + driver + ", listOfPlanOrders=" + listOfPlanOrders + ", devices=" + devices
				+ ", vehicle=" + vehicle + ", workShift=" + workShift + "]";
	}
}
