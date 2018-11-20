//package com.appzoneltd.lastmile.entity;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "plan_order_tmp", schema = "lastmile_plan")
//public class SavePlanOrderEntityWithStatus {
//
//
//    @Id
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "plan_id")
//    private Long planId;
//
//    @Column(name = "active_vehicle_id")
//    private Long activeVehicleId;
//
//    @Column(name = "package_id")
//    private Long packageId;
//
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @Column(name = "order_type")
//    private String jobOrderType;
//
//    @Column(name = "priority")
//    private Integer priorty;
//
//    @Column(name = "eta")
//    private String estimatedTimeForArrival;
//
//    @Temporal(TemporalType.TIME)
//    @Column(name = "departure_time", nullable = false)
//    private Date departureTime;
//
//    @Temporal(TemporalType.TIME)
//    @Column(name = "arrival_time", nullable = false)
//    private Date arrivalTime;
//
//    @Column(name = "status")
//    private String status;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getPlanId() {
//        return planId;
//    }
//
//    public void setPlanId(Long planId) {
//        this.planId = planId;
//    }
//
//    public Long getActiveVehicleId() {
//        return activeVehicleId;
//    }
//
//    public void setActiveVehicleId(Long activeVehicleId) {
//        this.activeVehicleId = activeVehicleId;
//    }
//
//    public Long getPackageId() {
//        return packageId;
//    }
//
//    public void setPackageId(Long packageId) {
//        this.packageId = packageId;
//    }
//
//    public String getJobOrderType() {
//        return jobOrderType;
//    }
//
//    public void setJobOrderType(String jobOrderType) {
//        this.jobOrderType = jobOrderType;
//    }
//
//    public Integer getPriorty() {
//        return priorty;
//    }
//
//    public void setPriorty(Integer priorty) {
//        this.priorty = priorty;
//    }
//
//    public String getEstimatedTimeForArrival() {
//        return estimatedTimeForArrival;
//    }
//
//    public void setEstimatedTimeForArrival(String estimatedTimeForArrival) {
//        this.estimatedTimeForArrival = estimatedTimeForArrival;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public Date getDepartureTime() {
//        return departureTime;
//    }
//
//    public void setDepartureTime(Date departureTime) {
//        this.departureTime = departureTime;
//    }
//
//    public Date getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void setArrivalTime(Date arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "SavePlanOrderEntity [id=" + id + ", planId=" + planId + ", activeVehicleId=" + activeVehicleId
//                + ", packageId=" + packageId + ", orderId=" + orderId + ", jobOrderType=" + jobOrderType + ", priorty="
//                + priorty + ", estimatedTimeForArrival=" + estimatedTimeForArrival + "]";
//    }
//
//
//}
