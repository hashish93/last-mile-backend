package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model;

import lombok.Data;

@Data
public class ServingArealocation {

	private String latitude;

	private String longitude;

	public ServingArealocation() {}

	public ServingArealocation(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
