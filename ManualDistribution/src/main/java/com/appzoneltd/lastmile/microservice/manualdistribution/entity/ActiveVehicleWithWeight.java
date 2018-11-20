package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

/**
 * Created by hashish on 4/2/2017.
 */
public class ActiveVehicleWithWeight {
    ActiveVehicle vehicle;
    int remainingWeight;

    public ActiveVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(ActiveVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getRemainingWeight() {
        return remainingWeight;
    }

    public void setRemainingWeight(int remainingWeight) {
        this.remainingWeight = remainingWeight;
    }
}
