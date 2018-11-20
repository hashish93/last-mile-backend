package com.appzoneltd.lastmile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EurekaInstanceInformation {
	
	private EurekaApplication application;

	public EurekaApplication getApplication() {
		return application;
	}

	public void setApplication(EurekaApplication application) {
		this.application = application;
	}


}
