package com.appzoneltd.lastmile.microservice.returnworkflow.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "package", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "PackageEntity.countAll", query = "SELECT COUNT(x) FROM PackageEntity x") })
public class PackageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "package_id", nullable = false)
	private Long packageId;

	@Column(name = "nickname", length = 50)
	private String nickname;

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
	@JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
	private ShipmentServiceTypeEntity shipmentServiceType;

	public PackageEntity() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public BigDecimal getActualweight() {
		return actualweight;
	}

	public void setActualweight(BigDecimal actualweight) {
		this.actualweight = actualweight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public ShipmentServiceTypeEntity getShipmentServiceType() {
		return shipmentServiceType;
	}

	public void setShipmentServiceType(ShipmentServiceTypeEntity shipmentServiceType) {
		this.shipmentServiceType = shipmentServiceType;
	}

}
