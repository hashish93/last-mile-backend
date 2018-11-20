package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "pickup_statistics_summery", schema = "reporting")
public class PickupStatisticsSummeryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "request_type", length = 2147483647)
	private String requestType;

	@Column(name = "pickedup_count")
	private Long pickedupCount;

	@Column(name = "canceled_count")
	private Long canceledCount;

	@Column(name = "no_capacity_count")
	private Long noCapacityCount;

	@Column(name = "no_coverage_count")
	private Long noCoverageCount;

	@Column(name = "within_period", length = 2147483647)
	private String withinPeriod;
}
