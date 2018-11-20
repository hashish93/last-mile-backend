package com.appzoneltd.lastmile.microservice.employee.entity;

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
@Table(name = "driver", schema = "lastmile_core")

public class DriverEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "driving_license_exp_date")
	private Date drivingLicenseExpDate;

	@Column(name = "driving_license_id", length = 50)
	private String drivingLicenseId;

	@Column(name = "rating")
	private BigDecimal rating;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "driving_license_type_id", referencedColumnName = "driving_license_type_id")
	private DrivingLicenseTypeEntity drivingLicenseType;
}
