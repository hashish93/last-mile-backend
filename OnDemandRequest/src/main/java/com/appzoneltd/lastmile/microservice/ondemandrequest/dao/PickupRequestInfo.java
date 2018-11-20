package com.appzoneltd.lastmile.microservice.ondemandrequest.dao;

public class PickupRequestInfo extends PickupRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4927038135253181628L;
	private String requestType;
	private String requesterMobile;
	private String requesterName;
	private Long hubId;

	public PickupRequestInfo() {
	}

	public PickupRequestInfo(String requestType, String requesterMobile, String requesterName) {
		super();
		this.requestType = requestType;
		this.requesterMobile = requesterMobile;
		this.requesterName = requesterName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequesterMobile() {
		return requesterMobile;
	}

	public void setRequesterMobile(String requesterMobile) {
		this.requesterMobile = requesterMobile;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}
	
	

}
