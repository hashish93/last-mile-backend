package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "package", schema = "lastmile_core")
@Data
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "tracking_number", length = 100)
	private String trackingNumber;

	@Column(name = "is_package_offloaded")
	private Boolean isPackageOffloaded;

	@Column(name = "offloading_comment", length = 150)
	private String offloadingComment;

	@ManyToOne
	@JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
	private PackageTypeEntity packageType;

}
