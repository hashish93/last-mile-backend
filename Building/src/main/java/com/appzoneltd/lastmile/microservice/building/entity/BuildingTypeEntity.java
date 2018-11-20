package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "building_type", schema = "lastmile_core")
public class BuildingTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "building_type_id", nullable = false)
	private Long buildingTypeId;

	@Column(name = "type", nullable = false, length = 50)
	private String type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

//	@OneToMany(mappedBy = "buildingType", targetEntity = BuildingEntity.class)
//	private List<BuildingEntity> listOfBuilding;

}
