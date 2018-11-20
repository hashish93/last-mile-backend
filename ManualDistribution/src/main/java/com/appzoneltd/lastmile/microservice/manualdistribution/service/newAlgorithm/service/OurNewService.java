//package com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.service;
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.Utility.DateUtility;
//import com.appzoneltd.lastmile.microservice.manualdistribution.couchbase.dao.ActiveVehicleRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dao.DeliveryJobOrderRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dao.PickupJobOrderRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderTodayDto;
//import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ActiveVehicle;
//import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
//import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PickupDate;
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.ManualDistributionServiceImpl;
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model.Region;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class OurNewService {
//
//	@Autowired
//	private ActiveVehicleRepo activeVehicleRepo;
//	@Autowired
//	private ManualDistributionServiceImpl manualDistributionService;
//	@Autowired
//	private PickupJobOrderRepo pickupJobOrderRepo;
//	@Autowired
//	private DeliveryJobOrderRepo deliveryJobOrderRepo;
//
//	@Autowired
//	private OrderRegionTransformationService orderRegionTransformationService;
//
//	private boolean isVehiclesExists() {
//		System.out.println("total count of pickup vehicles = " + activeVehiclePickupCount());
//		System.out.println("total count of delivery vehicles = " + activeVehicleDeliveryCount());
//		return (activeVehiclePickupCount() > 0) || (activeVehicleDeliveryCount() > 0);
//	}
//
//	private boolean isRequestsExists() throws Exception {
//		System.out.println("total count of pickup requests = " + getPickupJobToday().size());
//		System.out.println("total count of delivery vehicles = " + getDeliveryJobToday().size());
//		return (getPickupJobToday().size() > 0) || (getDeliveryJobToday().size() > 0);
//	}
//
//	private boolean isPickupVehicleExists() throws Exception {
//		int pickupJobsSize = getPickupJobToday().size();
//		int pickupActiveVehicleSize = activeVehiclePickupCount();
//		return pickupJobsSize <= 0 || pickupActiveVehicleSize > 0;
//	}
//
//	private boolean isDeliveryVehicleExists() throws Exception {
//		int DeliveryJobsSize = getDeliveryJobToday().size();
//		int DeliveryActiveVehicleSize = activeVehicleDeliveryCount();
//		return DeliveryJobsSize <= 0 || DeliveryActiveVehicleSize > 0;
//	}
//
//	private boolean isVehicleServeRequestTypes() throws Exception {
//		return isPickupVehicleExists() && isDeliveryVehicleExists();
//	}
//
//	private int activeVehiclePickupCount() {
//		return activeVehicleRepo.getActiveVehiclePickupCount();
//	}
//
//	private int activeVehicleDeliveryCount() {
//		return activeVehicleRepo.getActiveVehicleDeliveryCount();
//	}
//
//	private List<JobOrderTodayDto> getPickupJobToday() throws Exception {
//		return manualDistributionService.getJobOrderForToday(hubId);
//	}
//
//	private List<JobOrderTodayDto> getDeliveryJobToday() throws Exception {
//		return manualDistributionService.getDeliveryJobOrderForToday();
//	}
//
//	private boolean checkVehicleWorkShiftIntersection() throws Exception {
//		List<JobOrderTodayDto> todayOrders = new ArrayList<JobOrderTodayDto>(getPickupJobToday());
//		todayOrders.addAll(getDeliveryJobToday());
//		List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//		boolean jobCheck = false;
//		for (JobOrderTodayDto job : todayOrders) {
//			PickupDate jobPickupDate = getPickupDateFromJob(job);
//			jobCheck = false;
//			for (ActiveVehicle vehicle : activeVehicles) {
//				PickupDate vehiclePickupDate = getPickupDateFromVehicle(vehicle);
//				if (checkWorkshift(jobPickupDate, vehiclePickupDate)) {
//					jobCheck = true;
//					break;
//				}
//			}
//			if (!jobCheck) {
//				return jobCheck;
//			}
//		}
//		return jobCheck;
//	}
//
//	private List<ActiveVehicle> getActiveVEhicles() throws Exception {
//		return activeVehicleRepo.getActiveVehicles();
//	}
//
//	private PickupDate getPickupDateFromJob(JobOrderTodayDto job) {
//		PickupDate jobPickupDate = new PickupDate();
//		jobPickupDate.setTimeFrom(manualDistributionService.convertFromStringToDate(new Date(), job.getTimeFrom()));
//		jobPickupDate.setTimeTo(manualDistributionService.convertFromStringToDate(new Date(), job.getTimeTo()));
//		return jobPickupDate;
//	}
//
//	private PickupDate getPickupDateFromVehicle(ActiveVehicle vehicle) {
//		PickupDate vehiclePickupDate = new PickupDate();
//		vehiclePickupDate.setTimeFrom(DateUtility.longToDate(vehicle.getWorkShiftFrom()));
//		vehiclePickupDate.setTimeTo(DateUtility.longToDate(vehicle.getWorkShiftTo()));
//		return vehiclePickupDate;
//	}
//
//	private boolean checkWorkshift(PickupDate pickupDate1, PickupDate pickupDate2) {
//		return pickupDate1.getTimeFrom().after(pickupDate2.getTimeFrom())
//				&& pickupDate1.getTimeFrom().before(pickupDate2.getTimeTo())
//				|| pickupDate1.getTimeTo().after(pickupDate2.getTimeFrom())
//						&& pickupDate1.getTimeTo().before(pickupDate2.getTimeTo());
//	}
//
//	public void planToBeGenerated() throws Exception {
//		System.out.println("isRequestsExists " + isRequestsExists());
//		System.out.println("isVehiclesExists " + isVehiclesExists());
//		System.out.println("isVehicleServeRequestTypes " + isVehicleServeRequestTypes());
//		System.out.println("checkVehicleWorkShiftIntersection " + checkVehicleWorkShiftIntersection());
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		if (isRequestsExists() && isVehiclesExists() && isVehicleServeRequestTypes()
//				&& checkVehicleWorkShiftIntersection()) {
//			System.out.println("match all critiria");
//			List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//			List<JobOrderTodayDto> todayOrders = new ArrayList<JobOrderTodayDto>(getPickupJobToday());
//			todayOrders.addAll(getDeliveryJobToday());
//		}
//
//	}
//
//	public List<Region> testNewAlgorithm() {
//		List<JobOrder> jobOrders = getTodayJobOrders();
//		System.out.println(">> JOB ORDERS : "+jobOrders.toString());
//
//		List<Region> regions = orderRegionTransformationService.process(jobOrders);
//		System.out.println(">> REGIONS : "+regions);
//		return regions;
//	}
//
//	public List<JobOrder> getTodayJobOrders() {
//		List<JobOrder> jobOrders = new ArrayList<>(getAllPickupJobOrders());
//		jobOrders.addAll(getAllDeliveryJobOrders());
//		return jobOrders;
//	}
//
//	public List<JobOrder> getAllPickupJobOrders() {
//		List<JobOrder> jobOrders = pickupJobOrderRepo.jobOrderAllIForToday(new Date(), hubId);
//		return jobOrders;
//	}
//
//	public List<JobOrder> getAllDeliveryJobOrders() {
//		List<JobOrder> jobOrders = deliveryJobOrderRepo.jobOrderAllIForToday(new Date());
//		return jobOrders;
//	}
//
//}
