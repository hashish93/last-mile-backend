package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;

public class RequestParameters extends EndPointParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4885181177667954127L;
	private Long packageId;
	private String requesterMobile;
	private String fromRequestDate;
	private String toRequestDate;
	private String fromPickupDate;
	private String toPickupDate;

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getRequesterMobile() {
		return requesterMobile;
	}

	public void setRequesterMobile(String requesterMobile) {
		this.requesterMobile = requesterMobile;
	}

	public String getFromRequestDate() {
		return fromRequestDate;
	}

	public void setFromRequestDate(String fromRequestDate) {
		this.fromRequestDate = fromRequestDate;
	}

	public String getToRequestDate() {
		return toRequestDate;
	}

	public void setToRequestDate(String toRequestDate) {
		this.toRequestDate = toRequestDate;
	}

	public String getFromPickupDate() {
		return fromPickupDate;
	}

	public void setFromPickupDate(String fromPickupDate) {
		this.fromPickupDate = fromPickupDate;
	}

	public String getToPickupDate() {
		return toPickupDate;
	}

	public void setToPickupDate(String toPickupDate) {
		this.toPickupDate = toPickupDate;
	}

}
