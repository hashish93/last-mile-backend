package com.appzoneltd.lastmile.microservice.offloading.service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.offloading.dto.VehicleSummaryModel;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 8/6/2017.
 */
public interface OffloadingActivitiesService {
    List<VehicleSummaryModel> generateOffloadingVehiclesSummary(Long hubId,Principal principal);
    void updatePackageOffloadingStatus(Long packageId,Boolean isPackageOffloaded, String comment) throws EntityNotFoundException;
}
