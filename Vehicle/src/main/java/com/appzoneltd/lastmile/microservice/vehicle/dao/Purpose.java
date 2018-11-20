package com.appzoneltd.lastmile.microservice.vehicle.dao;

public enum Purpose {
    ON_DEMAND_SERVICES("ON_DEMAND_SERVICES"), SCHEDULED_SERVICES("SCHEDULED_SERVICES"), TRANSIT_SERVICES("TRANSIT_SERVICES");

    private final String purpose;

    private Purpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

}
