package com.appzoneltd.lastmile.microservice.vehicle.model;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 9/18/2017.
 */
public class TrackedVehicle implements Serializable {
    private Long vehicleId;
    private Long buildingId;
    private String buildingName;
    private String userType;

    public Long getVehicleId() {
        return vehicleId;
    }

    public TrackedVehicle setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public TrackedVehicle setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public TrackedVehicle setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public TrackedVehicle setUserType(String userType) {
        this.userType = userType;
        return this;
    }
}
