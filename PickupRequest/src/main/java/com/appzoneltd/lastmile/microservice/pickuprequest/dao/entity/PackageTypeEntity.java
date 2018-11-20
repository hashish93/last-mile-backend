/*
 * Created on 27 Dec 2016 ( Time 16:30:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "package_type", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "PackageTypeEntity.countAll", query = "SELECT COUNT(x) FROM PackageTypeEntity x") })
public class PackageTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_type_id", nullable = false)
	private Long packageTypeId;

	@Column(name = "packagetype", nullable = false, length = 50)
	private String packagetype;

	@Column(name = "packagedimension", nullable = false, length = 50)
	private String packagedimension;

	@Column(name = "expectedweight", nullable = false)
	private Long expectedweight;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@OneToMany(mappedBy = "packageType", targetEntity = PackageEntity.class)
	private List<PackageEntity> listOfPackage;

	public PackageTypeEntity() {
		super();
	}

	public PackageTypeEntity(Long packageTypeId, String packagetype, String packagedimension, Long expectedweight,
			String description, Date created, Long version, List<PackageEntity> listOfPackage) {
		super();
		this.packageTypeId = packageTypeId;
		this.packagetype = packagetype;
		this.packagedimension = packagedimension;
		this.expectedweight = expectedweight;
		this.description = description;
		this.created = created;
		this.version = version;
		this.listOfPackage = listOfPackage;
	}

	public void setPackageTypeId(Long packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public Long getPackageTypeId() {
		return this.packageTypeId;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}

	public String getPackagetype() {
		return this.packagetype;
	}

	public void setPackagedimension(String packagedimension) {
		this.packagedimension = packagedimension;
	}

	public String getPackagedimension() {
		return this.packagedimension;
	}

	public void setExpectedweight(Long expectedweight) {
		this.expectedweight = expectedweight;
	}

	public Long getExpectedweight() {
		return this.expectedweight;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setListOfPackage(List<PackageEntity> listOfPackage) {
		this.listOfPackage = listOfPackage;
	}

	public List<PackageEntity> getListOfPackage() {
		return this.listOfPackage;
	}

}
