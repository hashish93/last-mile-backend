package com.appzoneltd.lastmile.microservice.distributionplan.dao;

/**
 * Created by hashish on 4/9/2017.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "plan_order_tmp", schema = "lastmile_plan")
public class PlanOrderTmpEntity implements Serializable, Comparator<PlanOrderTmpEntity> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "plan_id", nullable = false, insertable = false, updatable = false)
    private Long planId;

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

    @Column(name = "status", nullable = true)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Temporal(TemporalType.TIME)
    @Column(name = "departure_time", nullable = false)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "arrival_time", nullable = false)
    private Date arrivalTime;


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
    private PlanEntityTmp plan;

    public PlanOrderTmpEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getPriority() {
        return this.priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getEta() {
        return this.eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDriverResponse() {
        return this.driverResponse;
    }

    public void setDriverResponse(String driverResponse) {
        this.driverResponse = driverResponse;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public RejectionReasonEntity getRejectionReason() {
        return this.rejectionReason;
    }

    public void setRejectionReason(RejectionReasonEntity rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public ActiveVehicleEntity getActiveVehicle() {
        return activeVehicle;
    }

    public void setActiveVehicle(ActiveVehicleEntity activeVehicle) {
        this.activeVehicle = activeVehicle;
    }

	/*
     * public void setActiveVehicle(ActiveVehicleEntity activeVehicle) {
	 * this.activeVehicle = activeVehicle; }
	 *
	 * public ActiveVehicleEntity getActiveVehicle() { return
	 * this.activeVehicle; }
	 */

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	/*
     * public void setPackage(PackageEntity package) { this.package = package; }
	 *
	 * public PackageEntity getPackage() { return this.package; }

	 */

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public PlanEntityTmp getPlan() {
        return plan;
    }

    public void setPlan(PlanEntityTmp plan) {
        this.plan = plan;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public void setPackageEntity(PackageEntity packageEntity) {
        this.packageEntity = packageEntity;
    }

    @Override
    public int compare(PlanOrderTmpEntity o1, PlanOrderTmpEntity o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
