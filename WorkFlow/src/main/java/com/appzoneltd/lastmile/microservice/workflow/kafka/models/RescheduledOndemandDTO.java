package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
public class RescheduledOndemandDTO implements Serializable{

    private Long requestId;
    private Long packageId;
    private Long requesterId;
    private String timeFrom;
    private String timeTo;
    private Date pickupDate;
    private Boolean isWebUser;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Boolean getWebUser() {
        return isWebUser;
    }

    public void setWebUser(Boolean webUser) {
        isWebUser = webUser;
    }
}
