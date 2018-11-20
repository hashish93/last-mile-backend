/*
 * Created on 3 Jul 2017 ( Time 15:32:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.ums.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * Persistent class for entity stored in table "building"
 *
 * @author Telosys Tools Generator
 *
 */

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

	@Column(name = "phone_number", length = 50)
	private String phoneNumber;

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

	@Column(name = "version", nullable = false)
	private Long version;

	@OneToMany(mappedBy = "building", targetEntity = RoleEntity.class)
	private List<RoleEntity> listOfRoles;

	@OneToMany(mappedBy = "building", targetEntity = UserHubEntity.class)
	private List<UserHubEntity> userHubs;

}
