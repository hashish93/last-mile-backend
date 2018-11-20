package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pickup_request", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "PickupRequestEntity.countAll", query = "SELECT COUNT(x) FROM PickupRequestEntity x")
})
public class PickupRequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pickup_request_id", nullable = false)
    private Long pickupRequestId;

    @Column(name = "time_from", length = 50)
    private String timeFrom;

    @Column(name = "time_to", length = 50)
    private String timeTo;

    @Temporal(TemporalType.DATE)
    @Column(name = "pickupdate")
    private Date pickupdate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Long pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
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

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }
}
