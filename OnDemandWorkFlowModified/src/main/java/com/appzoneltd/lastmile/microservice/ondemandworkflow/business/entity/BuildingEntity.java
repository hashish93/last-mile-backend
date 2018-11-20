package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "building", schema = "lastmile_core")
public class BuildingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "building_id", nullable = false)
	private Long buildingId;

	@Column(name = "buildingname", nullable = false, length = 100)
	private String buildingname;

	@Column(name = "buildingnumber", length = 50)
	private String buildingnumber;

	@Column(name = "area", length = 100)
	private String area;

	@Column(name = "street", length = 100)
	private String street;

	@Column(name = "waselcode", length = 100)
	private String waselcode;

	@Column(name = "longitude", length = 2147483647)
	private String longitude;

	@Column(name = "latitude", length = 2147483647)
	private String latitude;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "city_id")
	private Long cityId;

	// @OneToMany(mappedBy = "building", targetEntity =
	// BuildingServingAreaEntity.class)
	// private List<BuildingServingAreaEntity> listOfBuildingServingArea;

}
