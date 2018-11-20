package com.appzoneltd.lastmile.microservice.offloading.service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.offloading.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.offloading.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.offloading.dao.couchbase.VehicleTrackingRepository;
import com.appzoneltd.lastmile.microservice.offloading.dao.entity.*;
import com.appzoneltd.lastmile.microservice.offloading.dao.repository.*;
import com.appzoneltd.lastmile.microservice.offloading.dto.PackageSummaryModel;
import com.appzoneltd.lastmile.microservice.offloading.dto.VehicleSummaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 8/6/2017.
 */
@Service
public class OffloadingActivitiesServiceImpl implements OffloadingActivitiesService {


    private final static List<String> OFFLOADED_PACKAGE_STATUSES = Arrays.asList("PICKEDUP", "RESCHEDULED_FOR_DELIVERY", "RESCHEDULED_FOR_RETURN", "CANCELED", "CANCELED_DELIVERY");
    private final VehicleTrackingRepository vehicleTrackingRepository;
    private final UsersJpaRepository usersJpaRepository;
    private final FreelancerDriverJpaRepository freelancerDriverJpaRepository;
    private final VehicleJpaRepository vehicleJpaRepository;
    private final RequestHistoryJpaRepository requestHistoryJpaRepository;
    private final PackageJpaRepository packageJpaRepository;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    public OffloadingActivitiesServiceImpl(VehicleTrackingRepository vehicleTrackingRepository, UsersJpaRepository usersJpaRepository, FreelancerDriverJpaRepository freelancerDriverJpaRepository, VehicleJpaRepository vehicleJpaRepository, RequestHistoryJpaRepository requestHistoryJpaRepository, PackageJpaRepository packageJpaRepository) {
        this.vehicleTrackingRepository = vehicleTrackingRepository;
        this.usersJpaRepository = usersJpaRepository;
        this.freelancerDriverJpaRepository = freelancerDriverJpaRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.requestHistoryJpaRepository = requestHistoryJpaRepository;
        this.packageJpaRepository = packageJpaRepository;
    }

    @Override
    public List<VehicleSummaryModel> generateOffloadingVehiclesSummary(Long hubId,Principal principal) {
    	List<Long> hubs = new ArrayList<>();
    	if(!principalService.isSuperAdmin(principal) && !principalService.isSuperVisor(principal)){
    		hubId = principalService.getHubIdIfFound(principal);
    	}
    	if(hubId !=null){
			hubs.add(hubId);
		}
         
        final List<RegistrationModel> registrationModels = vehicleTrackingRepository.findAllByHubIds(hubs);
        return registrationModels.stream().map(this::buildSummaryModel).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void updatePackageOffloadingStatus(Long packageId, Boolean isPackageOffloaded, String comment) throws EntityNotFoundException {
        final PackageEntity packageEntity = packageJpaRepository.findOne(packageId);
        if (packageEntity == null) throw new EntityNotFoundException("PACKAGE_NOT_FOUND");
        packageEntity.setPackageOffloaded(isPackageOffloaded);
        packageEntity.setOffloadingComment(comment);
        packageJpaRepository.save(packageEntity);
    }

    private VehicleSummaryModel buildSummaryModel(RegistrationModel registrationModel) {
        final List<PackageSummaryModel> packageSummaryModels = registrationModel.getJobOrders().parallelStream().map(this::offloadedPackage).filter(Objects::nonNull).collect(Collectors.toList());
        if (packageSummaryModels == null || packageSummaryModels.isEmpty())
            return null;

        final VehicleSummaryModel vehicleSummaryModel = new VehicleSummaryModel();
        vehicleSummaryModel.setPackageSummaryModels(packageSummaryModels);
        if(registrationModel.getHubId() !=null){
        	BuildingEntity buildingEntity = buildingRepository.findOne(registrationModel.getHubId());
        	if(buildingEntity !=null){
        		vehicleSummaryModel.setHubName(buildingEntity.getBuildingname());
        	}
        }
        
        vehicleSummaryModel.setDriverName(registrationModel.getDriverName());
        vehicleSummaryModel.setPersonalPhotoId(usersJpaRepository.findOne(registrationModel.getDriverId()).getPersonalPhotoId());
        vehicleSummaryModel.setPhoneNumber(registrationModel.getDriverNumber());
        vehicleSummaryModel.setVehicleType("FREELANCER_DRIVER".equalsIgnoreCase(registrationModel.getUserType())
                ? constructFreelancerVehicleDetails(registrationModel.getDriverId())
                : "CORPORATE_DRIVER".equalsIgnoreCase(registrationModel.getUserType())
                ? constructCorporateVehicleDetails(registrationModel.getVehicleId()) : null);
        return vehicleSummaryModel;
    }

    private String constructFreelancerVehicleDetails(Long driverId) {
        final FreelancerDriverEntity freelancerDriverEntity = freelancerDriverJpaRepository.findOne(driverId);
        if (freelancerDriverEntity != null)
            return freelancerDriverEntity.getBrand() + " " + freelancerDriverEntity.getModel() + " " + freelancerDriverEntity.getModelYear();
        return null;
    }

    private String constructCorporateVehicleDetails(Long vehicleId) {
        final VehicleEntity vehicleEntity = vehicleJpaRepository.findOne(vehicleId);
        if (vehicleEntity != null)
            return vehicleEntity.getBrand() + " " + vehicleEntity.getModel();
        return null;
    }

    private PackageSummaryModel offloadedPackage(JobOrder jobOrder) {
        if (!OFFLOADED_PACKAGE_STATUSES.contains(jobOrder.getOrderStatus())) return null;
        final List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository.findByRequestIdAndRequestType(jobOrder.getJobOrderId(), jobOrder.getOrderType());
        if (requestHistoryEntities == null || requestHistoryEntities.isEmpty()) return null;
        final PackageEntity packageEntity = requestHistoryEntities.get(0).getPackageEntity();
        final PackageSummaryModel packageSummaryModel = new PackageSummaryModel();
        packageSummaryModel.setPackageId(packageEntity.getPackageId());
        packageSummaryModel.setRequestId(jobOrder.getJobOrderId());
        packageSummaryModel.setTrackingNumber(packageEntity.getTrackingNumber());
        packageSummaryModel.setCategory(packageEntity.getVerifiedPackage().getPackageType().getType());
        packageSummaryModel.setWeight(jobOrder.getActualWeight().toString());
        packageSummaryModel.setRequestType(jobOrder.getOrderType());
        packageSummaryModel.setPackageOffloaded(packageEntity.getPackageOffloaded());
        packageSummaryModel.setComment(packageEntity.getOffloadingComment());
        return packageSummaryModel;
    }

}
