package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "request_type_details", schema = "reporting")
public class RequestTypeDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "request_type", length = 2147483647)
	private String requestType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;
}
