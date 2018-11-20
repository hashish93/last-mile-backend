package com.appzoneltd.lastmile.microservice.employee.dao;

import javax.validation.constraints.NotNull;

import com.appzoneltd.lastmile.microservice.enums.UserType;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;

public class FindByIdControllerRequestObjectModel extends EndPointParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5287727824884832464L;
	@NotNull
	private UserType userType;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
