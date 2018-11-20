package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "package", schema = "lastmile_core")

@NamedQueries({ @NamedQuery(name = "PackageEntity.countAll", query = "SELECT COUNT(x) FROM PackageEntity x") })
public class PackageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_id", nullable = false)
	private Long packageId;

	@Column(name = "status", length = 50)
	private String status;

	public PackageEntity() {
		super();
	}

	public Long getPackageId() {
		return this.packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
