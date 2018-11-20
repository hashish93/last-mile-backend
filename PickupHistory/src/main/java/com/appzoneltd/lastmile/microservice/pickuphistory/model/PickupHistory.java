package com.appzoneltd.lastmile.microservice.pickuphistory.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.core.Model;


public class PickupHistory extends Model implements Serializable, RowMapper<PickupHistory> {
	
	private static final long serialVersionUID = 1L;
	
	
	private long PickupRequestId;
	
	private String requestType;
	
	private String packageType;
	
	private String pickupStatus;
	
	private long companyId ;
	




	public Long getPickupRequestId() {
		return PickupRequestId;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		PickupRequestId = pickupRequestId;
	}


	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPickupStatus() {
		return pickupStatus;
	}

	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	


	public PickupHistory mapRow(ResultSet rs, int num) throws SQLException {
    	PickupHistory pickupHistory = new PickupHistory();
    	//--- Set data from ResultSet to Bean attributes
    	
    	pickupHistory.setPickupRequestId((rs.getLong("pickup_request_id")));// java.lang.Long
    	
		if ( rs.wasNull() ) { pickupHistory.setPickupRequestId(null); }; // not primitive number => keep null value if any
		
		pickupHistory.setPackageType(rs.getString("packagetype"));
		
		pickupHistory.setPickupStatus(rs.getString("pickupstatus"));
		
		pickupHistory.setRequestType(rs.getString("requesttype"));
		

		
		
		return pickupHistory ;
	}

	

}
