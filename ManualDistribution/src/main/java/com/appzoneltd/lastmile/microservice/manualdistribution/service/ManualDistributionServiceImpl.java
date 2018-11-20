package com.appzoneltd.lastmile.microservice.manualdistribution.service;

import com.appzoneltd.lastmile.microservice.manualdistribution.Utility.DateUtility;
import com.appzoneltd.lastmile.microservice.manualdistribution.comparator.JourneyTimeInSecondsComparator;
import com.appzoneltd.lastmile.microservice.manualdistribution.couchbase.dao.ActiveVehicleRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.HubLocationRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.JobDeliveryOrderRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SystemConfigRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderDto.JobStatus;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderTodayDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.LocationDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.Mapper;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ActiveVehicle;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.Location;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SystemConfig;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.*;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.distancematrix.google.GoogleDistanceMatrixService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class ManualDistributionServiceImpl implements ManualDistributionService {

    private static final JourneyTimeInSecondsComparator journeyTimeInSecondsComparator = new JourneyTimeInSecondsComparator();
    private final ActiveVehicleRepo activeVehicleRepo;
    private final Mapper mapper;
    private final SystemConfigRepo systemConfigRepo;
    private final HubLocationRepo hubLocationRepo;
    private final JobDeliveryOrderRepo jobDeliveryOrderRepo;
    @Autowired
    private GoogleDistanceMatrixService googleDistanceMatrixService;

//    @Autowired
//    private AutomaticDistributionServiceImp automaticDistributionServiceImp;

    @Autowired
    public ManualDistributionServiceImpl(ActiveVehicleRepo activeVehicleRepo,
                                         SystemConfigRepo requestProcessingTimeRepo, HubLocationRepo hubLocationRepo,
                                         JobDeliveryOrderRepo jobDeliveryOrderRepo, Mapper mapper) {
        this.activeVehicleRepo = activeVehicleRepo;
        this.systemConfigRepo = requestProcessingTimeRepo;
        this.hubLocationRepo = hubLocationRepo;
        this.jobDeliveryOrderRepo = jobDeliveryOrderRepo;
        this.mapper = mapper;
    }

    @Override
    public List<VehiclePlanDetails> buildPlanDetails(List<PlanOutline> planOutlineList) throws Exception {
        List<VehiclePlanDetails> vehiclePlanDetails = validateAndSetTimesOfArrival(planOutlineList);

        validateAndSetVehicleCapacity(vehiclePlanDetails);

        return vehiclePlanDetails;
    }

    public void validateAndSetVehicleCapacity(List<VehiclePlanDetails> vehiclePlanDetails) {

        for (VehiclePlanDetails vehiclePlanDetail : vehiclePlanDetails) {

            ActiveVehicle activeVehicle = vehiclePlanDetail.getActiveVehicle();

            // Integer activeVehicleCapacity = activeVehicle.getCapacity();

            Integer availableVehicleCapacity = getVehicleAvailableCapacity(activeVehicle,
                    getEmergencyVehicleCapacity());

            List<JobOrderDto> jobOrders = vehiclePlanDetail.getJobOrders();
            Integer totalCapacityOfOrders = calculateTotalCapacityOfOrders(jobOrders);

            if (totalCapacityOfOrders > availableVehicleCapacity) {
                vehiclePlanDetail.setVehicleOverWeight(true);
            } else {
                vehiclePlanDetail.setVehicleOverWeight(false);
            }
        }
    }

    public Integer calculateTotalCapacityOfOrders(List<JobOrderDto> jobOrders) {
        Integer totalCapacityOfOrders = 0;
        // TODO to be revisited when delivery is implemented
        for (JobOrderDto jobOrderDto : jobOrders) {
            totalCapacityOfOrders += jobOrderDto.getActualWeight().intValue();
        }

        return totalCapacityOfOrders;
    }

    public List<VehiclePlanDetails> validateAndSetTimesOfArrival(List<PlanOutline> planOutlineList) throws Exception {
        List<VehiclePlanDetails> vehiclePlanDetails = new ArrayList<VehiclePlanDetails>();
        for (PlanOutline planOutline : planOutlineList) {
            VehiclePlanDetails details = new VehiclePlanDetails();
            details.setActiveVehicleId(planOutline.getActiveVehicleId());
            details.setActiveVehicle(getActiveVehicleInfo(planOutline.getActiveVehicleId()));
            details.setJobOrders(getJobOrdersOrderedByTime(planOutline.getJobOrderIds()));
            if (!details.getJobOrders().isEmpty()) {
                processActiveVehiclePlan(details,
                        getActiveVehicleStartedLocation(details.getActiveVehicle().getHubId()));
            }
            vehiclePlanDetails.add(details);
        }
        return vehiclePlanDetails;
    }

    public void processActiveVehiclePlan(VehiclePlanDetails vehiclePlanDetails, Location initialStartingLocation)
            throws Exception {
        List<JobOrderDto> jobsOfTheSameTimeGroup = new ArrayList<>();
        String timeRange = vehiclePlanDetails.getJobOrders().get(0).getTimeFrom();

        Long workshiftStartTimeStamp = vehiclePlanDetails.getActiveVehicle().getWorkShiftFrom();

        LocalDateTime workShiftStartTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(workshiftStartTimeStamp),
                ZoneId.systemDefault());

        LocalDateTime today = LocalDateTime.now();
        Integer day = today.getDayOfMonth();
        String dayOfMonth = StringUtils.leftPad("" + day, 2, '0');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy H:m");

        workShiftStartTime = LocalDateTime.parse(dayOfMonth + "/" + today.getMonthValue() + "/" + today.getYear() + " "
                + workShiftStartTime.getHour() + ":" + workShiftStartTime.getMinute(), formatter);

        LocationDto initialStartingLocationDto = new LocationDto();
        initialStartingLocationDto.setLatitude(initialStartingLocation.getLatitude());
        initialStartingLocationDto.setLongtitude(initialStartingLocation.getLongitude());

        JobGroupPlanStartingArguments jobGroupPlanStartingArguments = new JobGroupPlanStartingArguments();
        jobGroupPlanStartingArguments.setStartingLocation(initialStartingLocationDto);
        jobGroupPlanStartingArguments.setStartingTime(workShiftStartTime);

        List<JobOrderDto> prioritizedJobsOfActiveVehicle = new ArrayList<>();

        AtomicInteger priority = new AtomicInteger(1);

        for (JobOrderDto jobOrder : vehiclePlanDetails.getJobOrders()) {
            if (jobOrder.getTimeFrom().equals(timeRange)) {
                jobsOfTheSameTimeGroup.add(jobOrder);
            } else {
                processActiveVehicleJobGroupAndReturnJobGroupPlanStartingArguments(
                        vehiclePlanDetails.getActiveVehicle(), jobsOfTheSameTimeGroup, prioritizedJobsOfActiveVehicle,
                        jobGroupPlanStartingArguments, priority);

                timeRange = jobOrder.getTimeFrom();
                jobsOfTheSameTimeGroup.clear();
                jobsOfTheSameTimeGroup.add(jobOrder);

            }

        }

        processActiveVehicleJobGroupAndReturnJobGroupPlanStartingArguments(vehiclePlanDetails.getActiveVehicle(),
                jobsOfTheSameTimeGroup, prioritizedJobsOfActiveVehicle, jobGroupPlanStartingArguments, priority);

        vehiclePlanDetails.setJobOrders(prioritizedJobsOfActiveVehicle);

    }

    public void processActiveVehicleJobGroupAndReturnJobGroupPlanStartingArguments(ActiveVehicle activeVehicle,
                                                                                   List<JobOrderDto> jobsOfTheSameTimeGroup, List<JobOrderDto> prioritizedJobsOfActiveVehicle,
                                                                                   JobGroupPlanStartingArguments jobGroupPlanStartingArguments, AtomicInteger priority) throws Exception {

        List<LocationDto> destinations = convertJobOrdersToLocations(jobsOfTheSameTimeGroup);

        LocalDateTime startingTime = jobGroupPlanStartingArguments.getStartingTime();

        List<Journey> journies = googleDistanceMatrixService.getJourniesFromSourceToDestinations(startingTime,
                jobGroupPlanStartingArguments.getStartingLocation(), destinations);

        if (!journies.isEmpty()) {
            StartingJourney startingJourney = predictStartingJourney(jobsOfTheSameTimeGroup, startingTime, journies);

            Integer index = jobsOfTheSameTimeGroup
                    .indexOf(new JobOrderDto(startingJourney.getJourney().getDestination().getId()));

            JobOrderDto startingJobOrder = jobsOfTheSameTimeGroup.get(index);
            startingJobOrder.setStartTimeFromOrigin(startingJourney.getStartingTime());
            startingJobOrder.setPriority(priority.get());
            startingJobOrder
                    .setTimeTakenRoutingEngineInText(startingJourney.getJourney().getTimeTakenRoutingEngineInText());
            startingJobOrder.setTimeTakenValueRoutingEngineInSeconds(
                    startingJourney.getJourney().getTimeTakenRoutingEngineInSeconds());

            LocalDateTime jobArrivalTime = startingJourney.getStartingTime()
                    .plusSeconds(startingJourney.getJourney().getTimeTakenRoutingEngineInSeconds());
            startingJobOrder.setArrivalTime(jobArrivalTime);
            LocalDateTime jobDepartureTime = jobArrivalTime.plusMinutes(getRequestProcessingTimeInMinutes());
            startingJobOrder.setDepartureTime(jobDepartureTime);

            prioritizedJobsOfActiveVehicle.add(startingJobOrder);

            jobsOfTheSameTimeGroup.remove(startingJobOrder);

            jobGroupPlanStartingArguments.clear();
            jobGroupPlanStartingArguments.setStartingLocation(startingJourney.getJourney().getDestination());
            jobGroupPlanStartingArguments.setStartingTime(jobDepartureTime);

            priority.set(priority.incrementAndGet());

            if (jobsOfTheSameTimeGroup.size() > 1) {
                processActiveVehicleJobGroupAndReturnJobGroupPlanStartingArguments(activeVehicle,
                        jobsOfTheSameTimeGroup, prioritizedJobsOfActiveVehicle, jobGroupPlanStartingArguments,
                        priority);
            } else if (jobsOfTheSameTimeGroup.size() == 1) {

                JobOrderDto lastJobOrder = jobsOfTheSameTimeGroup.get(0);
                lastJobOrder.setPriority(priority.get());

                LocationDto lastOrderLocation = new LocationDto();
                lastOrderLocation.setId(lastJobOrder.getId());
                lastOrderLocation.setLatitude(lastJobOrder.getJobLatitude());
                lastOrderLocation.setLongtitude(lastJobOrder.getJobLongitude());

                List<LocationDto> lastDestination = convertJobOrdersToLocations(jobsOfTheSameTimeGroup);

                List<Journey> lastJourneyList = googleDistanceMatrixService.getJourniesFromSourceToDestinations(
                        jobGroupPlanStartingArguments.getStartingTime(),
                        jobGroupPlanStartingArguments.getStartingLocation(), lastDestination);

                StartingJourney startingJourneyForLastOrder = predictStartingJourney(jobsOfTheSameTimeGroup,
                        jobGroupPlanStartingArguments.getStartingTime(), lastJourneyList);

                lastJobOrder.setStartTimeFromOrigin(startingJourneyForLastOrder.getStartingTime());

                priority.set(priority.incrementAndGet());

                lastJobOrder.setTimeTakenRoutingEngineInText(
                        startingJourneyForLastOrder.getJourney().getTimeTakenRoutingEngineInText());
                lastJobOrder.setTimeTakenValueRoutingEngineInSeconds(
                        startingJourneyForLastOrder.getJourney().getTimeTakenRoutingEngineInSeconds());

                LocalDateTime lastJobArrivalTime = startingJourneyForLastOrder.getStartingTime()
                        .plusSeconds(startingJourneyForLastOrder.getJourney().getTimeTakenRoutingEngineInSeconds());
                lastJobOrder.setArrivalTime(lastJobArrivalTime);
                LocalDateTime lastJobDepartureTime = lastJobArrivalTime
                        .plusMinutes(getRequestProcessingTimeInMinutes());
                lastJobOrder.setDepartureTime(lastJobDepartureTime);
                prioritizedJobsOfActiveVehicle.add(lastJobOrder);

                jobGroupPlanStartingArguments.clear();
                jobGroupPlanStartingArguments.setStartingLocation(lastOrderLocation);
                // TODO we do not need to get the processing time from the
                // database every time. Should be cached somewhere
                jobGroupPlanStartingArguments.setStartingTime(lastJobDepartureTime);

                // TODO refactor this code
                Integer gracePeriodInMinutes = getGracePeriodInMinutes();

                LocalDateTime today = LocalDateTime.now();
                Integer day = today.getDayOfMonth();
                String dayOfMonth = StringUtils.leftPad("" + day, 2, '0');
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy h:mm a");
                LocalDateTime closestJobOrderArrivalTimeFromWithTodaysDate = LocalDateTime.parse(dayOfMonth + "/"
                                + today.getMonthValue() + "/" + today.getYear() + " " + lastJobOrder.getTimeFrom().trim(),
                        formatter);
                LocalDateTime closestJobOrderArrivalTimeToWithTodaysDate = LocalDateTime.parse(dayOfMonth + "/"
                                + today.getMonthValue() + "/" + today.getYear() + " " + lastJobOrder.getTimeTo().trim(),
                        formatter);

                LocalDateTime minimumTimeOfArrival = closestJobOrderArrivalTimeFromWithTodaysDate
                        .minusMinutes(gracePeriodInMinutes);
                LocalDateTime maximumTimeOfArrival = closestJobOrderArrivalTimeToWithTodaysDate
                        .plusMinutes(gracePeriodInMinutes);

                if ((lastJobArrivalTime.isEqual(minimumTimeOfArrival)
                        || lastJobArrivalTime.isAfter(minimumTimeOfArrival))
                        && (lastJobArrivalTime.isEqual(maximumTimeOfArrival)
                        || lastJobArrivalTime.isBefore(maximumTimeOfArrival))) {
                    lastJobOrder.setJobStatus(JobStatus.SUCCESS);

                    if (lastJobArrivalTime.isBefore(closestJobOrderArrivalTimeFromWithTodaysDate)
                            || lastJobArrivalTime.isAfter(closestJobOrderArrivalTimeToWithTodaysDate)) {
                        lastJobOrder.setJobStatus(JobStatus.IN_GRACE_PERIOD);
                    }

                } else if (lastJobArrivalTime.isAfter(maximumTimeOfArrival)) {
                    lastJobOrder.setJobStatus(JobStatus.FAIL);
                }

                return;
            }

        }

    }

    public StartingJourney predictStartingJourney(List<JobOrderDto> jobsOfTheSameTimeGroup, LocalDateTime startingTime,
                                                  List<Journey> journies) {

        Journey closestJourney = Collections.min(journies, journeyTimeInSecondsComparator);

        LocalDateTime estimatedTimeOfArrivalToJob = startingTime
                .plusSeconds(closestJourney.getTimeTakenRoutingEngineInSeconds());
        Integer gracePeriodInMinutes = getGracePeriodInMinutes();
        Integer index = jobsOfTheSameTimeGroup.indexOf(new JobOrderDto(closestJourney.getDestination().getId()));
        JobOrderDto closestJobOrder = jobsOfTheSameTimeGroup.get(index);
        LocalDateTime today = LocalDateTime.now();
        Integer day = today.getDayOfMonth();
        String dayOfMonth = StringUtils.leftPad("" + day, 2, '0');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
        LocalDateTime closestJobOrderArrivalTimeFromWithTodaysDate = LocalDateTime.parse(dayOfMonth + "/"
                        + today.getMonthValue() + "/" + today.getYear() + " " + closestJobOrder.getTimeFrom().trim(),
                formatter);
        LocalDateTime closestJobOrderArrivalTimeToWithTodaysDate = LocalDateTime.parse(today.getDayOfMonth() + "/"
                + today.getMonthValue() + "/" + today.getYear() + " " + closestJobOrder.getTimeTo().trim(), formatter);

        LocalDateTime minimumTimeOfArrival = closestJobOrderArrivalTimeFromWithTodaysDate
                .minusMinutes(gracePeriodInMinutes);
        LocalDateTime maximumTimeOfArrival = closestJobOrderArrivalTimeToWithTodaysDate
                .plusMinutes(gracePeriodInMinutes);

        StartingJourney startingJourney = new StartingJourney();

        if ((estimatedTimeOfArrivalToJob.isEqual(minimumTimeOfArrival)
                || estimatedTimeOfArrivalToJob.isAfter(minimumTimeOfArrival))
                && (estimatedTimeOfArrivalToJob.isEqual(maximumTimeOfArrival)
                || estimatedTimeOfArrivalToJob.isBefore(maximumTimeOfArrival))) {
            startingJourney.setJourney(closestJourney);
            startingJourney.setStartingTime(startingTime);
            closestJobOrder.setJobStatus(JobStatus.SUCCESS);

            if (estimatedTimeOfArrivalToJob.isBefore(closestJobOrderArrivalTimeFromWithTodaysDate)
                    || estimatedTimeOfArrivalToJob.isAfter(closestJobOrderArrivalTimeToWithTodaysDate)) {
                closestJobOrder.setJobStatus(JobStatus.IN_GRACE_PERIOD);
            }

            return startingJourney;
        } else if (estimatedTimeOfArrivalToJob.isBefore(minimumTimeOfArrival)) {
            LocalDateTime newStartingTime = startingTime.plusMinutes(getStartTimeIncrementInMinutes());
            return predictStartingJourney(jobsOfTheSameTimeGroup, newStartingTime, journies);
        } else if (estimatedTimeOfArrivalToJob.isAfter(maximumTimeOfArrival)) {
            startingJourney.setJourney(closestJourney);
            startingJourney.setStartingTime(startingTime);
            closestJobOrder.setJobStatus(JobStatus.FAIL);
            return startingJourney;
        }

        return null;

    }

    public List<LocationDto> convertJobOrdersToLocations(List<JobOrderDto> jobsOfTheSameTimeGroup) {
        List<LocationDto> destinations = new ArrayList<>();

        for (JobOrderDto jobOrderDto : jobsOfTheSameTimeGroup) {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(jobOrderDto.getId());
            locationDto.setLatitude(jobOrderDto.getJobLatitude());
            locationDto.setLongtitude(jobOrderDto.getJobLongitude());

            destinations.add(locationDto);
        }
        return destinations;
    }

    public ActiveVehicle getActiveVehicleInfo(Long activeVehilceId) {
        ActiveVehicle data = new ActiveVehicle();
        data = activeVehicleRepo.getActiveVehicleInfo(activeVehilceId);
        return data;
    }

    public Location getActiveVehicleStartedLocation(Long hubId) {
        Location location = new Location();
        location = hubLocationRepo.getHubLocation(hubId);
        return location;
    }

    public Integer getRequestProcessingTimeInMinutes() {

        SystemConfig requestProcessingTimeInMinutes = systemConfigRepo.getRequestProcessingTime();
        Integer value = requestProcessingTimeInMinutes.getValue().intValue();
        return value;
    }

    public Integer getGracePeriodInMinutes() {

        // RequestProcessingTime info = new RequestProcessingTime();
        SystemConfig gracePeriodInMinutes = systemConfigRepo.getGracePeriodInMinutes();
        Integer value = gracePeriodInMinutes.getValue().intValue();
        return value;

    }

    public Integer getStartTimeIncrementInMinutes() {
        // RequestProcessingTime info = new RequestProcessingTime();
        SystemConfig startTimeIncrementInMinutes = systemConfigRepo.getStartTimeIncrementInMinutes();
        Integer value = startTimeIncrementInMinutes.getValue().intValue();
        return value;

    }

    public Integer getEmergencyVehicleCapacity() {

        SystemConfig emeregencyCapacity = systemConfigRepo.getVehiclesEmergencyCapacity();
        Integer percentageValue = emeregencyCapacity.getValue().intValue();
        return percentageValue;
    }

    public Integer getVehicleAvailableCapacity(ActiveVehicle activeVehicle, Integer emeregencyPercentageValue) {

        Integer availableCapacity = activeVehicle.getCapacity()
                - (activeVehicle.getCapacity() * emeregencyPercentageValue / 100);

        return availableCapacity;
    }

    public List<JobOrderDto> getJobOrdersOrderedByTime(List<Long> jobOrdersId) {
        List<JobOrder> jobOrders = mapper.getJobsOfAllTypes(jobOrdersId);
        List<JobOrderDto> jobOrdersDtos = jobOrders.stream().map(mapper::getJobOrdersOrderedByTimeDto)
                .collect(Collectors.toList());
        return jobOrdersDtos;
    }

    public List<JobOrderTodayDto> getJobOrderForToday(Long hubId) {
        Date today = new Date();
        List<JobOrder> jobOrders = mapper.getJobsOfAllFroToday(today, hubId);
        List<JobOrderTodayDto> jobOrderTodayDtos = jobOrders.stream().map(mapper::getJobOrderTodayDto)
                .collect(Collectors.toList());
        return jobOrderTodayDtos;

    }

    public List<JobOrderTodayDto> getDeliveryJobOrderForToday() {
        Date today = new Date();
        List<JobOrder> jobOrders = jobDeliveryOrderRepo.jobDeliveryOrderAllIForToday(today);
        List<JobOrderTodayDto> jobOrderTodayDtos = jobOrders.stream().map(mapper::getJobOrderTodayDto)
                .collect(Collectors.toList());

        return jobOrderTodayDtos;

    }

    public Date convertFromStringToDate(Date date, String time) {
        Date timeFrom = DateUtility.get24HourTimeFromString(time);
        Date finalDate = DateUtility.getDateTimeFormat(date, timeFrom);
        return finalDate;
    }

//    public boolean checkOrdersToActiveVehicle() throws Exception {
//        return automaticDistributionServiceImp.isVehicleServeRequestTypes();
//
//    }

}
