/**
 * Jun 15, 20161:32:20 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.scheduledrequest.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.core.Model;

/**
 * @author alaa.nabil
 *
 */
public class ScheduledRequest extends Model implements Serializable, RowMapper<ScheduledRequest> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pickupRequestId;

	private Date pickupDate;
	
	private Date requestDate ;

	private String timeInterval;

	private String formatedAddress;

	private String packageType;
	
	private long companyId ;
	
	private String pickupTime;
	
	private String requesterMobile ;

	public Long getPickupRequestId() {
		return pickupRequestId;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getFormatedAddress() {
		return formatedAddress;
	}

	public void setFormatedAddress(String formatedAddress) {
		this.formatedAddress = formatedAddress;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getRequesterMobile() {
		return requesterMobile;
	}

	public void setRequesterMobile(String requesterMobile) {
		this.requesterMobile = requesterMobile;
	}

	/**
	* 
	*/
	public ScheduledRequest() {
		
	}

	public ScheduledRequest(Long pickupRequestId, String requesterMobile ,Date pickupDate, Date requestDate, String timeInterval, String formatedAddress,
			String packageType ) {
	
		this.pickupRequestId = pickupRequestId;
		this.requesterMobile=requesterMobile;
		this.pickupDate = pickupDate;
		this.timeInterval = timeInterval;
		this.formatedAddress = formatedAddress;
		this.packageType = packageType;
		this.requestDate=requestDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public ScheduledRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		ScheduledRequest scheduledRequest = new ScheduledRequest(rs.getLong("pickup_request_id"),rs.getString("requestermobile") ,rs.getDate("pickupdate"),rs.getDate("requestdate") ,rs.getString("pickuptime"), rs.getString("pickupformatedaddress"), rs.getString("packagetype"));
		return scheduledRequest;
	}

}
