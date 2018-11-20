package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "country", schema = "lastmile_core")
public class CountryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "name_ar", nullable = false, length = 50)
	private String nameAr;

	@Column(name = "name_en", nullable = false, length = 50)
	private String nameEn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@OneToMany(mappedBy = "country", targetEntity = CityEntity.class)
	private List<CityEntity> listOfCity;

//	@OneToMany(mappedBy = "country", targetEntity = BuildingEntity.class)
//	private List<BuildingEntity> listOfBuilding;
}
