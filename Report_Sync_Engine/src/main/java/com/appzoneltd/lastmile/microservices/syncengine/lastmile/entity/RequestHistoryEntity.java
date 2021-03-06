package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "request_history", schema = "lastmile_request")
public class RequestHistoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "request_history_id", nullable = false)
	private Long requestHistoryId;

	@Column(name = "package_id", nullable = false)
	private Long packageId;

	@Column(name = "request_status", length = 50)
	private String requestStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "request_id", nullable = false)
	private Long requestId;

	@Column(name = "request_type", length = 50)
	private String requestType;

}
