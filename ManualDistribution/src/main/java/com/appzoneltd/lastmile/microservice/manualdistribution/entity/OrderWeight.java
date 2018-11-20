package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package", schema = "lastmile_core")
public class OrderWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long packageId;

	private BigDecimal actualWeight;

	private PackageType packageType;

	@Id
	@Column(name = "package_id")
	public Long getPackageId() {
		return packageId;
	}

	@Column(name = "actualweight")
	public BigDecimal getActualWeight() {
		return actualWeight;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public void setActualWeight(BigDecimal actualWeight) {
		this.actualWeight = actualWeight;
	}

	@OneToOne
	@JoinColumn(name = "package_type_id")
	public PackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
	}

}
