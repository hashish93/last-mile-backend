package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPickupRequestDTO implements Serializable, RowMapper<MyPickupRequestDTO> {
	/**
	 *
	 */
	private static final long serialVersionUID = -8151603647808244156L;
	private Long requestId;
	private String nickname;
	private String recipientName;
	private String recipientAddress;
	private String status;
	private String requestType;
	private String longitude;
	private String latitude;
	private String pickupRequestType;
	private Long packageId;
	private String packageDescription;
	private String confirmationCode;

	private Long hubId;

	public MyPickupRequestDTO() {
	}

	public MyPickupRequestDTO(Long requestId, String nickname, String recipientName, String recipientAddress,
			String status, String requestType, String longitude, String latitude, String pickupRequestType,
			Long packageIdee, String packageDesc ) {
		super();
		this.requestId = requestId;
		this.nickname = nickname;
		this.recipientName = recipientName;
		this.recipientAddress = recipientAddress;
		this.status = status;
		this.longitude = longitude;
		this.latitude = latitude;
		this.requestType = requestType;
		this.pickupRequestType = pickupRequestType;
		this.packageId = packageIdee;
		this.packageDescription = packageDesc;
	}

	public MyPickupRequestDTO(Long requestId, String nickname, String recipientName, String recipientAddress,
			String status, String requestType, String longitude, String latitude, String pickupRequestType,
			Long packageIdee, String packageDesc ,
			Long hubId ) {
		super();
		this.requestId = requestId;
		this.nickname = nickname;
		this.recipientName = recipientName;
		this.recipientAddress = recipientAddress;
		this.status = status;
		this.longitude = longitude;
		this.latitude = latitude;
		this.requestType = requestType;
		this.pickupRequestType = pickupRequestType;
		this.packageId = packageIdee;
		this.packageDescription = packageDesc;

		this.hubId = hubId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPickupRequestType() {
		return pickupRequestType;
	}

	public void setPickupRequestType(String pickupRequestType) {
		this.pickupRequestType = pickupRequestType;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public MyPickupRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			
			Long hubId = rs.getLong("hub_id");
			
			return new MyPickupRequestDTO(rs.getLong("pickup_request_id"), rs.getString("nickname"),
					rs.getString("recipientName"), rs.getString("recipientAddress"), rs.getString("status"),
					rs.getString("requesttype"), rs.getString("longitude"), rs.getString("latitude"),
					rs.getString("pickupRequestType"),
					rs.getLong("package_id"), rs.getString("package_description") ,  hubId);
			
		} catch (SQLException e) {
			
			return new MyPickupRequestDTO(rs.getLong("pickup_request_id"), rs.getString("nickname"),
					rs.getString("recipientName"), rs.getString("recipientAddress"), rs.getString("status"),
					rs.getString("requesttype"), rs.getString("longitude"), rs.getString("latitude"),
					rs.getString("pickupRequestType"),
					rs.getLong("package_id"), rs.getString("package_description") );
		}
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}
}
