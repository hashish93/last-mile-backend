package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicle", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "VehicleEntity.countAll", query = "SELECT COUNT(x) FROM VehicleEntity x") })
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
	@OneToMany(mappedBy = "vehicle", targetEntity = ActiveVehicleEntity.class)
	private List<ActiveVehicleEntity> listOfActiveVehicle;

	@Column(name = "building_id", nullable = false)
	private Long buildingId;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "building_id", referencedColumnName = "building_id")
	 * private BuildingEntity building;
	 */

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	@ManyToOne
	@JoinColumn(name = "vehicle_type_id", referencedColumnName = "vehicle_type_id")
	private VehicleTypeEntity vehicleType;

	public VehicleEntity() {
		super();
	}

	public Long getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getChassis() {
		return this.chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMotor() {
		return this.motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<ActiveVehicleEntity> getListOfActiveVehicle() {
		return this.listOfActiveVehicle;
	}

	public void setListOfActiveVehicle(List<ActiveVehicleEntity> listOfActiveVehicle) {
		this.listOfActiveVehicle = listOfActiveVehicle;
	}

	public VehicleTypeEntity getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleTypeEntity vehicleType) {
		this.vehicleType = vehicleType;
	}

}
