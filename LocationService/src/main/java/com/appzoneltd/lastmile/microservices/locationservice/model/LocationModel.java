package com.appzoneltd.lastmile.microservices.locationservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by hashish on 5/9/2017.
 */

public class LocationModel {
    @NotNull(message = "location.latitude.required")
    @Pattern(regexp = "^$|^[-0-9]+\\.{0,1}[0-9]*$", message = "location.latitude.format")
    private String latitude;
    @NotNull(message = "location.longitude.required")
    @Pattern(regexp = "^$|^[-0-9]+\\.{0,1}[0-9]*$", message = "location.longitude.format")
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
