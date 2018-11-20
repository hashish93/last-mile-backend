package com.appzoneltd.lastmile.microservice.countryandcity.dto;

import java.io.Serializable;

/**
 * @author alaa.nabil
 *
 */

public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3976540102908684295L;

	private Long cityId;

	private String name;

	private Long countryId;

	public City() {
	}
	
	

	public City(Long cityId, String name, Long countryId) {
		super();
		this.cityId = cityId;
		this.name = name;
		this.countryId = countryId;
	}



	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

}
