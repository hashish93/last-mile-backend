package com.appzoneltd.lastmile.microservice.workflowservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflowservice.dao.SystemConfigJpaRepository;
import com.appzoneltd.lastmile.microservice.workflowservice.dao.VehicleModelDao;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.WorkflowServiceProducer;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.CancelReasonEnum;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageStatus;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageWeight;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupDiameter;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NearByVehicleAssign;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NearVehiclesDetector;
import com.appzoneltd.lastmile.microservice.workflowservice.model.Vehicle;
import com.appzoneltd.lastmile.microservice.workflowservice.model.VehicleCapacity;
import com.appzoneltd.lastmile.microservice.workflowservice.model.VehicleLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NearByVehicleDetectorService {

	@Autowired
	private VehicleModelDao vehicleModelDao;

	@Autowired
	private SystemConfigJpaRepository systemConfigJpaRepository;

	@Autowired
	private NearVehiclesDetector nearVehiclesDetector;

	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;

	@Autowired
	private ObjectMapper mapper;

	public List<Long> getAvailablePickupHubVehicles(Long hubId) {
		// return result
		return vehicleModelDao.getAvailablePickupHubVehicles(hubId);
	}// end getNearByVehicles Method

	private List<Long> filterNearByVehicles(List<Long> availablePickupVehicles,
			WorkflowPackageLocation workflowPackageLocation, WorkflowPickupDiameter workflowPickupDiameter) {
		/// Init NearByVehicleIds
		List<Long> nearByVehicles = new ArrayList<Long>();
		List<Double> vehicleDistances = new ArrayList<Double>();
		//// Getting NearByVehicleList
		System.out.println(">> " + availablePickupVehicles.toString());
		/// Getting the Radius
		Double radius = workflowPickupDiameter.getDiameter().doubleValue() / 2;
		// Check if the Distance Less Than or Equal the Mapped Circle
		for (Long vehicleId : availablePickupVehicles) {
			VehicleLocation vehicleLocation = vehicleModelDao.getVehicleLocation(vehicleId);
			if (vehicleLocation != null) {
				System.out.println(">> " + vehicleLocation.toString());
				Double distance = nearVehiclesDetector.getDistanceBetweenUs(workflowPackageLocation, vehicleLocation);
				// Checking if the Vehicle Is Nearest
				if (distance <= radius) {
					/// Add it TO List
					nearByVehicles.add(vehicleId);
					vehicleDistances.add(distance);
				} // end if Condition
			} // end outer if condition

		} // end for_each
			/// Sorting the ArrayList Before Reply
			// return result
		return gettingTheNearestVehicleWithDistances(nearByVehicles, vehicleDistances);
	}// end filterNearByVehicles

	public List<Long> gettingTheNearestVehicleWithDistances(List<Long> nearByVehicles, List<Double> vehicleDistances) {
		// Check if NearByVehicles Not Null
		if (vehicleDistances.size() > 0) {
			Double theSmallest = vehicleDistances.get(0);
			int smallestIndex = 0;
			for (int index = 1; index < vehicleDistances.size(); index++) {
				if (vehicleDistances.get(index) < theSmallest) {
					theSmallest = vehicleDistances.get(index);
					smallestIndex = index;
				} // end if Condition
			} // end for Loop
			Long firstNearest = nearByVehicles.get(0);
			Long swappedNearest = nearByVehicles.get(smallestIndex);

			nearByVehicles.set(0, swappedNearest);
			nearByVehicles.set(smallestIndex, firstNearest);
		} // end if condition
			// return result
		return nearByVehicles;
	}// end gettingTheNearestVehicleWithDistances

	private List<Long> filterVehiclesWithAvailableCapacity(List<Long> nearByVehicles, double packageWeight) {
		BigDecimal emergencyCapacityPercent = systemConfigJpaRepository.findOne(8L).getValue();
		/// Init NearByVehicleIds
		List<Long> vehiclesWithCapacity = new ArrayList<Long>();
		for (Long vehicleId : nearByVehicles) {
			VehicleCapacity vehicleCapacity = vehicleModelDao.getVehicleCapacity(vehicleId);
			if (isVehicleCapacityAcceptable(vehicleCapacity, packageWeight, emergencyCapacityPercent)) {
				System.out.println("Vehicle " + vehicleId + " ACCEPTABLE");
				vehiclesWithCapacity.add(vehicleId);
			} // end if
		} // end for Loop
			// return result
		return vehiclesWithCapacity;
	}// end filterVehiclesWithAvailableCapacity

	private boolean isVehicleCapacityAcceptable(VehicleCapacity vehicleCapacity, double packageWeight,
			BigDecimal emergencyCapacityPercent) {
		// init boolean to be returned
		boolean isAcceptable = false;
		Double emergencyCapacity = calculateEmergencyCapacity(vehicleCapacity.getCapacity(), emergencyCapacityPercent);
		Double totalWeight = 0.0;
		for (Double weight : vehicleCapacity.getOrderWeights()) {
			totalWeight += weight;
		} // end for Each
		System.out.println("Total Weight " + totalWeight);
		// remain Weight
		Double remainWeight = vehicleCapacity.getCapacity() - totalWeight;
		remainWeight -= emergencyCapacity;
		System.out.println("Emergency Capacity" + emergencyCapacity);
		System.out.println("Remain Weight " + remainWeight);
		/// Check if Remain Capacity acceptable
		if (remainWeight >= packageWeight) {
			System.out.println("Accepted");
			isAcceptable = true;
		} else {
			System.out.println("NOT Accepted");
		} // end if-else
			// return result
		return isAcceptable;
	}// end isVehicleCapacityAcceptable

	private Double calculateEmergencyCapacity(int vehicleCapacity, BigDecimal emergencyCapacityPercent) {
		Double emergencyCapacity = 0.0;
		emergencyCapacity = emergencyCapacityPercent.multiply(new BigDecimal(vehicleCapacity))
				.divide(new BigDecimal(100)).doubleValue();
		return emergencyCapacity;
	}

	public List<Long> getNearByVehicles(WorkflowPickupRequestInfo workflowPickupRequestInfo , WorkflowPackageLocation workflowPackageLocation,
			WorkflowPickupDiameter workflowPickupDiameter, WorkflowPackageHub workflowPackageHub,
			WorkflowPackageWeight workflowPackageWeight, boolean workflowCall) {
		/// Getting AvailablePickupHubVehicles
		Long packageId = workflowPackageLocation.getPackageId();

		List<Long> availablePickupVehicles = getAvailablePickupHubVehicles(workflowPackageHub.getHubId());
		// Getting NearBy Vehicles
		List<Long> nearByVehicles = filterNearByVehicles(availablePickupVehicles, workflowPackageLocation,
				workflowPickupDiameter);
		List<Long> vehiclesWithCapacity = new ArrayList<>();

		if ((nearByVehicles != null) && (!nearByVehicles.isEmpty())) {
			System.out.println(">>> NearBy Vehicles " + nearByVehicles.toString());
			// Getting Vehicles With Available Weight
			vehiclesWithCapacity = filterVehiclesWithAvailableCapacity(nearByVehicles,
					workflowPackageWeight.getWeight().doubleValue());
			if ((vehiclesWithCapacity == null) || (vehiclesWithCapacity.isEmpty())) {
				// TODO CANCEL REASON
				if (workflowCall) {
					cancelRequestForReason(workflowPickupRequestInfo, CancelReasonEnum.NO_CAPACITY.name());
				}
			}
			System.out.println(">>> ACCEPTABLE WITH CAPACITIES " + vehiclesWithCapacity.toString());
		} else {
			if (workflowCall) {
				cancelRequestForReason(workflowPickupRequestInfo, CancelReasonEnum.NO_VEHICLES.name());
			}
		}
		// return result
		return vehiclesWithCapacity;
	}// end getNearByVehicles Method

	public List<Vehicle> getNearByVehiclesForAssing(NearByVehicleAssign nearByVehicleAssign,WorkflowPickupRequestInfo workflowPickupRequestInfo) {
		/// Filling Package Locations
		WorkflowPackageLocation workflowPackageLocation = new WorkflowPackageLocation();
		workflowPackageLocation.setPackageId(nearByVehicleAssign.getPackageId());
		workflowPackageLocation.setLatitude(nearByVehicleAssign.getLocation().getLatitude());
		workflowPackageLocation.setLongitude(nearByVehicleAssign.getLocation().getLongitude());

		// Filling Pacakge Diameter
		WorkflowPickupDiameter workflowPickupDiameter = new WorkflowPickupDiameter();
		workflowPickupDiameter.setDiameter(nearByVehicleAssign.getDiameter());
		workflowPickupDiameter.setPackageId(nearByVehicleAssign.getPackageId());

		// Filling WorkFlow PackageHub
		WorkflowPackageHub workflowPackageHub = new WorkflowPackageHub();
		workflowPackageHub.setHubId(nearByVehicleAssign.getHubId());
		workflowPackageHub.setPackageId(nearByVehicleAssign.getPackageId());

		// Flling WorkFlow PackageWeight
		WorkflowPackageWeight workflowPackageWeight = new WorkflowPackageWeight();
		workflowPackageWeight.setPackageId(nearByVehicleAssign.getPackageId());
		workflowPackageWeight.setWeight(nearByVehicleAssign.getWeight());

		/// Getting List Of Vehicle
		List<Long> nearestDrivers = getNearByVehicles(workflowPickupRequestInfo, workflowPackageLocation,
				workflowPickupDiameter, workflowPackageHub, workflowPackageWeight,false);

		List<Vehicle> nearestVehicles = new ArrayList<>();

		for (Long driverId : nearestDrivers) {
			Long vehicleId = vehicleModelDao.getVehicleForDriver(driverId);
			// Create New Vehicle
			Vehicle vehicle = new Vehicle();
			vehicle.setDriverId(driverId);
			vehicle.setVehicleId(vehicleId);
			/// Adding to List
			nearestVehicles.add(vehicle);
		} // end for Loop
			// return result
		return nearestVehicles;

	}// end getNearByVehicles Method

	private void cancelRequestForReason(WorkflowPickupRequestInfo workflowPickupRequestInfo, String cancelReason) {
		WorkflowPackageStatus workflowPackageStatus = new WorkflowPackageStatus();
		workflowPackageStatus.setRequestId(workflowPickupRequestInfo.getRequestId());
		workflowPackageStatus.setPackageId(workflowPickupRequestInfo.getPackageId());
		workflowPackageStatus.setCancelReason(cancelReason);
		workflowPackageStatus.setStatus(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE);
		workflowPackageStatus.setRequesterId(workflowPickupRequestInfo.getRequesterId());
        workflowPackageStatus.setIsWebUser(false);
		

		try {
			workflowServiceProducer.sendMessage("ChangePackageStatusRequest",
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatus));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
