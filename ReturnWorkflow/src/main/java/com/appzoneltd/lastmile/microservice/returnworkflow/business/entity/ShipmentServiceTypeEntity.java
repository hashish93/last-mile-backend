package com.appzoneltd.lastmile.microservice.returnworkflow.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "shipment_service_type", schema = "lastmile_core")
@NamedQueries({
		@NamedQuery(name = "ShipmentServiceTypeEntity.countAll", query = "SELECT COUNT(x) FROM ShipmentServiceTypeEntity x") })
public class ShipmentServiceTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shipment_service_type_id", nullable = false)
	private Long shipmentServiceTypeId;

	@Column(name = "type", nullable = false, length = 50)
	private String type;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	public ShipmentServiceTypeEntity() {
		super();
	}

	public Long getShipmentServiceTypeId() {
		return this.shipmentServiceTypeId;
	}

	public void setShipmentServiceTypeId(Long shipmentServiceTypeId) {
		this.shipmentServiceTypeId = shipmentServiceTypeId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
