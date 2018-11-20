package com.appzoneltd.lastmile.microservice.packge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "shipment_service", schema = "lastmile_core")
public class ShipmentServiceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shipment_service_id", nullable = false)
	private Long shipmentServiceId;

	@Column(name = "service", nullable = false, length = 50)
	private String service;

	@Column(name = "description", length = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@OneToMany(mappedBy = "shipmentService", targetEntity = ShipmentServiceTypeEntity.class)
	private List<ShipmentServiceTypeEntity> shipmentServiceTypes;

}
