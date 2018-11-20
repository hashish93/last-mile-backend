package com.appzoneltd.lastmile.microservice.returnrequest.dto;

/**
 * Created by alaa.nabil on 3/29/2017.
 */
public class Location {
    private String longitude;
    private String latitude;
    private String formattedAddress;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Location setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }
}
