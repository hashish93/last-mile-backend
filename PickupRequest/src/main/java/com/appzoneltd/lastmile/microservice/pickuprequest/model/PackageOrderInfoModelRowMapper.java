package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PackageOrderInfoModelRowMapper implements RowMapper<PackageOrderInfoModel> {

	@Override
	public PackageOrderInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PackageOrderInfoModel packageOrderInfoModel = new PackageOrderInfoModel();
		packageOrderInfoModel.setRequestId(rs.getLong("pickup_request_id"));
		packageOrderInfoModel.setRecepientName(rs.getString("recipientname"));
		packageOrderInfoModel.setRecepientPhone(rs.getString("recipientmobile"));
		packageOrderInfoModel.setRequestStatus(rs.getString("request_status"));
		packageOrderInfoModel.setPickupLatitiude(rs.getString("pickuplatitude"));
		packageOrderInfoModel.setPickupLongitude(rs.getString("pickuplongitude"));
		packageOrderInfoModel.setActualWeight(rs.getInt("actualweight"));
		packageOrderInfoModel.setPickupRequestType(rs.getString("request_type"));
		packageOrderInfoModel.setCustomerId(rs.getLong("user_id"));
		packageOrderInfoModel.setCustomerPhone(rs.getString("phone"));
		packageOrderInfoModel.setCustomerName(rs.getString("username"));
		// return result
		return packageOrderInfoModel;
	}
	
}
