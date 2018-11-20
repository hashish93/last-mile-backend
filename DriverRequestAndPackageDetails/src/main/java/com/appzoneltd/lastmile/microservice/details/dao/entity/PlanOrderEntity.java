package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "plan_order", schema = "lastmile_plan")
@NamedQueries({
        @NamedQuery(name = "PlanOrderEntity.countAll", query = "SELECT COUNT(x) FROM PlanOrderEntity x")
})
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

    @Column(name = "excluded")
    private Boolean isExcluded;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @ManyToOne
    @JoinColumn(name = "rejection_reason_id", referencedColumnName = "id")
    private RejectionReasonEntity rejectionReason;
    @Column(name = "rejection_reason", length = 250)
    private String rejectionReasonStr;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private PackageEntity packge;
    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanEntity plan;


    public PlanOrderEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRejectionReasonStr() {
        return rejectionReasonStr;
    }

    public void setRejectionReasonStr(String rejectionReasonStr) {
        this.rejectionReasonStr = rejectionReasonStr;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActiveVehicleId() {
        return this.activeVehicleId;
    }

    public void setActiveVehicleId(Long activeVehicleId) {
        this.activeVehicleId = activeVehicleId;
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

    public RejectionReasonEntity getRejectionReason() {
        return this.rejectionReason;
    }

    public void setRejectionReason(RejectionReasonEntity rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public PackageEntity getPackage() {
        return this.packge;
    }

    public void setPackage(PackageEntity packge) {
        this.packge = packge;
    }

    public PlanEntity getPlan() {
        return this.plan;
    }

    public void setPlan(PlanEntity plan) {
        this.plan = plan;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public PackageEntity getPackge() {
        return packge;
    }

    public void setPackge(PackageEntity packge) {
        this.packge = packge;
    }


    public Boolean getExcluded() {
        return isExcluded;
    }

    public void setExcluded(Boolean excluded) {
        isExcluded = excluded;
    }
}
