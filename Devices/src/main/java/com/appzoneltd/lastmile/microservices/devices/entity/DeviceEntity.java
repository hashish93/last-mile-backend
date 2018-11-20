package com.appzoneltd.lastmile.microservices.devices.entity;

import java.io.Serializable;
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

@Data
@Entity
@Table(name = "devices", catalog = "lastmile", schema = "lastmile_core")
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "device_id", nullable = false)
	private Long deviceId;

	@Column(name = "model", nullable = false, length = 500)
	private String model;

	@Column(name = "imei_info", nullable = false, length = 500)
	private String imeiInfo;

	@Column(name = "phonenumber", nullable = false, length = 500)
	private String phoneNumber;

	@Column(name = "status", length = 2147483647)
	private String status;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "created", insertable = false, updatable = false)
	private Date created;

	@Column(name = "version")
	private Long version;

	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private BrandEntity deviceBrand;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;

}
