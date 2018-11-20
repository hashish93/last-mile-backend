package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "driver_rating_details", schema = "reporting")
public class DriverRatingDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "driver_id")
	private Long driverId;

	@Column(name = "driver_type", length = 2147483647)
	private String driverType;
	
	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "driver_rating")
	private BigDecimal driverRating;
}
