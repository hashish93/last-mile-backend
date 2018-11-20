package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pickup_time", schema = "lastmile_request")
public class PickupTimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_time_id", nullable = false)
	private Long pickupTimeId;

	@Column(name = "time_from", nullable = true)
	private String timeFrom;

	@Column(name = "time_to", nullable = true)
	private String timeTo;
	
}
