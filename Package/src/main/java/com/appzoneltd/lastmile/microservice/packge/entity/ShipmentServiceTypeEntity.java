package com.appzoneltd.lastmile.microservice.packge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "shipment_service_type", schema = "lastmile_core")
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

	@OneToMany(mappedBy = "shipmentServiceType", targetEntity = PackageEntity.class)
	private List<PackageEntity> packageEntities;

	@ManyToOne
	@JoinColumn(name = "shipment_service_id", referencedColumnName = "shipment_service_id")
	private ShipmentServiceEntity shipmentService;

}
