package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.io.Serializable;
import java.util.Comparator;
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

import lombok.Data;

@Entity
@Table(name = "plan_order", schema = "lastmile_plan")
@Data
public class PlanOrderEntity implements Serializable, Comparator<PlanOrderEntity> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "plan_id", nullable = false , insertable=false , updatable=false)
	private Long planId ;

	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "order_type", nullable = false, length = 100)
	private String orderType;

	@Column(name = "priority", nullable = false)
	private Long priority;

	@Column(name = "eta", nullable = false, length = 100)
	private String eta;

	@Column(name = "driver_response", length = 100, nullable = true)
	private String driverResponse;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Temporal(TemporalType.TIME)
	@Column(name = "departure_time", nullable = false)
	private Date departureTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "arrival_time", nullable = false)
	private Date arrivalTime;
	
	@Column(name = "status")
	private String status ; 
	
	
	@ManyToOne
	@JoinColumn(name = "rejection_reason_id", referencedColumnName = "id", nullable = true)
	private RejectionReasonEntity rejectionReason;

	@ManyToOne
	@JoinColumn(name = "active_vehicle_id", referencedColumnName = "id")
	// @Column(name = "active_vehicle_id", nullable = false)
	private ActiveVehicleEntity activeVehicle;

	@ManyToOne
	@JoinColumn(name = "package_id", referencedColumnName = "package_id")
	// @Column(name = "package_id", nullable = false)
	private PackageEntity packageEntity;

	@ManyToOne
	@JoinColumn(name = "plan_id", referencedColumnName = "id")
	private PlanEntity plan;
	
	@Override
	public int compare(PlanOrderEntity o1, PlanOrderEntity o2) {
		return o1.getId().compareTo(o2.getId());
	}

	@Override
	public String toString() {
		return "PlanOrderEntity [id=" + id + ", planId=" + planId + ", orderId=" + orderId + ", orderType=" + orderType
				+ ", priority=" + priority + ", eta=" + eta + ", driverResponse=" + driverResponse + ", created="
				+ created + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", status=" + status
				+ ", rejectionReason=" + rejectionReason + ", activeVehicle=" + activeVehicle + ", packageEntity="
				+ packageEntity + ", plan=" + plan + "]";
	}
	
	
	
	
}
