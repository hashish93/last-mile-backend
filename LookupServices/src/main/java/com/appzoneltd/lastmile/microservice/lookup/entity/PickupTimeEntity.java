package com.appzoneltd.lastmile.microservice.lookup.entity;

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
@Table(name = "pickup_time", schema = "lastmile_request")
public class PickupTimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_time_id", nullable = false)
	private Long pickupTimeId;

	@Column(name = "time_from", nullable = false, length = 50)
	private String timeFrom;

	@Column(name = "time_to", nullable = false, length = 50)
	private String timeTo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

}
