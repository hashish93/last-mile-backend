package com.appzoneltd.lastmile.microservices.stats.reports.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "go_extra_summery", schema = "reporting")
public class GoExtraSummeryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "reject_count")
	private Long rejectCount;

	@Column(name = "acknowledge_count")
	private Long acknowledgeCount;

	@Column(name = "canceled_count")
	private Long canceledCount;

	@Column(name = "with_period", length = 2147483647)
	private String withPeriod;
}
