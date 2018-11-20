package com.appzoneltd.lastmile.microservice.ondemandrequest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PickupRequestRowMapper implements RowMapper<PickupRequestInfo> {

	private PickupRequestInfo pickupRequestInfo;

	@Override
	public PickupRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		pickupRequestInfo = new PickupRequestInfo();
		pickupRequestInfo.setPickupRequestId(rs.getLong("pickup_request_id"));
		pickupRequestInfo.setRequestTypeId(rs.getLong("pickup_request_type_id"));
		pickupRequestInfo.setRequestType(rs.getString("type"));
		pickupRequestInfo.setRequestTime(rs.getTimestamp("recievedfrom"));
		pickupRequestInfo.setRequesterId(rs.getLong("requester_id"));
		pickupRequestInfo.setRequesterName(rs.getString("username"));
		pickupRequestInfo.setRequesterMobile(rs.getString("phone"));
		pickupRequestInfo.setPickupLatitude(rs.getString("pickuplatitude"));
		pickupRequestInfo.setPickupLongitude(rs.getString("pickuplongitude"));
		pickupRequestInfo.setPickupWaselLocation(rs.getString("pickupwasellocation"));
		pickupRequestInfo.setPickupFormatedAddress(rs.getString("pickupformatedaddress"));
		pickupRequestInfo.setTimeFrom(rs.getString("time_from"));
		pickupRequestInfo.setTimeTo(rs.getString("time_to"));
		pickupRequestInfo.setPickupDate(rs.getTimestamp("pickupdate"));
		pickupRequestInfo.setRecipientId(rs.getLong("recipient_id"));
		if (rs.wasNull())
			pickupRequestInfo.setRecipientId(null);
		pickupRequestInfo.setRecipientName(rs.getString("recipientname"));
		pickupRequestInfo.setRecipientMobile(rs.getString("recipientmobile"));
		pickupRequestInfo.setRecipientLongitude(rs.getString("recipientlongitude"));
		pickupRequestInfo.setRecipientLatitude(rs.getString("recipientlatitude"));
		pickupRequestInfo.setRecipientWaselLocation(rs.getString("recipientwasellocation"));
		pickupRequestInfo.setRecipientFormatedAddress(rs.getString("recipientformatedaddress"));
		pickupRequestInfo.setRecipientAdditionalInfo(rs.getString("recipientadditionalinfo"));
		pickupRequestInfo.setAdditionalServices(rs.getString("additionalservices"));
		pickupRequestInfo.setLabelingText(rs.getString("labelingtext"));
		pickupRequestInfo.setPaymentType(rs.getString("paymenttype"));
		pickupRequestInfo.setPaymentMethod(rs.getString("paymentmethod"));
		pickupRequestInfo.setDescription(rs.getString("description"));
		pickupRequestInfo.setVersion(rs.getLong("version"));
		pickupRequestInfo.setCreated(rs.getTimestamp("created"));
		pickupRequestInfo.setHubId(rs.getLong("hub_id"));

		return pickupRequestInfo;
	}

}
