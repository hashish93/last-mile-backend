package com.appzoneltd.lastmile.microservice.packge.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "package", schema = "lastmile_core")
public class PackageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_id", nullable = false)
	private Long packageId;

	@Column(name = "nickname", length = 50)
	private String nickname;

	@Column(name = "package_value", nullable = false, length = 2147483647)
	private String packageValue;

	@Column(name = "actualweight", nullable = false)
	private BigDecimal actualweight;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "tracking_number", length = 100)
	private String trackingNumber;

	@ManyToOne
	@JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
	private PackageTypeEntity packageType;

	@OneToMany(mappedBy = "packageEntity", targetEntity = VerifiedPackageEntity.class)
	private List<VerifiedPackageEntity> verifiedPackages;

	@ManyToOne
	@JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
	private ShipmentServiceTypeEntity shipmentServiceType;

	@OneToMany(mappedBy = "packageEntity", targetEntity = PackageImagesEntity.class)
	private List<PackageImagesEntity> packageImages;

	@OneToMany(mappedBy = "packageEntity", targetEntity = PackageLabelingEntity.class)
	private List<PackageLabelingEntity> packageLabelings;
}
