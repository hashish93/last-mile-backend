package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_request", schema = "lastmile_request")
public class DeliveryJobOrder extends JobOrder {

    /**
     *
     */
    private static final long serialVersionUID = 4394210090339011427L;

    @Id
    @Column(name = "delivery_request_id")
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

    @Column(name = "recipientlongitude", nullable = true)
    public String getJobLongitude() {
        return jobLongitude;
    }

    @Column(name = "recipientlatitude", nullable = true)
    public String getJobLatitude() {
        return jobLatitude;
    }

    @Column(name = "recipientformatedaddress", nullable = true)
    public String getJobAddress() {
        return jobAddress;
    }


    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "deliverydate", nullable = true)
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
