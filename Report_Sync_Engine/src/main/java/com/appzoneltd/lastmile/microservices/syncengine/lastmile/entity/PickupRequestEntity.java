package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "pickup_request", schema = "lastmile_request")
public class PickupRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_request_id", nullable = false)
	private Long pickupRequestId;

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

	@Column(name = "time_from", length = 50)
	private String timeFrom;

	@Column(name = "time_to", length = 50)
	private String timeTo;

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

	@Column(name = "pickup_service_type_id")
	private Long pickupServiceTypeId;

	@Column(name = "additionalservices", length = 200)
	private String additionalservices;

	@Column(name = "labelingtext", length = 250)
	private String labelingtext;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@Column(name = "paymentmethod", length = 100)
	private String paymentmethod;

	@Column(name = "description", length = 250)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "request_status", length = 50)
	private String requestStatus;

	@Column(name = "countrycode", length = 100)
	private String countryCode;

	@Column(name = "cancellation_reason", length = 100)
	private String cancellationReason;

	@Column(name = "requester_id", length = 100)
	private Long requesterId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

	@ManyToOne
	@JoinColumn(name = "pickup_request_type_id", referencedColumnName = "pickup_request_type_id")
	private PickupRequestTypeEntity pickupRequestType;

}
