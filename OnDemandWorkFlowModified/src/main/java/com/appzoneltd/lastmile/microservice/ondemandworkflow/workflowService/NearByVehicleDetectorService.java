package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.HubConfigurationRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.HubConfigurationEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.CancelReasonEnum;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.*;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowDao.VehicleModelDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public abstract class NearByVehicleDetectorService {

    @Autowired
    protected List<NearByVehicleDetectorService> enabledDetectorsList;


    @Autowired
    protected VehicleModelDao vehicleModelDao;

    @Autowired
    protected HubConfigurationRepository hubConfigurationRepository;

    @Autowired
    protected PickupRequestRepository pickupRequestRepository;

    @Autowired
    protected NearVehiclesDetector nearVehiclesDetector;

    @Autowired
    protected PackageRepository packageRepository;

    @Autowired
    protected RequestService requestService;

    static List<Long> gettingTheNearestVehicleWithDistances(List<Long> nearByVehicles, List<Double> vehicleDistances) {
        if (vehicleDistances.size() > 0) {
            Double theSmallest = vehicleDistances.get(0);
            int smallestIndex = 0;
            for (int index = 1; index < vehicleDistances.size(); index++) {
                if (vehicleDistances.get(index) < theSmallest) {
                    theSmallest = vehicleDistances.get(index);
                    smallestIndex = index;
                }
            }
            Long firstNearest = nearByVehicles.get(0);
            Long swappedNearest = nearByVehicles.get(smallestIndex);
            nearByVehicles.set(0, swappedNearest);
            nearByVehicles.set(smallestIndex, firstNearest);
        }
        // return result
        return nearByVehicles;
    }

    @PostConstruct
    public void registerToEnabledDetectorsList() {

        System.out.println("@PostConstruct");
        if (this.enabledDetectorsList != null) {

            System.out.println(this.getClass().getSimpleName());

            this.enabledDetectorsList.add(this);
        }
    }

    public VehicleDistancesSearchingModel getNearByVehicles(Long packageId, Long requestId, boolean workflowCall) {
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        VehicleDistancesSearchingModel nearByVehicles = null;
        if (pickupRequestEntity != null) {
            List<Long> availablePickupVehicles = getAvailablePickupHubVehicles(pickupRequestEntity.getHubId());
            System.out.println(">>> GETTING AVAILABLE " + availablePickupVehicles.toString());
            RequestLocation requestLocation = new RequestLocation();
            requestLocation.setLatitude(pickupRequestEntity.getPickuplatitude());
            requestLocation.setLongitude(pickupRequestEntity.getPickuplongitude());
            nearByVehicles = filterNearByVehicles(availablePickupVehicles, requestLocation, pickupRequestEntity.getHubId());
        }
        MyPrinter.print(nearByVehicles.toString(), nearByVehicles.toString());
        return nearByVehicles;
    }

    abstract List<Long> getAvailablePickupHubVehicles(Long hubId);

    private VehicleDistancesSearchingModel filterNearByVehicles(List<Long> availablePickupVehicles, RequestLocation requestLocation, Long hubId) {
        VehicleDistancesSearchingModel vehicleDistancesSearchingModel = new VehicleDistancesSearchingModel();
        List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationRepository.findSystemConfigurationForHub(hubId, 3L);
        if (hubConfigurationEntities != null && hubConfigurationEntities.size() > 0) {
            Double radius = hubConfigurationEntities.get(0).getValue().doubleValue() / 2;
            for (Long vehicleId : availablePickupVehicles) {
                VehicleLocation vehicleLocation = vehicleModelDao.getVehicleLocation(vehicleId);
                if (vehicleLocation != null) {
                    System.out.println(">> " + vehicleLocation.toString());
                    Double distance = nearVehiclesDetector.getDistanceBetweenUs(requestLocation, vehicleLocation);
                    // Checking if the Vehicle Is Nearest
                    if (distance <= radius) {
                        MyPrinter.print("H%%%%%%%%%%%% ", "H55555555555555555555");

                        vehicleDistancesSearchingModel.getNearByVehicles().add(vehicleId);
                        vehicleDistancesSearchingModel.getVehicleDistances().add(distance);
                    }
                }
            }
        }
        MyPrinter.print(vehicleDistancesSearchingModel.toString(), vehicleDistancesSearchingModel.toString());
        return vehicleDistancesSearchingModel;
    }

    List<Long> filterCapacity(Long packageId, Long requestId, boolean workflowCall, List<Long> nearByVehicles) {
        List<Long> vehiclesWithCapacity = new ArrayList<>();
        if ((nearByVehicles != null) && (!nearByVehicles.isEmpty())) {
            System.out.println(">>> NearBy Vehicles " + nearByVehicles.toString());
            PackageEntity packageEntity = packageRepository.findOne(packageId);
            PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
            vehiclesWithCapacity = filterVehiclesWithAvailableCapacity(nearByVehicles,
                    packageEntity.getActualweight().doubleValue(), pickupRequestEntity.getHubId());
            if ((vehiclesWithCapacity == null) || (vehiclesWithCapacity.isEmpty())) {
                // TODO CANCEL REASON
                if (workflowCall) {
                    cancelRequestForReason(requestId, packageId, CancelReasonEnum.NO_CAPACITY.name());
                }
            }
            System.out.println(">>> ACCEPTABLE WITH CAPACITIES " + vehiclesWithCapacity.toString());
        } else {
            if (workflowCall) {
                cancelRequestForReason(requestId, packageId, CancelReasonEnum.NO_VEHICLES.name());
            }
        }
        return vehiclesWithCapacity;
    }

    private List<Long> filterVehiclesWithAvailableCapacity(List<Long> nearByVehicles, double packageWeight, Long hubId) {
        List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationRepository.findSystemConfigurationForHub(hubId, 8L);
        List<Long> vehiclesWithCapacity = new ArrayList<Long>();
        if (hubConfigurationEntities != null && hubConfigurationEntities.size() > 0) {
            BigDecimal emergencyCapacityPercent = hubConfigurationEntities.get(0).getValue();
            for (Long vehicleId : nearByVehicles) {
                VehicleCapacity vehicleCapacity = vehicleModelDao.getVehicleCapacity(vehicleId);
                if (isVehicleCapacityAcceptable(vehicleCapacity, packageWeight, emergencyCapacityPercent)) {
                    System.out.println("Vehicle " + vehicleId + " ACCEPTABLE");
                    vehiclesWithCapacity.add(vehicleId);
                }
            }
        }
        return vehiclesWithCapacity;
    }

    private void cancelRequestForReason(Long requestId, Long packageId, String cancelReason) {
        PackageStatusModel packageStatusModel = new PackageStatusModel();
        packageStatusModel.setPackageId(packageId);
        packageStatusModel.setRequestId(requestId);
        packageStatusModel.setStatus(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE.getPackageStatus());
        // CHANGE STATUS
        requestService.changePackageStatus(packageStatusModel);

        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        pickupRequestEntity.setCancellationReason(cancelReason);
        if (pickupRequestEntity != null) {
            pickupRequestRepository.save(pickupRequestEntity);
        }
    }

    private boolean isVehicleCapacityAcceptable(VehicleCapacity vehicleCapacity, double packageWeight,
                                                BigDecimal emergencyCapacityPercent) {
        // init boolean to be returned
        boolean isAcceptable = false;
        Double emergencyCapacity = calculateEmergencyCapacity(vehicleCapacity.getCapacity(), emergencyCapacityPercent);
        Double totalWeight = 0.0;
        for (Double weight : vehicleCapacity.getOrderWeights()) {
            totalWeight += weight;
        }
        System.out.println("Total Weight " + totalWeight);
        Double remainWeight = vehicleCapacity.getCapacity() - totalWeight;
        remainWeight -= emergencyCapacity;
        System.out.println("Emergency Capacity" + emergencyCapacity);
        System.out.println("Remain Weight " + remainWeight);
        if (remainWeight >= packageWeight) {
            System.out.println("Accepted");
            isAcceptable = true;
        } else {
            System.out.println("NOT Accepted");
        }
        // return result
        return isAcceptable;
    }

    private Double calculateEmergencyCapacity(int vehicleCapacity, BigDecimal emergencyCapacityPercent) {
        Double emergencyCapacity = 0.0;
        emergencyCapacity = emergencyCapacityPercent.multiply(new BigDecimal(vehicleCapacity))
                .divide(new BigDecimal(100)).doubleValue();
        return emergencyCapacity;
    }

    List<Vehicle> getVehicleIdsForDriverIds(List<Long> nearestDrivers) {
        List<Vehicle> nearestVehicles = new ArrayList<>();
        if (nearestDrivers == null) {
            return nearestVehicles;
        }
        for (Long driverId : nearestDrivers) {
            Long vehicleId = vehicleModelDao.getVehicleForDriver(driverId);
            // Create New Vehicle
            Vehicle vehicle = new Vehicle();
            vehicle.setDriverId(driverId);
            vehicle.setVehicleId(vehicleId);
            nearestVehicles.add(vehicle);
        }
        // return Vehicles
        return nearestVehicles;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
