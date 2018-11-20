package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import java.util.List;
import java.util.Vector;

/**
 * Created by alaa.nabil on 7/27/2017.
 */
public class VehicleDistancesSearchingModel {
    private final Vector<Long> nearByVehicles;
    private final Vector<Double> vehicleDistances;

    public VehicleDistancesSearchingModel() {
        this.nearByVehicles = new Vector<>();
        this.vehicleDistances = new Vector<>();
    }

    public List<Long> getNearByVehicles() {
        return nearByVehicles;
    }

    public List<Double> getVehicleDistances() {
        return vehicleDistances;
    }

    @Override
    public String toString() {
        return "ANA AHOOOOOOOOOOOOOOOOOOOOOOOOO :D 3shan 5aled myz3lsh : " + this.nearByVehicles.toString();
    }
}
