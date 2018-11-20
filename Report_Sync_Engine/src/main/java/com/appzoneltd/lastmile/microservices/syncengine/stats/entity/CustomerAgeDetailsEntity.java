package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_age_details", schema = "reporting")
public class CustomerAgeDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer_age")
	private Long customerAge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

}
