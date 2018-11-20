package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.FreelancerDriverJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.FreelancerDriverEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.VehicleDistancesSearchingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa.nabil on 7/26/2017.
 */

@Component
@ConditionalOnProperty(value = "features.FREELANCER_DRIVER.enabled", havingValue = "true")
public class FreelanceNearByVehicleDetectorService extends NearByVehicleDetectorService {

    @Autowired
    private FreelancerDriverJpaRepository freelancerDriverJpaRepository;
    @Autowired
    private BuildingRepository buildingJpaRepository;
    
    @Override
    List<Long> getAvailablePickupHubVehicles(Long hubId) {
        BuildingEntity buildingEntity = buildingJpaRepository.findOne(hubId);
        
        List<Long> availablePickupFreelanceVehicles = vehicleModelDao.getAvailablePickupFreelanceVehicles();
        
        List<Long> outputList = new ArrayList<>();
        
        for (Long driverId : availablePickupFreelanceVehicles) {
            if (!freelancerDriverJpaRepository.findOne(driverId).getCityId().equals(buildingEntity.getCityId())) {
            	
//                availablePickupFreelanceVehicles.remove(driverId);
            } else {
            	outputList.add(driverId);
            }
        }
//        return availablePickupFreelanceVehicles;
        System.out.println(">> FREELACER DRIVERS:: "+ outputList.size());
        return outputList;
    }

    @Override
    public VehicleDistancesSearchingModel getNearByVehicles(Long packageId, Long requestId, boolean workflowCall) {
        VehicleDistancesSearchingModel driverIds = super.getNearByVehicles(packageId, requestId, workflowCall);
        return filterByCreditLimit(driverIds, packageId);
    }

    private VehicleDistancesSearchingModel filterByCreditLimit(VehicleDistancesSearchingModel models, Long packageId) {
        String packageValue = packageRepository.findOne(packageId).getPackageValue();
        BigDecimal packageValueDec = new BigDecimal(packageValue);
        FreelancerDriverEntity freelancerDriverEntity = null;
        for (int i = 0; i < models.getNearByVehicles().size(); i++) {
            freelancerDriverEntity = freelancerDriverJpaRepository.findOne(models.getNearByVehicles().get(i));
            if (freelancerDriverEntity.getAmount().compareTo(packageValueDec) != 1) {
                models.getNearByVehicles().remove(i);
                models.getVehicleDistances().remove(i);
            }
        }
        return models;
    }
}
