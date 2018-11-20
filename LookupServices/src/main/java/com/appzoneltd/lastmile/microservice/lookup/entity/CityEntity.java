package com.appzoneltd.lastmile.microservice.lookup.entity;

import java.io.Serializable;
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
@Table(name = "city", schema = "lastmile_core")
public class CityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "city_id", nullable = false)
	private Long cityId;

	@Column(name = "name_ar", nullable = false, length = 100)
	private String nameAr;

	@Column(name = "name_en", nullable = false, length = 100)
	private String nameEn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private CountryEntity country;

}
