package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
public class JobOrder implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private long packageId;
    private String packageType;
    private int priority;
    private BigDecimal weight;
    private String address;
    private Location location;
    private JobOrderTiming timing;
    private String requestType;
    private String status;
    private String timeFrom;
    private String timeTo;
    private String response;
    private String rejectionReason;
    private boolean excluded;
    private boolean next;

    public String getTimeFrom() {
        return timeFrom;
    }

    public JobOrder setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public JobOrder setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public JobOrderTiming getTiming() {
        return timing;
    }

    public void setTiming(JobOrderTiming timing) {
        this.timing = timing;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
