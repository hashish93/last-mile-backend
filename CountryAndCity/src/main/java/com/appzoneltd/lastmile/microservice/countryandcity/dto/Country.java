package com.appzoneltd.lastmile.microservice.countryandcity.dto;

import java.io.Serializable;

/**
 * @author alaa.nabil
 *
 */
public class Country implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 521598873984551364L;

	private Long countryId;

	private String name;

	public Country() {
	}

	public Country(Long countryId, String name) {
		super();
		this.countryId = countryId;
		this.name = name;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}