package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
public class JobOrderTiming implements Serializable {
    private Date departure;
    private Date arrival;
    private String pickupStart;
    private String pickupEnd;
    private String etaText;
    private Long etaSeconds;

    public JobOrderTiming(Date departure, Date arrival, String pickupStart, String pickupEnd, String etaText, Long etaSeconds) {
        this.departure = departure;
        this.arrival = arrival;
        this.pickupStart = pickupStart;
        this.pickupEnd = pickupEnd;
        this.etaText = etaText;
        this.etaSeconds = etaSeconds;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public String getPickupStart() {
        return pickupStart;
    }

    public void setPickupStart(String pickupStart) {
        this.pickupStart = pickupStart;
    }

    public String getPickupEnd() {
        return pickupEnd;
    }

    public void setPickupEnd(String pickupEnd) {
        this.pickupEnd = pickupEnd;
    }

    public String getEtaText() {
        return etaText;
    }

    public void setEtaText(String etaText) {
        this.etaText = etaText;
    }

    public Long getEtaSeconds() {
        return etaSeconds;
    }

    public void setEtaSeconds(Long etaSeconds) {
        this.etaSeconds = etaSeconds;
    }
}
