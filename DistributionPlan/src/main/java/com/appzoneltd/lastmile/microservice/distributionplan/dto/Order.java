package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 2/16/2017.
 */
public class Order implements Serializable{


    private Long orderId;
    private String actualWeight;
    private String packageType;
    private String content;
    private String driverResponse;
    private String reason;

    public Order() {
    }

    public Order(Long orderId, String actualWeight, String packageType, String content, String driverResponse, String reason) {
        this.orderId = orderId;
        this.actualWeight = actualWeight;
        this.packageType = packageType;
        this.content = content;
        this.driverResponse = driverResponse;
        this.reason = reason;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Order setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public Order setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
        return this;
    }

    public String getPackageType() {
        return packageType;
    }

    public Order setPackageType(String packageType) {
        this.packageType = packageType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Order setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDriverResponse() {
        return driverResponse;
    }

    public Order setDriverResponse(String driverResponse) {
        this.driverResponse = driverResponse;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Order setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
