package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "freelancer_driver", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "FreelancerDriverEntity.countAll", query = "SELECT COUNT(x) FROM FreelancerDriverEntity x") })
public class FreelancerDriverEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "national_id", length = 50)
	private String nationalId;

	@Temporal(TemporalType.DATE)
	@Column(name = "driving_license_exp_date")
	private Date drivingLicenseExpDate;

	@Column(name = "driving_license_id", length = 50)
	private String drivingLicenseId;

	@Column(name = "rating")
	private BigDecimal rating;

	@Column(name = "brand")
	private Long brand;

	@Column(name = "model")
	private Long model;

	@Column(name = "color", length = 50)
	private String color;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "plate", length = 50)
	private String plate;

	@Column(name = "chassis", length = 100)
	private String chassis;

	@Column(name = "weight")
	private BigDecimal weight;

	@Column(name = "purpose", length = 50)
	private String purpose;

	@Column(name = "motor", length = 50)
	private String motor;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "is_approved")
	private Boolean isApproved;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "version")
	private Long version;

	// @ManyToOne
	// @JoinColumn(name = "building_id", referencedColumnName = "building_id")
	// private BuildingEntity building;

	@Column(name = "building_id", nullable = true, updatable = true)
	private Long buildingId;

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "image_id", nullable = false)
	private Long nationalIdImage;

	public FreelancerDriverEntity() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNationalId() {
		return this.nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public Date getDrivingLicenseExpDate() {
		return this.drivingLicenseExpDate;
	}

	public void setDrivingLicenseExpDate(Date drivingLicenseExpDate) {
		this.drivingLicenseExpDate = drivingLicenseExpDate;
	}

	public String getDrivingLicenseId() {
		return this.drivingLicenseId;
	}

	public void setDrivingLicenseId(String drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Long getBrand() {
		return this.brand;
	}

	public void setBrand(Long brand) {
		this.brand = brand;
	}

	public Long getModel() {
		return this.model;
	}

	public void setModel(Long model) {
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

	public Boolean getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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

	public Long getNationalIdImage() {
		return this.nationalIdImage;
	}

	public void setNationalIdImage(Long nationalIdImage) {
		this.nationalIdImage = nationalIdImage;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
