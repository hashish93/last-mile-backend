package com.appzoneltd.lastmile.microservice.pushnotification.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.Serializable;
import java.util.Objects;

public class FirebaseTokenObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3536068062842582562L;

	/**
	 * the OS type, either android or iOs
	 */
	private String type;

	private String firebaseToken;
	
	public FirebaseTokenObject() {}
	
	public FirebaseTokenObject(String type ,String firebaseToken) {
		this.type = type ;
		this.firebaseToken = firebaseToken ;
	}
	public FirebaseTokenObject(String registrationId) {
//		this.type = ;
		this.firebaseToken = registrationId ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

	@Override
	public int hashCode() {

		return Objects.hash(type,firebaseToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FirebaseTokenObject) ) {
			return false ;
		}

		FirebaseTokenObject objectToCompareTo = (FirebaseTokenObject)obj;

		if (objectToCompareTo.getType() != null || objectToCompareTo.getType().trim().isEmpty()) {

			return (this.type.equalsIgnoreCase(objectToCompareTo.getType()) 
					&& this.firebaseToken.equals(objectToCompareTo.getFirebaseToken())) ;
		} else {

			return this.firebaseToken.equals(objectToCompareTo.getFirebaseToken()) ;
		}

	}

	@Override
	public String toString() {
		return new String("TYPE:: " + this.type + " >> TokenF: "+this.firebaseToken);
	}

}
