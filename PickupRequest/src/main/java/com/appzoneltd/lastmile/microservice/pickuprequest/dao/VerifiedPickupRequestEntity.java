/*
 * Created on 30 Jan 2017 ( Time 11:57:30 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "verified_pickup_request", schema = "lastmile_request")
@NamedQueries({
		@NamedQuery(name = "VerifiedPickupRequestEntity.countAll", query = "SELECT COUNT(x) FROM VerifiedPickupRequestEntity x") })
public class VerifiedPickupRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_request_id", nullable = false)
	private Long pickupRequestId;

	@Column(name = "additionalservices", length = 200)
	private String additionalservices;

	@Column(name = "labelingtext", length = 250)
	private String labelingtext;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@Column(name = "paymentmethod", length = 100)
	private String paymentmethod;

	@Column(name = "version", nullable = false)
	private Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

//	@OneToOne
//	@JoinColumn(name = "pickup_request_id", referencedColumnName = "pickup_request_id", insertable = false, updatable = false)
//	private PickupRequestEntity pickupRequest;

	public VerifiedPickupRequestEntity() {
		super();
	}

	public VerifiedPickupRequestEntity(Long pickupRequestId, String additionalservices, String labelingtext,
			String paymenttype, String paymentmethod) {
		super();
		this.pickupRequestId = pickupRequestId;
		this.additionalservices = additionalservices;
		this.labelingtext = labelingtext;
		this.paymenttype = paymenttype;
		this.paymentmethod = paymentmethod;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public Long getPickupRequestId() {
		return this.pickupRequestId;
	}

	public void setAdditionalservices(String additionalservices) {
		this.additionalservices = additionalservices;
	}

	public String getAdditionalservices() {
		return this.additionalservices;
	}

	public void setLabelingtext(String labelingtext) {
		this.labelingtext = labelingtext;
	}

	public String getLabelingtext() {
		return this.labelingtext;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	// public void setPickupRequest(PickupRequestEntity pickupRequest) {
	// this.pickupRequest = pickupRequest;
	// }
	//
	// public PickupRequestEntity getPickupRequest() {
	// return this.pickupRequest;
	// }

}
