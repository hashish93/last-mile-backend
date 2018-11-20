package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "package_type_details", schema = "reporting")
public class PackageTypeDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "package_id")
	private Long packageId;

	@Column(name = "package_value")
	private Long packageValue;

	@Column(name = "package_type_id")
	private Long packageTypeId;

	@Column(name = "hub_id")
	private Long hubId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

}
