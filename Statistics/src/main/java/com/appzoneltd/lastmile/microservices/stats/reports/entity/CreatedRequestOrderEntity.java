package com.appzoneltd.lastmile.microservices.stats.reports.entity;


import java.io.Serializable;
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
@Table(name = "request_creation_in_duration", schema = "reporting")
@NamedQueries({
	@NamedQuery(name = "request_creation_in_duration.countAll", query = "SELECT COUNT(x) FROM CreatedRequestOrderEntity x") })
public class CreatedRequestOrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360217520406895175L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="request_id")
	private Long requestId;
	
	@Column(name="request_type")
	private String requestType;
	
	@Column(name="pickup_type_id")
	private Long pickupTypeId;
	
	@Column(name="hub_id")
	private Long hubId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="request_date")
	private Date requestDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Long getPickupTypeId() {
		return pickupTypeId;
	}

	public void setPickupTypeId(Long pickupTypeId) {
		this.pickupTypeId = pickupTypeId;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
}
