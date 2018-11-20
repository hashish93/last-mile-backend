package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Created by alaa.nabil on 7/26/2017.
 */

@Component
@ConditionalOnProperty(value = "features.CORPORATE_DRIVER.enabled", havingValue = "true")
public class CorporateNearByVehicleDetectorService extends NearByVehicleDetectorService {

    @Override
    List<Long> getAvailablePickupHubVehicles(Long hubId) {
        return vehicleModelDao.getAvailablePickupHubVehicles(hubId);
    }
}
