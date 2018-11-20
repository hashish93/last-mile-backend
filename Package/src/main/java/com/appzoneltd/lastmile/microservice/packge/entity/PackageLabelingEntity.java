package com.appzoneltd.lastmile.microservice.packge.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "package_labeling", schema = "lastmile_core")
public class PackageLabelingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	private PackageEntity packageEntity;

	@ManyToOne
	@JoinColumn(name = "package_label_id", referencedColumnName = "package_label_id")
	private PackageLabelEntity packageLabel;

}
