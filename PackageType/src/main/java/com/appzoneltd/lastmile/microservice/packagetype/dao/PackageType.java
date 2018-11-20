package com.appzoneltd.lastmile.microservice.packagetype.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Alaa Nabil
 *
 */
public class PackageType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -430850492617722126L;
	private Long packageTypeId;
	private String packageType;
	private String packageDimension;
	private Long expectedWeight;
	private String description;
	private Date created;
	private Long version;

	public PackageType() {
	}

	public PackageType(Long packageTypeId, String packageType, String packageDimension, Long expectedWeight,
			String description, Date created, Long version) {
		super();
		this.packageTypeId = packageTypeId;
		this.packageType = packageType;
		this.packageDimension = packageDimension;
		this.expectedWeight = expectedWeight;
		this.description = description;
		this.created = created;
		this.version = version;
	}

	public Long getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Long packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageDimension() {
		return packageDimension;
	}

	public void setPackageDimension(String packageDimension) {
		this.packageDimension = packageDimension;
	}

	public Long getExpectedWeight() {
		return expectedWeight;
	}

	public void setExpectedWeight(Long expectedWeight) {
		this.expectedWeight = expectedWeight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
