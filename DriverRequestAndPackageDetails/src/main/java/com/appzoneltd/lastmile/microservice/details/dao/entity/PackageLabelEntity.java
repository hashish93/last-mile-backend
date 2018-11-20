/*
 * Created on 28 Dec 2016 ( Time 15:33:36 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.details.dao.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "package_label", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "PackageLabelEntity.countAll", query = "SELECT COUNT(x) FROM PackageLabelEntity x") })
public class PackageLabelEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_label_id", nullable = false)
	private Long packageLabelId;

	@Column(name = "label", nullable = false, length = 50)
	private String label;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToMany(mappedBy = "listOfPackageLabel", targetEntity = PackageEntity.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<PackageEntity> listOfPackage;

	public PackageLabelEntity() {
		super();
	}

	public void setPackageLabelId(Long packageLabelId) {
		this.packageLabelId = packageLabelId;
	}

	public Long getPackageLabelId() {
		return this.packageLabelId;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
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

	public void setListOfPackage(Set<PackageEntity> listOfPackage) {
		this.listOfPackage = listOfPackage;
	}

	public Set<PackageEntity> getListOfPackage() {
		return this.listOfPackage;
	}

}