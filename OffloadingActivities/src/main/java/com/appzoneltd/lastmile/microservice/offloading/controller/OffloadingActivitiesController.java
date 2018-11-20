package com.appzoneltd.lastmile.microservice.offloading.controller;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.offloading.dto.PackageSummaryModel;
import com.appzoneltd.lastmile.microservice.offloading.dto.VehicleSummaryModel;
import com.appzoneltd.lastmile.microservice.offloading.model.RequestModel;
import com.appzoneltd.lastmile.microservice.offloading.service.OffloadingActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 8/6/2017.
 */
@RestController
public class OffloadingActivitiesController {

    private final OffloadingActivitiesService offloadingActivitiesService;

    @Autowired
    public OffloadingActivitiesController(OffloadingActivitiesService offloadingActivitiesService) {
        this.offloadingActivitiesService = offloadingActivitiesService;
    }

    @RequestMapping(value = "/vehicleSummary", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleSummaryModel>> vehicleSummery(@RequestBody RequestModel requestModel) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
        final List<VehicleSummaryModel> vehicleSummaryModels = offloadingActivitiesService.generateOffloadingVehiclesSummary(requestModel.getHubId(),principal);
        return ResponseEntity.ok(vehicleSummaryModels);
    }

    @RequestMapping(value = "/packageOffloading", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity packageOffloading(@RequestBody PackageSummaryModel packageSummaryModel) throws EntityNotFoundException {
        offloadingActivitiesService.updatePackageOffloadingStatus(packageSummaryModel.getPackageId(), packageSummaryModel.getPackageOffloaded(), packageSummaryModel.getComment());
        return ResponseEntity.ok().build();
    }
}
