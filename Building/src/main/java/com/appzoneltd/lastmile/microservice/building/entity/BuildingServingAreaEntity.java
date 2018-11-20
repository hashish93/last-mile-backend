package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "building_serving_area", schema = "lastmile_core")

public class BuildingServingAreaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "building_serving_id", nullable = false)
	private Long buildingServingId;

	@Column(name = "latitude", length = 2147483647)
	private String latitude;

	@Column(name = "longitude", length = 2147483647)
	private String longitude;

	@Temporal(TemporalType.TIME)
	@Column(name = "created")
	private Date created;

	@ManyToOne
	@JoinColumn(name = "building_id", referencedColumnName = "building_id")
	private BuildingEntity building;
}
