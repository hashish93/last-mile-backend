package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 8/3/2017.
 */
public class TodaySummary implements Serializable {
    private int totalOrders;
    private int pickupOrders;
    private int deliveryOrders;
    private int returnOrders;
    private int completedOrders;

    public int getTotalOrders() {
        return totalOrders;
    }

    public TodaySummary addTotalOrders(int totalOrders) {
        this.totalOrders += totalOrders;
        return this;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public TodaySummary addCompletedOrders(int completedOrders) {
        this.completedOrders += completedOrders;
        return this;
    }

    public int getPickupOrders() {
        return pickupOrders;
    }

    public TodaySummary addPickupOrders(int pickupOrders) {
        this.pickupOrders += pickupOrders;
        return this;
    }

    public int getDeliveryOrders() {
        return deliveryOrders;
    }

    public TodaySummary addDeliveryOrders(int deliveryOrders) {
        this.deliveryOrders += deliveryOrders;
        return this;
    }

    public int getReturnOrders() {
        return returnOrders;
    }

    public TodaySummary addReturnOrders(int returnOrders) {
        this.returnOrders += returnOrders;
        return this;
    }
}
