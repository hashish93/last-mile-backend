
package com.appzoneltd.lastmile.microservice.details.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "package_labeling", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "PackageLabelingEntity.countAll", query = "SELECT COUNT(x) FROM PackageLabelingEntity x") })
public class PackageLabelingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PackageLabelingEntityKey compositePrimaryKey;

	@Column(name = "version", nullable = false)
	private Long version;

	public PackageLabelingEntity() {
		super();
		this.compositePrimaryKey = new PackageLabelingEntityKey();
	}

	public void setPackageId(Long packageId) {
		this.compositePrimaryKey.setPackageId(packageId);
	}

	public Long getPackageId() {
		return this.compositePrimaryKey.getPackageId();
	}

	public void setPackageLabelId(Long packageLabelId) {
		this.compositePrimaryKey.setPackageLabelId(packageLabelId);
	}

	public Long getPackageLabelId() {
		return this.compositePrimaryKey.getPackageLabelId();
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

}
