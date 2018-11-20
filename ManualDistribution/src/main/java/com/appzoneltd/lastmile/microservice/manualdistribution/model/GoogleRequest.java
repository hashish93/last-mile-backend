package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.io.Serializable;
import java.util.Date;

public class GoogleRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String [] origin ; 
	
	private String[] destination ; 
	
	private Date date = new Date();
	
	
	
	public String[] getOrigin() {
		return origin;
	}

	public String[] getDestination() {
		return destination;
	}

	public void setOrigin(String[] origin) {
		this.origin = origin;
	}

	public void setDestination(String[] destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}






}
