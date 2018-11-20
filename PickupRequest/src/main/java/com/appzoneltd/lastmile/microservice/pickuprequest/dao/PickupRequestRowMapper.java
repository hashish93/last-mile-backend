package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PickupRequestRowMapper implements RowMapper<PickupRequestInfo> {

    @Override
    public PickupRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PickupRequestInfo pickupRequestInfo = new PickupRequestInfo();
        pickupRequestInfo.setPickupRequestId(rs.getLong("pickup_request_id"));
        pickupRequestInfo.setPickupRequestTypeId(rs.getLong("pickup_request_type_id"));
        pickupRequestInfo.setRequestType(rs.getString("type"));
        pickupRequestInfo.setPackageValue(rs.getString("package_value"));
        pickupRequestInfo.setRequestTime(rs.getTimestamp("requesttime"));
        pickupRequestInfo.setReceivedFrom(String.valueOf(
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - rs.getTimestamp("requesttime").getTime())));
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
        pickupRequestInfo.setOrderStatus(OrderStatus.valueOf(rs.getString("request_status")));

        pickupRequestInfo.setActualWeight(rs.getString("actualweight"));
        pickupRequestInfo.setPackageDimension(rs.getString("packagedimension"));
        pickupRequestInfo.setPackageType(rs.getString("packagetype"));
        pickupRequestInfo.setPackageId(rs.getLong("package_id"));
        pickupRequestInfo.setHubId(rs.getLong("hub_id"));
        pickupRequestInfo.setBuildingName(rs.getString("buildingname"));
        pickupRequestInfo.setCancellationReason(rs.getString("cancellation_reason"));
        if ("ON-DEMAND".equalsIgnoreCase(pickupRequestInfo.getRequestType()))
            pickupRequestInfo.setPickupDate(pickupRequestInfo.getRequestTime());

        Long activeVehicleOfPlan = rs.getLong("active_vehicle_id");
        if (activeVehicleOfPlan != null && activeVehicleOfPlan > 0) {
            pickupRequestInfo.setInTodaysPlan(true);
        } else {
            pickupRequestInfo.setInTodaysPlan(false);
        }

        return pickupRequestInfo;
    }

}
