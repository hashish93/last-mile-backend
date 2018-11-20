package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "package_type_summary", schema = "reporting")
public class PackageTypeSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "hub_id")
	private Long hubId;

	@Column(name = "package_type_id")
	private Long packageTypeId;

	@Column(name = "within_period")
	private String withinPeriod;

	@Column(name = "package_less_5_kg")
	private Long packageLess5Kg;

	@Column(name = "package_less_10_kg")
	private Long packageLess10Kg;

	@Column(name = "package_less_25_kg")
	private Long packageLess25Kg;
}
