package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.NearByVehicleAssign;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.Vehicle;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.VehicleDistancesSearchingModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alaa.nabil on 7/26/2017.
 */
//@Component
public class NearByServiceDetectorManager implements INearByServiceDetectorManager {

    @Autowired
    private List<NearByVehicleDetectorService> nearByVehicleDetectorServiceList;

    @Override
    public void addServiceDetector(NearByVehicleDetectorService newServiceObject) {
        nearByVehicleDetectorServiceList.add(newServiceObject);
    }

    @Override
    public List<Long> getNearByVehicles(Long packageId, Long requestId, boolean workflowCall) {

        System.out.println("@@@@@>>>>>@@@@####$$$%%^^^&&&&^%$#@>> MANAGER:::getNearByVehicles() :  " + nearByVehicleDetectorServiceList.size());

        VehicleDistancesSearchingModel vehicleDistancesSearchingModel = new VehicleDistancesSearchingModel();

        for (NearByVehicleDetectorService nearByVehicleDetectorService : nearByVehicleDetectorServiceList) {
            VehicleDistancesSearchingModel nearByDrivers = nearByVehicleDetectorService.getNearByVehicles(packageId, requestId, workflowCall);
            System.out.println("nearby vehicle list size OF " + nearByVehicleDetectorService.toString() + "::: " + nearByDrivers.getNearByVehicles().size());
            vehicleDistancesSearchingModel.getNearByVehicles().addAll(nearByDrivers.getNearByVehicles());
            vehicleDistancesSearchingModel.getVehicleDistances().addAll(nearByDrivers.getVehicleDistances());
        }

        System.out.println("nearby vehicle list size::: " + vehicleDistancesSearchingModel.getNearByVehicles().size());

        List<Long> theNearestVehicleWithDistances =
                NearByVehicleDetectorService.gettingTheNearestVehicleWithDistances(vehicleDistancesSearchingModel.getNearByVehicles(),
                        vehicleDistancesSearchingModel.getVehicleDistances());

        System.out.println("will call:: filterCapacity()");

        return nearByVehicleDetectorServiceList.get(0).filterCapacity(packageId, requestId, workflowCall, theNearestVehicleWithDistances);
    }

    @Override
    public List<Vehicle> getNearByVehiclesForAssing(NearByVehicleAssign nearByVehicleAssign,
                                                    WorkflowPickupRequestInfo workflowPickupRequestInfo) {
        VehicleDistancesSearchingModel vehicleDistancesSearchingModel = new VehicleDistancesSearchingModel();
        nearByVehicleDetectorServiceList.forEach(nearByVehicleDetectorService -> {
            VehicleDistancesSearchingModel nearByDrivers = nearByVehicleDetectorService.getNearByVehicles(nearByVehicleAssign.getPackageId(), nearByVehicleAssign.getRequestId(), false);
            vehicleDistancesSearchingModel.getNearByVehicles().addAll(nearByDrivers.getNearByVehicles());
            vehicleDistancesSearchingModel.getVehicleDistances().addAll(nearByDrivers.getVehicleDistances());
        });
        List<Long> theNearestVehicleWithDistances = NearByVehicleDetectorService.gettingTheNearestVehicleWithDistances(vehicleDistancesSearchingModel.getNearByVehicles(), vehicleDistancesSearchingModel.getVehicleDistances());
        List<Long> nearestDriversWithAvailableCapacity = nearByVehicleDetectorServiceList.get(0).filterCapacity(nearByVehicleAssign.getPackageId(), nearByVehicleAssign.getRequestId(), false, theNearestVehicleWithDistances);
        return nearByVehicleDetectorServiceList.get(0).getVehicleIdsForDriverIds(nearestDriversWithAvailableCapacity);
    }
}
