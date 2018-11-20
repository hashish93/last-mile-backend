package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "country_codes", schema = "lastmile_core")
public class CountryCodesEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "iso", nullable = false, length = 2147483647)
	private String iso;

	@Column(name = "name", nullable = false, length = 80)
	private String name;

	@Column(name = "nicename", nullable = false, length = 80)
	private String nicename;

	@Column(name = "iso3", length = 2147483647)
	private String iso3;

	@Column(name = "numcode")
	private Long numcode;

	@Column(name = "phonecode", nullable = false)
	private Long phonecode;

//	@OneToMany(mappedBy = "countryCodes", targetEntity = UsersEntity.class)
//	private List<UsersEntity> listOfUsers;
//
//	@OneToMany(mappedBy = "countryCodes", targetEntity = BuildingEntity.class)
//	private List<BuildingEntity> listOfBuilding;
}
