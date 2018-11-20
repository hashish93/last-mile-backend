package com.appzoneltd.lastmile.microservice.building.model;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Building implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 3180530009896717518L;

    private Long buildingId;

    @NotEmpty(message = "building.buildingname.notblank")
    @Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]+", message = "building.buildingname.format")
    private String buildingname;

    @Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "building.buildingnumber.format")
    private String buildingnumber;

    private Long countryCodeId;

    @Pattern(regexp = "^[0-9+]?[0-9]*$", message = "building.phone.errorformat")
    private String phoneNumber;

    @NotNull(message = "building.countryname.notblank")
    private Long countryId;


    @NotNull(message = "building.cityname.notblank")
    private Long cityId;

    private String area;

    @Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "building.street.format")
    private String street;

    private String waselcode;

    @NotEmpty(message = "building.longitude.notnull")
    @Pattern(regexp = "^$|^[-0-9]+\\.{0,1}[0-9]*$", message = "building.longitude.format")
    private String longitude;

    @NotEmpty(message = "building.latitude.notnull")
    @Pattern(regexp = "^$|^[-0-9]+\\.{0,1}[0-9]*$", message = "building.latitude.format")
    private String latitude;


    private List<ServingArealocation> buildingservingArea;

    @NotNull(message = "building.buildingtype.notnull")
    private Long buildingTypeId;

    @Size(max = 500, message = "building.description.size")
    private String description;

    private Date created;

    private EntityStatus status;

    private Long version;

    public Building() {

    }

    public Building(Long buildingId, String buildingname, String buildingnumber, Long countryId, Long cityId,
                    String area, String street, String waselcode, String longitude, String latitude, Long buildingTypeId,
                    String description, Date created, EntityStatus entityStatus, Long version, List<ServingArealocation> buildingservingArea) {
        super();
        this.buildingId = buildingId;
        this.buildingname = buildingname;
        this.buildingnumber = buildingnumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.area = area;
        this.street = street;
        this.waselcode = waselcode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.buildingTypeId = buildingTypeId;
        this.description = description;
        this.created = created;
        this.status = entityStatus;
        this.version = version;
        this.setBuildingServingArea(buildingservingArea);
    }

    public Long getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingname() {
        return this.buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWaselcode() {
        return this.waselcode;
    }

    public void setWaselcode(String waselcode) {
        this.waselcode = waselcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingnumber() {
        return buildingnumber;
    }

    public void setBuildingnumber(String buildingnumber) {
        this.buildingnumber = buildingnumber;
    }

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getBuildingTypeId() {
        return buildingTypeId;
    }

    public void setBuildingTypeId(Long buildingTypeId) {
        this.buildingTypeId = buildingTypeId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus entityStatus) {
        this.status = entityStatus;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<ServingArealocation> getBuildingServingArea() {
        return buildingservingArea;
    }

    public void setBuildingServingArea(List<ServingArealocation> buildingservingArea) {
        this.buildingservingArea = buildingservingArea;
    }

    public Long getCountryCodeId() {
        return countryCodeId;
    }

    public void setCountryCodeId(Long countryCodeId) {
        this.countryCodeId = countryCodeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
