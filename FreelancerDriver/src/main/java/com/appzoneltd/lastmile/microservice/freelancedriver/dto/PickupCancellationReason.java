package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.io.Serializable;

public class PickupCancellationReason implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 877765411050054156L;
	private Long id;
	private String name;

	public PickupCancellationReason() {
	}

	public PickupCancellationReason(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
