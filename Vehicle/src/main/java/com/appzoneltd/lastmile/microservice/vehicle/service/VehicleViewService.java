package com.appzoneltd.lastmile.microservice.vehicle.service;

import com.appzoneltd.lastmile.microservice.vehicle.model.TrackedVehicle;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 9/17/2017.
 */
public interface VehicleViewService {
    List<TrackedVehicle> fetchTrackedVehicle(Principal principal);
}
