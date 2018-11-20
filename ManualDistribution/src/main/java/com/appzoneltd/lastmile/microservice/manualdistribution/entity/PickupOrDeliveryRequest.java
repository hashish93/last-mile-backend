 package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "request_history", schema = "lastmile_request")
public class PickupOrDeliveryRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long jobOrderId ;
	
	private Long packageId ;
	
	private String jobOrderType ;

	private Date created ;
	
	@Id
	@Column(name = "request_id")
	public Long getJobOrderId() {
		return jobOrderId;
	}

	@Column(name = "package_id")
	public Long getPackageId() {
		return packageId;
	}

	@Column(name = "request_type")
	public String getJobOrderType() {
		return jobOrderType;
	}
	
	public void setJobOrderId(Long jobOrderId) {
		this.jobOrderId = jobOrderId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	

	public void setJobOrderType(String jobOrderType) {
		this.jobOrderType = jobOrderType;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
