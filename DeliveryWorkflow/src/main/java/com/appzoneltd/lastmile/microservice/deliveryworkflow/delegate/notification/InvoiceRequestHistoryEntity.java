package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PackageEntity;

/**
* Persistent class for entity stored in table "request_history"
*
* @author Telosys Tools Generator
*
*/

@Entity
@Table(name = "request_history", schema = "lastmile_request")
//Define named queries here
@NamedQueries({
		@NamedQuery(name = "InvoiceRequestHistoryEntity.countAll", query = "SELECT COUNT(x) FROM InvoiceRequestHistoryEntity x") })
public class InvoiceRequestHistoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "request_history_id", nullable = false)
	private Long requestHistoryId;

	@Column(name = "request_status", nullable = false, length = 50)
	private String requestStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "request_id", nullable = false)
	private Long requestId;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	private InvoicePackageEntity packageEntity;
	
	
	@Column(name = "request_type", nullable = false)
	private String requestType;

	public void setRequestHistoryId(Long requestHistoryId) {
		this.requestHistoryId = requestHistoryId;
	}

	public Long getRequestHistoryId() {
		return this.requestHistoryId;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getRequestId() {
		return this.requestId;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

	public InvoicePackageEntity getPackageEntity() {
		return packageEntity;
	}

	public void setPackageEntity(InvoicePackageEntity packageEntity) {
		this.packageEntity = packageEntity;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}