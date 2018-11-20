package com.appzoneltd.lastmile.microservice.packge.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "verified_package_images", schema = "lastmile_core")
public class VerifiedPackageImagesEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	private VerifiedPackageEntity verifiedPackage;

	@ManyToOne
	@JoinColumn(name = "image_id", referencedColumnName = "content_id")
	private StaticContentEntity staticContent;

}
