package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

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

	@ManyToOne
	@JoinColumn(name = "building_type_id", referencedColumnName = "building_type_id")
	private BuildingTypeEntity buildingType;

	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "city_id")
	private CityEntity city;

	@OneToMany(mappedBy = "building", targetEntity = VehicleEntity.class)
	private List<VehicleEntity> listOfVehicle;

	@OneToMany(mappedBy = "building", targetEntity = BuildingServingAreaEntity.class)
	private List<BuildingServingAreaEntity> listOfBuildingServingArea;

	// @OneToMany(mappedBy="building",
	// targetEntity=HubConfigurationEntity.class)
	// private List<HubConfigurationEntity> listOfHubConfiguration;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private CountryEntity country;

	@ManyToOne
	@JoinColumn(name = "country_code_id", referencedColumnName = "id")
	private CountryCodesEntity countryCodes;

	@OneToMany(mappedBy = "building", targetEntity = UserHubEntity.class)
	private List<UserHubEntity> listOfUserHub;

}
