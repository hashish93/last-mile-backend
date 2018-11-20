package com.appzoneltd.lastmile.microservices.stats.reports.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "driver_rating_summery", schema = "reporting")
public class DriverRatingSummeryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "driver_type", length = 2147483647)
	private String driverType;

	@Column(name = "one_star")
	private Long oneStar;

	@Column(name = "two_star")
	private Long twoStar;

	@Column(name = "three_star")
	private Long threeStar;

	@Column(name = "four_star")
	private Long fourStar;

	@Column(name = "five_star")
	private Long fiveStar;
}
