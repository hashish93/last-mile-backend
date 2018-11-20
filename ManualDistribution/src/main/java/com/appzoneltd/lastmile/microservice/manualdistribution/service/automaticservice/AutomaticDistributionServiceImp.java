//package com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice;
//
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.Utility.DateUtility;
//import com.appzoneltd.lastmile.microservice.manualdistribution.couchbase.dao.ActiveVehicleRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dao.PlanTmpRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SavePlanOrderTmpRepo;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderDto;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderTodayDto;
//import com.appzoneltd.lastmile.microservice.manualdistribution.dto.SavePlanOrderDto;
//import com.appzoneltd.lastmile.microservice.manualdistribution.entity.*;
//import com.appzoneltd.lastmile.microservice.manualdistribution.model.PlanOutline;
//import com.appzoneltd.lastmile.microservice.manualdistribution.model.SavePlanOrder;
//import com.appzoneltd.lastmile.microservice.manualdistribution.model.VehiclePlanDetails;
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.ManualDistributionServiceImpl;
//import com.appzoneltd.lastmile.microservice.utility.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.ZoneId;
//import java.util.*;
//
//
//@Service
//public class AutomaticDistributionServiceImp implements AutomaticDistributionService {
//
//    @Autowired
//    private ActiveVehicleRepo activeVehicleRepo;
//    @Autowired
//    private ManualDistributionServiceImpl manualDistributionService;
//    @Autowired
//    private SavePlanOrderTmpRepo savePlanOrderTmpRepo;
//    @Autowired
//    private PlanTmpRepo planTmpRepo;
//
//    private List<ActiveVehicle> getActiveVEhicles() throws Exception {
//        return activeVehicleRepo.getActiveVehicles();
//    }
//
//    private List<JobOrderTodayDto> getPickupJobToday(Long hubId) throws Exception {
//        return manualDistributionService.getJobOrderForToday(hubId);
//    }
//
//    private List<JobOrderTodayDto> getDeliveryJobToday() throws Exception {
//        return manualDistributionService.getDeliveryJobOrderForToday();
//    }
//
//    public int activeVehiclePickupCount() {
//        return activeVehicleRepo.getActiveVehiclePickupCount();
//    }
//
//    public int activeVehicleDeliveryCount() {
//        return activeVehicleRepo.getActiveVehicleDeliveryCount();
//    }
//
//    public boolean isVehiclesExists() {
//        System.out.println("total count of pickup vehicles = " + activeVehiclePickupCount());
//        System.out.println("total count of delivery vehicles = " + activeVehicleDeliveryCount());
//        return (activeVehiclePickupCount() > 0) || (activeVehicleDeliveryCount() > 0);
//    }
//
//    private boolean isRequestsExists() throws Exception {
//        System.out.println("total count of pickup requests = " + getPickupJobToday().size());
//        System.out.println("total count of delivery vehicles = " + getDeliveryJobToday().size());
//        return (getPickupJobToday().size() > 0) || (getDeliveryJobToday().size() > 0);
//    }
//
//    public boolean isPickupVehicleExists() throws Exception {
//        int pickupJobsSize = getPickupJobToday().size();
//        int pickupActiveVehicleSize = activeVehiclePickupCount();
//        return pickupJobsSize <= 0 || pickupActiveVehicleSize > 0;
//    }
//
//    public boolean isDeliveryVehicleExists() throws Exception {
//        int DeliveryJobsSize = getDeliveryJobToday().size();
//        int DeliveryActiveVehicleSize = activeVehicleDeliveryCount();
//        return DeliveryJobsSize <= 0 || DeliveryActiveVehicleSize > 0;
//    }
//
//    public boolean isVehicleServeRequestTypes() throws Exception {
//        return isPickupVehicleExists() && isDeliveryVehicleExists();
//    }
//
//    private BigDecimal getPickupTotalCapacity() throws Exception {
//        List<JobOrderTodayDto> pickupJobs = getPickupJobToday(hubId);
//        BigDecimal totalCapacity = BigDecimal.ZERO;
//        for (JobOrderTodayDto job : pickupJobs) {
//            totalCapacity = totalCapacity.add(job.getActualWeight());
//        }
//        return totalCapacity;
//    }
//
//    private BigDecimal getDeliveryTotalCapacity() throws Exception {
//        List<JobOrderTodayDto> DeliveryJobs = getDeliveryJobToday();
//        BigDecimal totalCapacity = BigDecimal.ZERO;
//        for (JobOrderTodayDto job : DeliveryJobs) {
//            totalCapacity = totalCapacity.add(job.getActualWeight());
//        }
//        return totalCapacity;
//    }
//
//    private BigDecimal getPickupActiveVehicleCapacity() throws Exception {
//        List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//        BigDecimal totalCapacity = BigDecimal.ZERO;
//        for (ActiveVehicle vehicle : activeVehicles) {
//            if ("pickup".equalsIgnoreCase(vehicle.getPurpose()) || "pickup_delivery".equalsIgnoreCase(vehicle.getPurpose()))
//                totalCapacity = totalCapacity.add(new BigDecimal(vehicle.getCapacity()));
//        }
//        return totalCapacity;
//    }
//
//    private BigDecimal getDeliveryActiveVehicleCapacity() throws Exception {
//        List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//        BigDecimal totalCapacity = BigDecimal.ZERO;
//        for (ActiveVehicle vehicle : activeVehicles) {
//            if ("delivery".equalsIgnoreCase(vehicle.getPurpose()) || "pickup_delivery".equalsIgnoreCase(vehicle.getPurpose()))
//                totalCapacity = totalCapacity.add(new BigDecimal(vehicle.getCapacity()));
//        }
//        return totalCapacity;
//    }
//
//    private boolean isPickupVehicleServeCapacity() throws Exception {
//        BigDecimal pickupOrdersCapacity = getPickupTotalCapacity();
//        BigDecimal pickupVehiclesCapacity = getPickupActiveVehicleCapacity();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> total pickup weight" + pickupOrdersCapacity);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> total vehicles weight" + pickupVehiclesCapacity
//        );
//        int comparedResult = pickupOrdersCapacity.compareTo(pickupVehiclesCapacity);
//        return comparedResult != 1;
//    }
//
//    private boolean isPickupVehicleCanHoldRequest() throws Exception {
//        List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//        List<JobOrderTodayDto> pickupOrders = getPickupJobToday();
//        return !isOrderExceedCapacity(activeVehicles, pickupOrders);
//    }
//
//    private boolean isDeliveryVehicleCanHoldRequest() throws Exception {
//        List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//        List<JobOrderTodayDto> deliveryOrders = getDeliveryJobToday();
//        return !isOrderExceedCapacity(activeVehicles, deliveryOrders);
//    }
//
//    private boolean isvehiclesCanHoldRequest() throws Exception {
//        return (isPickupVehicleCanHoldRequest() && isDeliveryVehicleCanHoldRequest());
//    }
//
//    private boolean isOrderExceedCapacity(List<ActiveVehicle> activeVehicles, List<JobOrderTodayDto> orders) {
//        List<ActiveVehicleWithWeight> activeVehicleWithWeights = convertFromActiveVehicleToActiveVehicleWithWeight(activeVehicles);
//        for (JobOrderTodayDto job : orders) {
//            boolean isVehicleCanHoldJob = false;
//            for (ActiveVehicleWithWeight vehicle : activeVehicleWithWeights) {
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>> job weight" + job.getActualWeight());
//                int comparedResult = job.getActualWeight().compareTo(new BigDecimal(vehicle.getRemainingWeight()));
//                if (comparedResult != 1) {
//                    isVehicleCanHoldJob = true;
//                    vehicle.setRemainingWeight(vehicle.getRemainingWeight() - job.getActualWeight().intValue());
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>> vehicle total capacity" + vehicle.getVehicle().getCapacity());
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>> vehicle remaining" + vehicle.getRemainingWeight());
//                    break;
//                }
//            }
//            if (!isVehicleCanHoldJob) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isDeliveryVehicleServeCapacity() throws Exception {
//        BigDecimal deliveryOrdersCapacity = getDeliveryTotalCapacity();
//        BigDecimal deliveryVehiclesCapacity = getDeliveryActiveVehicleCapacity();
//        int comparedResult = deliveryOrdersCapacity.compareTo(deliveryVehiclesCapacity);
//        return comparedResult != 1;
//    }
//
//    private boolean isVehicleServeCapacity() throws Exception {
//        return isPickupVehicleServeCapacity() && isDeliveryVehicleServeCapacity();
//    }
//
//    private boolean checkWorkshift(PickupDate pickupDate1, PickupDate pickupDate2) {
//        return pickupDate1.getTimeFrom().after(pickupDate2.getTimeFrom()) && pickupDate1.getTimeFrom().before(pickupDate2.getTimeTo()) ||
//                pickupDate1.getTimeTo().after(pickupDate2.getTimeFrom()) && pickupDate1.getTimeTo().before(pickupDate2.getTimeTo());
//    }
//
//    private boolean checkVehicleWorkShiftIntersection() throws Exception {
//        List<JobOrderTodayDto> todayOrders = new ArrayList<JobOrderTodayDto>(getPickupJobToday());
//        todayOrders.addAll(getDeliveryJobToday());
//        List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//        boolean jobCheck = false;
//        for (JobOrderTodayDto job : todayOrders) {
//            PickupDate jobPickupDate = getPickupDateFromJob(job);
//            jobCheck = false;
//            for (ActiveVehicle vehicle : activeVehicles) {
//                PickupDate vehiclePickupDate = getPickupDateFromVehicle(vehicle);
//                if (checkWorkshift(jobPickupDate, vehiclePickupDate)) {
//                    jobCheck = true;
//                    break;
//                }
//            }
//            if (!jobCheck) {
//                return jobCheck;
//            }
//        }
//        return jobCheck;
//    }
//
//    private PickupDate getPickupDateFromJob(JobOrderTodayDto job) {
//        PickupDate jobPickupDate = new PickupDate();
//        jobPickupDate.setTimeFrom(manualDistributionService.convertFromStringToDate(new Date(), job.getTimeFrom()));
//        jobPickupDate.setTimeTo(manualDistributionService.convertFromStringToDate(new Date(), job.getTimeTo()));
//        return jobPickupDate;
//    }
//
//    private PickupDate getPickupDateFromVehicle(ActiveVehicle vehicle) {
//        PickupDate vehiclePickupDate = new PickupDate();
//        vehiclePickupDate.setTimeFrom(DateUtility.longToDate(vehicle.getWorkShiftFrom()));
//        vehiclePickupDate.setTimeTo(DateUtility.longToDate(vehicle.getWorkShiftTo()));
//        return vehiclePickupDate;
//    }
//
//    private boolean isVehicleMatchWeight(ActiveVehicleWithWeight vehicle, JobOrderTodayDto job) {
//        int comparedResult = job.getActualWeight().compareTo(new BigDecimal(vehicle.getRemainingWeight()));
//        return comparedResult != 1;
//    }
//
//    private boolean isVehicleMatchType(ActiveVehicle vehicle, JobOrderTodayDto job) {
//        return job.getJobType().equalsIgnoreCase(vehicle.getPurpose()) ||
//                ((job.getJobType().equalsIgnoreCase("pickup")) || (job.getJobType().equalsIgnoreCase("delivery"))) && (vehicle.getPurpose().equalsIgnoreCase("pickup_delivery"));
//    }
//
//    private boolean isVehicleMatchCritaria(JobOrderTodayDto job, ActiveVehicleWithWeight vehicle) {
//        return isVehicleMatchWeight(vehicle, job) && isVehicleMatchType(vehicle.getVehicle(), job) &&
//                checkWorkshift(getPickupDateFromJob(job), getPickupDateFromVehicle(vehicle.getVehicle()));
//    }
//
//    private List<PlanOutline> getPlan(List<ActiveVehicle> activeVehicles, List<JobOrderTodayDto> todayJobs) {
//        List<ActiveVehicleWithWeight> activeVehicleWithWeights = convertFromActiveVehicleToActiveVehicleWithWeight(activeVehicles);
//        HashMap<Long, List<Long>> plan = new HashMap<>();
//        for (JobOrderTodayDto job : todayJobs) {
//            for (ActiveVehicleWithWeight vehicle : activeVehicleWithWeights) {
//                if (isVehicleMatchCritaria(job, vehicle)) {
//                    vehicle.setRemainingWeight(vehicle.getRemainingWeight() - job.getActualWeight().intValue());
//                    System.out.println("job id " + job.getId());
//                    System.out.println(">>>>>>>>>>>>>>>>>>> success vehicle match criteria with vehicle id : " + vehicle.getVehicle().getVehicleId() + " and active vehicle id " + vehicle.getVehicle().getActiveVehicleId());
//                    List<Long> jobOrdersIds = plan.get(vehicle.getVehicle().getActiveVehicleId());
//                    if (jobOrdersIds == null) {
//                        jobOrdersIds = new ArrayList<>();
//                    }
//                    jobOrdersIds.add(job.getId());
//                    plan.put(vehicle.getVehicle().getActiveVehicleId(), jobOrdersIds);
//                    break;
//                }
//            }
//        }
//
//        List<PlanOutline> planOutlines = convertToPlanDetails(plan);
//        System.out.println(">>>hashmap<<<<");
//        System.out.println("map is " + planOutlines);
//        return planOutlines;
//    }
//
//
//    private List<PlanOutline> convertToPlanDetails(Map<Long, List<Long>> plan) {
//        List<PlanOutline> planOutlines = new ArrayList<>();
//        for (Map.Entry<Long, List<Long>> entry : plan.entrySet()) {
//            PlanOutline planOutline = new PlanOutline();
//            planOutline.setActiveVehicleId(entry.getKey());
//            planOutline.setJobOrderIds(entry.getValue());
//            planOutlines.add(planOutline);
//
//        }
//        return planOutlines;
//    }
//
//    private List<ActiveVehicleWithWeight> convertFromActiveVehicleToActiveVehicleWithWeight(List<ActiveVehicle> activeVehicles) {
//        List<ActiveVehicleWithWeight> activeVehicleWithWeight = new ArrayList<>();
//        for (ActiveVehicle vehicle : activeVehicles) {
//            ActiveVehicleWithWeight vehicleWithWeight = new ActiveVehicleWithWeight();
//            vehicleWithWeight.setVehicle(vehicle);
//            vehicleWithWeight.setRemainingWeight(vehicle.getCapacity());
//            activeVehicleWithWeight.add(vehicleWithWeight);
//        }
//        return activeVehicleWithWeight;
//    }
//
//    private List<VehiclePlanDetails> getPlanDetails(List<PlanOutline> plan) throws Exception {
//        List<VehiclePlanDetails> data = manualDistributionService
//                .buildPlanDetails(plan);
//        return data;
//    }
//
//    private boolean isReturnPlanValidated(List<VehiclePlanDetails> plan) {
//        for (VehiclePlanDetails vehicle : plan) {
//            for (JobOrderDto job : vehicle.getJobOrders()) {
//                if (JobOrderDto.JobStatus.FAIL.equals(job.getJobStatus())) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private List<SavePlanOrder> mapToSavePlan(List<JobOrderDto> jobOrderDtos) {
//        List<SavePlanOrder> savePlanOrders = new ArrayList<>();
//
//        for (JobOrderDto jobOrderDto : jobOrderDtos) {
//            SavePlanOrder savePlanOrder = new SavePlanOrder();
//            savePlanOrder.setOrderId(jobOrderDto.getId());
//            savePlanOrder.setPackageId(jobOrderDto.getOrderPackageId());
//            savePlanOrder.setEstimatedTimeForArrival(jobOrderDto.getTimeTakenRoutingEngineInText());
//            savePlanOrder.setOrderType(jobOrderDto.getJobType());
//            savePlanOrder.setPriority(jobOrderDto.getPriority());
//            savePlanOrder.setDepartureTime(Date.from(jobOrderDto.getDepartureTime().atZone(ZoneId.systemDefault()).toInstant()));
//            savePlanOrder.setArrivalTime(Date.from(jobOrderDto.getArrivalTime().atZone(ZoneId.systemDefault()).toInstant()));
//            savePlanOrder.setStatus(jobOrderDto.getJobStatus().name());
//            savePlanOrders.add(savePlanOrder);
//        }
//
//        return savePlanOrders;
//    }
//
//    @Override
//    public List<VehiclePlanDetails> planToBeGenerated() throws Exception {
//        System.out.println("isRequestsExists " + isRequestsExists());
//        System.out.println("isVehiclesExists " + isVehiclesExists());
//        System.out.println("isVehicleServeCapacity " + isVehicleServeCapacity());
//        System.out.println("isVehicleServeRequestTypes " + isVehicleServeRequestTypes());
//        System.out.println("checkVehicleWorkShiftIntersection " + checkVehicleWorkShiftIntersection());
//        System.out.println("isvehiclesCanHoldRequest " + isvehiclesCanHoldRequest());
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        if (isRequestsExists() && isVehiclesExists() && isVehicleServeCapacity() && isVehicleServeRequestTypes() && checkVehicleWorkShiftIntersection() && isvehiclesCanHoldRequest()) {
//            System.out.println("match all critiria");
//            List<ActiveVehicle> activeVehicles = getActiveVEhicles();
//            List<JobOrderTodayDto> todayOrders = new ArrayList<JobOrderTodayDto>(getPickupJobToday());
//            todayOrders.addAll(getDeliveryJobToday());
//            System.out.println("---------------------- preparing for plan ---------------------------");
//            List<VehiclePlanDetails> plan = getPlanDetails(getPlan(activeVehicles, todayOrders));
//            if (plan != null) {
//                //if (isReturnPlanValidated(plan)) {
//                return plan;
//                //}
//            }
//
//        }
//        return null;
//    }
//
//    @Override
//    public Long savePlan() throws Exception {
//        Long result = null;
//        List<VehiclePlanDetails> plan = planToBeGenerated();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> plan " + plan);
//        if (plan != null) {
//            List<SavePlanOrderDto> savePlanOrderDtos = new ArrayList<>();
//            for (VehiclePlanDetails vehicle : plan) {
//                SavePlanOrderDto savePlanOrderDto = new SavePlanOrderDto();
//                savePlanOrderDto.setActiveVehicleId(vehicle.getActiveVehicleId());
//                savePlanOrderDto.setJobOrders(mapToSavePlan(vehicle.getJobOrders()));
//                savePlanOrderDtos.add(savePlanOrderDto);
//            }
//            result = savePlanTmp(savePlanOrderDtos);
//        }
//        return result;
//    }
//
//    public Long savePlanTmp(List<SavePlanOrderDto> savePlanOrders) {
//        Long planId = Utils.generateUUID();
//        PlanEntityTmp plan = new PlanEntityTmp();
//        plan.setId(planId);
//        plan.setCreated(new Date());
//        savePlanOrderTmpRepo.deleteAll();
//        planTmpRepo.deleteAll();
//        planTmpRepo.save(plan);
//        for (SavePlanOrderDto savePlanOrder : savePlanOrders) {
//            for (SavePlanOrder job : savePlanOrder.getJobOrders()) {
//                Long Id = Utils.generateUUID();
//                SavePlanOrderEntityWithStatus savePlan = new SavePlanOrderEntityWithStatus();
//                savePlan.setPlanId(planId);
//                savePlan.setId(Id);
//                savePlan.setActiveVehicleId(savePlanOrder.getActiveVehicleId());
//                savePlan.setEstimatedTimeForArrival(job.getEstimatedTimeForArrival());
//                savePlan.setPriorty(job.getPriority());
//                savePlan.setOrderId(job.getOrderId());
//                savePlan.setPackageId(job.getPackageId());
//                savePlan.setJobOrderType(job.getOrderType());
//                savePlan.setArrivalTime(job.getArrivalTime());
//                savePlan.setDepartureTime(job.getDepartureTime());
//                savePlan.setStatus(job.getStatus());
//                savePlanOrderTmpRepo.save(savePlan);
//            }
//
//        }
//
//        return planId;
//    }
//}
