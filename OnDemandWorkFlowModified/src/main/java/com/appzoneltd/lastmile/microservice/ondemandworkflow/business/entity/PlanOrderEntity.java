package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "plan_order", schema = "lastmile_plan")
public class PlanOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "active_vehicle_id", nullable = false)
	private Long activeVehicleId;

	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "order_type", nullable = false, length = 100)
	private String orderType;

	@Column(name = "priority", nullable = false)
	private Long priority;

	@Column(name = "eta", nullable = false, length = 100)
	private String eta;

	@Temporal(TemporalType.TIME)
	@Column(name = "departure_time", nullable = false)
	private Date departureTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "arrival_time", nullable = false)
	private Date arrivalTime;

	@Column(name = "driver_response", length = 100)
	private String driverResponse;

	@Column(name = "rejection_reason", length = 100)
	private String rejectionReason;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "status", length = 2147483647)
	private String status;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	private PackageEntity packageEntity;

	
	@Column(name = "excluded", length = 100)
	private boolean excluded;
	
	@ManyToOne
	@JoinColumn(name = "plan_id", referencedColumnName = "id")
	private PlanEntity plan;
}
