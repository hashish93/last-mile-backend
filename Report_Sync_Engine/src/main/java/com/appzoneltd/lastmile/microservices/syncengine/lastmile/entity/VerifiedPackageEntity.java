package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "verified_package", schema = "lastmile_core")
public class VerifiedPackageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_id", nullable = false)
	private Long packageId;

	@Column(name = "package_value", nullable = false, length = 2147483647)
	private String packageValue;

	@Column(name = "actualweight", nullable = false)
	private BigDecimal actualweight;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
	private PackageEntity packageEntity;

	@ManyToOne
	@JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
	private PackageTypeEntity packageType;
}
