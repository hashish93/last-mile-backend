package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.util.Date;

public class SavePlanOrder {

    private Long orderId;
    private Long packageId;
    private String orderType;
    private Integer priority;
    private String estimatedTimeForArrival;
    private Date departureTime;
    private Date arrivalTime;
    private String status;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getEstimatedTimeForArrival() {
        return estimatedTimeForArrival;
    }

    public void setEstimatedTimeForArrival(String estimatedTimeForArrival) {
        this.estimatedTimeForArrival = estimatedTimeForArrival;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
