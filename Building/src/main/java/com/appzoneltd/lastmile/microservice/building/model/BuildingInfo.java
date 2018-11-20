package com.appzoneltd.lastmile.microservice.building.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL, content = Include.NON_NULL)
public class BuildingInfo extends Building {

    /**
     *
     */
    private static final long serialVersionUID = 3898786275011406715L;

    private String countryNameAr;
    private String countryNameEn;
    private String cityNameAr;
    private String cityNameEn;
    private String type;
    private String countryCode;

    public BuildingInfo() {

    }

    public BuildingInfo(String countryNameAr, String countryNameEn, String cityNameAr, String cityNameEn, String type) {
        super();
        this.countryNameAr = countryNameAr;
        this.countryNameEn = countryNameEn;
        this.cityNameAr = cityNameAr;
        this.cityNameEn = cityNameEn;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
