package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.util.Date;

/**
 * Created by hashish on 3/29/2017.
 */
public class PickupDate {
    private Date timeFrom;
    private Date timeTo;

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }
}
