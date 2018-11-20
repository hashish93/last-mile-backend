package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "package_type", schema = "lastmile_core")
public class PackageType {

	private Long packageTypeId;

	private String packageType;

	@Id
	@Column(name = "package_type_id")
	public Long getPackageTypeId() {
		return packageTypeId;
	}

	@Column(name = "packagetype")
	public String getPackageType() {
		return packageType;
	}

	public void setPackageTypeId(Long packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

}
