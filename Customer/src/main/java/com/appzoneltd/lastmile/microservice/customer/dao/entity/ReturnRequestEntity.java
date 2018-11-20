package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "return_request", schema = "lastmile_request")
public class ReturnRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "return_request_id", nullable = false)
	private Long returnRequestId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requesttime")
	private Date requesttime;

	@Column(name = "pickuplongitude", length = 50)
	private String pickuplongitude;

	@Column(name = "pickuplatitude", length = 50)
	private String pickuplatitude;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "pickupwasellocation", length = 200)
	private String pickupwasellocation;

	@Column(name = "pickupformatedaddress", length = 250)
	private String pickupformatedaddress;

	@Temporal(TemporalType.DATE)
	@Column(name = "pickupdate")
	private Date pickupdate;

	@Column(name = "recipientname", length = 50)
	private String recipientname;

	@Column(name = "recipientmobile", length = 20)
	private String recipientmobile;

	@Column(name = "recipientlongitude", length = 50)
	private String recipientlongitude;

	@Column(name = "recipientlatitude", length = 50)
	private String recipientlatitude;

	@Column(name = "recipientwasellocation", length = 200)
	private String recipientwasellocation;

	@Column(name = "recipientformatedaddress", length = 250)
	private String recipientformatedaddress;

	@Column(name = "recipientadditionalinfo", length = 250)
	private String recipientadditionalinfo;

	@Column(name = "additionalservices", length = 200)
	private String additionalservices;

	@Column(name = "return_longitude", length = 50)
	private String returnLongitude;

	@Column(name = "return_latitude", length = 50)
	private String returnLatitude;

	@Column(name = "return_description", length = 250)
	private String returnDescription;

	@Column(name = "time_from", length = 50)
	private String timeFrom;

	@Column(name = "time_to", length = 50)
	private String timeTo;

	@Temporal(TemporalType.DATE)
	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "labelingtext", length = 250)
	private String labelingtext;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@Column(name = "paymentmethod", length = 100)
	private String paymentmethod;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "version", nullable = false)
	private Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "request_status", length = 50)
	private String requestStatus;

	@Column(name = "return_formatted_address", length = 250)
	private String returnFormattedAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

	@Column(name = "requester_id")
	private Long requesterId;
}
