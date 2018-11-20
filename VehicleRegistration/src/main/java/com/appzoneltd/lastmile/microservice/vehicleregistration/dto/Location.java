package com.appzoneltd.lastmile.microservice.vehicleregistration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 2/22/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {
    private Double longitude;
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
