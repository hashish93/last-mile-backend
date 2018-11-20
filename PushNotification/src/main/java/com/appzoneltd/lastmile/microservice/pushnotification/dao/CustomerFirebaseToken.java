package com.appzoneltd.lastmile.microservice.pushnotification.dao;

import com.couchbase.client.java.repository.annotation.Id;

import java.io.Serializable;

public class CustomerFirebaseToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437513057547486290L;

	@Id
	private Long _ID;
	private Long userId;
	private String firebaseToken;

	public CustomerFirebaseToken() {
	}

	/**
	 * @param _ID
	 * @param userId
	 * @param firebaseToken
	 */
	public CustomerFirebaseToken(Long _ID, Long userId, String firebaseToken) {
		super();
		this._ID = _ID;
		this.userId = userId;
		this.firebaseToken = firebaseToken;
	}

	/**
	 * @return the _ID
	 */
	public Long get_ID() {
		return _ID;
	}

	/**
	 * @param _ID
	 *            the _ID to set
	 */
	public CustomerFirebaseToken set_ID(Long _ID) {
		this._ID = _ID;
		return this;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the fireBaseToken
	 */
	public String getFirebaseToken() {
		return firebaseToken;
	}

	/**
	 * @param firebaseToken
	 *            the fireBaseToken to set
	 */
	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

}
