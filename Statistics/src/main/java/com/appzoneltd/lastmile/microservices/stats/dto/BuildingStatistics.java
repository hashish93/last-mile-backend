package com.appzoneltd.lastmile.microservices.stats.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created by alaa.nabil on 10/17/2017.
 */
public class BuildingStatistics implements Serializable {
    private Long buildingId;
    private String buildingName;
    private String buildingAddress;
    private String latitude;
    private String longitude;
    private String rankNumber;
    private BuildingRank buildingRank;
    private int requests;
    private List<BuildingServingArea> buildingServingAreas;
    private String country;
    private String city;

    public BuildingStatistics() {
    }

    public BuildingStatistics(Long buildingId, String buildingName, String buildingAddress, String latitude, String longitude, int requests, List<BuildingServingArea> buildingServingAreas, String country, String city) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.buildingAddress = buildingAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.requests = requests;
        this.buildingServingAreas = buildingServingAreas;
        this.country = country;
        this.city = city;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public BuildingRank getBuildingRank() {
        return buildingRank;
    }

    public void setBuildingRank(BuildingRank buildingRank) {
        this.buildingRank = buildingRank;
    }

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public List<BuildingServingArea> getBuildingServingAreas() {
        return buildingServingAreas;
    }

    public void setBuildingServingAreas(List<BuildingServingArea> buildingServingAreas) {
        this.buildingServingAreas = buildingServingAreas;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
