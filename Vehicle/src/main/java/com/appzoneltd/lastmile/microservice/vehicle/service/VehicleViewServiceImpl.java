package com.appzoneltd.lastmile.microservice.vehicle.service;

import com.appzoneltd.lastmile.microservice.vehicle.couchbase.CustomRegistrationRepository;
import com.appzoneltd.lastmile.microservice.vehicle.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.vehicle.model.TrackedVehicle;
import com.appzoneltd.lastmile.microservice.vehicle.repo.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 9/17/2017.
 */
@Service
public class VehicleViewServiceImpl implements VehicleViewService {

    private final PrincipalService principalService;
    private final CustomRegistrationRepository customRegistrationRepository;
    private final BuildingRepository buildingRepository;

    @Autowired
    public VehicleViewServiceImpl(PrincipalService principalService, CustomRegistrationRepository customRegistrationRepository, BuildingRepository buildingRepository) {
        this.principalService = principalService;
        this.customRegistrationRepository = customRegistrationRepository;
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<TrackedVehicle> fetchTrackedVehicle(Principal principal) {
        final List<Long> hubs = principalService.getHubs(principal);
        return customRegistrationRepository.findAllByHubIds(hubs).stream()
                .map(this::mapToTrackedVehicleDto)
                .collect(Collectors.toList());
    }

    private TrackedVehicle mapToTrackedVehicleDto(RegistrationModel registrationModel) {
        return new TrackedVehicle().setVehicleId(registrationModel.getVehicleId())
                .setBuildingId(registrationModel.getHubId())
                .setBuildingName(buildingRepository.findOne(registrationModel.getHubId()).getBuildingname())
                .setUserType(registrationModel.getUserType());
    }
}
