package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by alaa.nabil on 3/29/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnSchedule extends ReturnRequest {
    private Location location;
    private String returnDescription;
    private Boolean isSenderAddress;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public Boolean isSenderAddress() {
        return isSenderAddress;
    }

    public void setIsSenderAddress(Boolean senderAddress) {
        isSenderAddress = senderAddress;
    }
}
