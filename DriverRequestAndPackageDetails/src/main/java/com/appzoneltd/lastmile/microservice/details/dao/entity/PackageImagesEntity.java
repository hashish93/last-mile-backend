
package com.appzoneltd.lastmile.microservice.details.dao.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "package_images", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "PackageImagesEntity.countAll", query = "SELECT COUNT(x) FROM PackageImagesEntity x") })
public class PackageImagesEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PackageImagesEntityKey compositePrimaryKey;

	@Column(name = "version", nullable = false)
	private Long version;

	public PackageImagesEntity() {
		super();
		this.compositePrimaryKey = new PackageImagesEntityKey();
	}

	public void setPackageId(Long packageId) {
		this.compositePrimaryKey.setPackageId(packageId);
	}

	public Long getPackageId() {
		return this.compositePrimaryKey.getPackageId();
	}

	public void setImageId(Long imageId) {
		this.compositePrimaryKey.setImageId(imageId);
	}

	public Long getImageId() {
		return this.compositePrimaryKey.getImageId();
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

}
