package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "delivery_request", schema = "lastmile_request")
@NamedQueries({
		@NamedQuery(name = "DeliveryRequestEntity.countAll", query = "SELECT COUNT(x) FROM DeliveryRequestEntity x") })
public class DeliveryRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "delivery_request_id", nullable = false)
	private Long deliveryRequestId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requesttime")
	private Date requesttime;


	@Temporal(TemporalType.DATE)
	@Column(name = "deliverydate")
	private Date deliverydate;


	@Column(name = "request_status", length = 50)
	private String requestStatus;
	
	@Column(name = "hub_id")
	private Long hubId;

	public DeliveryRequestEntity() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getDeliveryRequestId() {
		return deliveryRequestId;
	}

	public void setDeliveryRequestId(Long deliveryRequestId) {
		this.deliveryRequestId = deliveryRequestId;
	}

	public Date getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}


	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

}
