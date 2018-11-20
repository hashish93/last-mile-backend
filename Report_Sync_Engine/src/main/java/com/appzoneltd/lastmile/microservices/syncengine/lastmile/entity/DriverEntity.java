package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "driver", schema = "lastmile_core")
public class DriverEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "rating")
	private BigDecimal rating;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;
}
