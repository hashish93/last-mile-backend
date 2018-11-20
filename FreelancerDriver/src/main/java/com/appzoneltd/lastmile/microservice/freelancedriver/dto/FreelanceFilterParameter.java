package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;

/**
 * Created by alaa.nabil on 10/26/2017.
 */
public class FreelanceFilterParameter extends EndPointParameter {
    private String driverName;
    private String city;
    private String mobileNumber;
    private String brand;
    private String model;
    private String status;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
