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
@Table(name = "package_label", schema = "lastmile_core")
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

}
