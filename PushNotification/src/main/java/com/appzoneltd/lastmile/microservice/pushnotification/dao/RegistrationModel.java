package com.appzoneltd.lastmile.microservice.pushnotification.dao;

import java.io.Serializable;

import com.couchbase.client.java.repository.annotation.Id;

/**
 * @author alaa.nabil
 *
 */
public class RegistrationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long _ID;
	private Long driverId;
	private String firebaseToken;

	/**
	 * 
	 */
	public RegistrationModel() {
	}

	public Long getId() {
		return _ID;
	}

	public void setId(Long id) {
		this._ID = id;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

}
