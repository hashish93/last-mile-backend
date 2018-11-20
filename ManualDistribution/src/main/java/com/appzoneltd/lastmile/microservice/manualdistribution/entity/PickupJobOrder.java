package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pickup_request", schema = "lastmile_request")
public class PickupJobOrder extends JobOrder {

    /**
     *
     */
    private static final long serialVersionUID = -7133166588211081505L;

    private Long jobTypeId;

    @Id
    @Column(name = "pickup_request_id")
    public Long getJobOrderId() {
        return jobOrderId;
    }

    @Column(name = "time_from", nullable = true)
    public String getTimeFrom() {
        return timeFrom;
    }

    @Column(name = "time_to", nullable = true)
    public String getTimeTo() {
        return timeTo;
    }

    @Column(name = "pickuplongitude", nullable = true)
    public String getJobLongitude() {
        return jobLongitude;
    }

    @Column(name = "pickuplatitude", nullable = true)
    public String getJobLatitude() {
        return jobLatitude;
    }

    @Column(name = "pickupformatedaddress", nullable = true)
    public String getJobAddress() {
        return jobAddress;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "pickupdate", nullable = true)
    public Date getJobDate() {
        return jobDate;
    }

    @Column(name = "request_status", nullable = true)
    public String getJobStatus() {
        return jobStatus;
    }

    @Column(name = "pickup_request_type_id", nullable = true)
    public Long getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Long jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    @Column(name = "hub_id")
    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }
}
