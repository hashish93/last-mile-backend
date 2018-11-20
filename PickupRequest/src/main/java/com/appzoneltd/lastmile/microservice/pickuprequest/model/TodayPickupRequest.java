/**
 * Jun 23, 20161:09:17 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author alaa.nabil
 *
 */
public class TodayPickupRequest implements Serializable, RowMapper<TodayPickupRequest> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pickupRequestId;
	private String requestType;
	private String pickupGoogleLocation;
	private String pickupStatus;

	public Long getPickupRequestId() {
		return pickupRequestId;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPickupGoogleLocation() {
		return pickupGoogleLocation;
	}

	public void setPickupGoogleLocation(String pickupGoogleLocation) {
		this.pickupGoogleLocation = pickupGoogleLocation;
	}

	public String getPickupStatus() {
		return pickupStatus;
	}

	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public TodayPickupRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		TodayPickupRequest toDayPickupRequest = new TodayPickupRequest();
		toDayPickupRequest.setPickupRequestId(rs.getLong("pickup_request_id"));
		toDayPickupRequest.setRequestType(rs.getString("requesttype"));
		toDayPickupRequest.setPickupGoogleLocation(rs.getString("pickupgooglelocation"));
		toDayPickupRequest.setPickupStatus(rs.getString("pickupstatus"));

		return toDayPickupRequest;
	}

}
