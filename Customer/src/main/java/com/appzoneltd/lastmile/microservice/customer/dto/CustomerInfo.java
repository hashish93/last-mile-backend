package com.appzoneltd.lastmile.microservice.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL, content = Include.NON_NULL)
public class CustomerInfo extends Customer {
    /**
     *
     */
    private static final long serialVersionUID = 2446400803203718860L;
    private String countryNameAr;
    private String countryNameEn;
    private String cityNameAr;
    private String cityNameEn;
    private Boolean isVerified;
    private Long countryCode;

    public CustomerInfo() {

    }

    public CustomerInfo(String countryNameAr, String countryNameEn, String cityNameAr, String cityNameEn) {
        super();
        this.countryNameAr = countryNameAr;
        this.countryNameEn = countryNameEn;
        this.cityNameAr = cityNameAr;
        this.cityNameEn = cityNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCityNameAr() {
        return cityNameAr;
    }

    public void setCityNameAr(String cityNameAr) {
        this.cityNameAr = cityNameAr;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }
}
