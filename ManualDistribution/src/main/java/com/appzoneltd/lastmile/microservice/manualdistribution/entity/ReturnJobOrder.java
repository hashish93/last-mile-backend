package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "return_request", schema = "lastmile_request")
public class ReturnJobOrder extends JobOrder {


    private static final long serialVersionUID = -5261717196327157164L;

    @Id
    @Column(name = "return_request_id")
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

    @Column(name = "return_longitude", nullable = true)
    public String getJobLongitude() {
        return jobLongitude;
    }

    @Column(name = "return_latitude", nullable = true)
    public String getJobLatitude() {
        return jobLatitude;
    }

    @Column(name = "recipientformatedaddress", nullable = true)
    public String getJobAddress() {
        return jobAddress;
    }


    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "return_date", nullable = true)
    public Date getJobDate() {
        return jobDate;
    }


    @Column(name = "request_status", nullable = true)
    public String getJobStatus() {
        return jobStatus;
    }

    @Column(name = "hub_id")
    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }
}
