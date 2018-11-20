package com.appzoneltd.lastmile.microservices.stats.reports.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "request_type_summery", schema = "reporting")
public class RequestTypeSummeryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "on_demand_count")
	private Long onDemandCount;

	@Column(name = "scheduled_count")
	private Long scheduledCount;

	@Column(name = "delivery_count")
	private Long deliveryCount;

	@Column(name = "return_count")
	private Long returnCount;
	
	@Column(name = "within_period")
	private String withinPeriod;
}
