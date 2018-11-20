package com.appzoneltd.lastmile.microservice.lookup.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "package_type", schema = "lastmile_core")
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

}
