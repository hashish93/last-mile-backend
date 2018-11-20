package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "return_request", schema = "lastmile_request")
@NamedQueries({
	@NamedQuery(name = "ReturnRequestEntity.countAll", query = "SELECT COUNT(x) FROM ReturnRequestEntity x")
})
public class ReturnRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "return_request_id", nullable = false)
	private Long returnRequestId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requesttime")
	private Date requesttime;

	@Column(name = "hub_id")
	private Long hubId;

	@Temporal(TemporalType.DATE)
	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "request_status", length = 50)
	private String requestStatus;

	public ReturnRequestEntity() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getReturnRequestId() {
		return this.returnRequestId;
	}

	public void setReturnRequestId(Long returnRequestId) {
		this.returnRequestId = returnRequestId;
	}

	public Date getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public Long getHubId() {
		return this.hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

}
