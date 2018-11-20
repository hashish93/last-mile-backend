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
@Table(name = "customer", schema = "lastmile_core")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "gender", length = 50)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date birthdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;
}
