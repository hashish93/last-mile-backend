package com.appzoneltd.lastmile.microservice.vehicle.controller;

import com.appzoneltd.lastmile.microservice.vehicle.model.TrackedVehicle;
import com.appzoneltd.lastmile.microservice.vehicle.service.VehicleViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 9/17/2017.
 */
@RestController
public class VehicleViewController {

    private final VehicleViewService vehicleViewService;

    @Autowired
    public VehicleViewController(VehicleViewService vehicleViewService) {
        this.vehicleViewService = vehicleViewService;
    }

    @RequestMapping(value = "/trackedVehicles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> vehicleFindAllForHub() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        final List<TrackedVehicle> trackedVehicles = vehicleViewService.fetchTrackedVehicle(principal);
        return new ResponseEntity<>(trackedVehicles, HttpStatus.OK);
    }
}
