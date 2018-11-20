package com.appzoneltd.lastmile.microservice.offloading.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "request_history", schema = "lastmile_request")
@NamedQueries({
		@NamedQuery(name = "RequestHistoryEntity.countAll", query = "SELECT COUNT(x) FROM RequestHistoryEntity x") })
public class RequestHistoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "request_history_id", nullable = false)
	private Long requestHistoryId;

	@Column(name = "request_id", nullable = false)
	private Long requestId;

	@Column(name = "request_type", nullable = false)
	private String requestType;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	private PackageEntity packageEntity;

	public RequestHistoryEntity() {
		super();
	}

	public Long getRequestHistoryId() {
		return this.requestHistoryId;
	}

	public void setRequestHistoryId(Long requestHistoryId) {
		this.requestHistoryId = requestHistoryId;
	}

	public Long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public PackageEntity getPackageEntity() {
		return packageEntity;
	}

	public void setPackageEntity(PackageEntity packageEntity) {
		this.packageEntity = packageEntity;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
