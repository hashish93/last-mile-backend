package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "delivery_request", schema = "lastmile_request")
public class DeliveryRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "delivery_request_id", nullable = false)
	private Long deliveryRequestId;

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

	@Column(name = "time_from", length = 50)
	private String timeFrom;

	@Column(name = "time_to", length = 50)
	private String timeTo;

	@Temporal(TemporalType.DATE)
	@Column(name = "deliverydate")
	private Date deliverydate;

	@Column(name = "labelingtext", length = 250)
	private String labelingtext;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@Column(name = "paymentmethod", length = 100)
	private String paymentmethod;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "confirmationcode", length = 50)
	private String confirmationcode;

	@Column(name = "countrycode", length = 50)
	private String countrycode;

	@Column(name = "version", nullable = false)
	private Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "request_status", length = 50)
	private String requestStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;
}
