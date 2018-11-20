package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.CalendarJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PickupTimeRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dto.DeliveryScheduledDTO;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.CalendarEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PickupTimeEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryRequestModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliverySchedulerHolder;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.ShipmentServiceType;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.utility.DateUtil;

@Service
public class DeliverySchedulingService {

	@Autowired
	private CalendarJpaRepository calendarJpaRepository;

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;

	@Autowired
	private PickupRequestJpaRepository pickupRequestJpaRepository;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private RequestHistoryService requestHistoryService;

	@Autowired
	private PickupTimeRepository pickupTimeRepository;

	public boolean scheduleDeliveryRequest(Long packageId) {
		boolean finished = false;
		Long deliveryRequestId = requestHistoryService.getDeliveryIdForPackage(packageId);
		DeliveryScheduledDTO deliveryScheduledDTO = getScheduledDeliveryDate(packageId, deliveryRequestId);
		deliveryScheduledDTO.setDeliveryId(deliveryRequestId);

		if (deliveryScheduledDTO != null) {
			saveScheduledDateForDelivery(deliveryScheduledDTO);
			finished = true;
		}
		return finished;
	}

	private DeliveryScheduledDTO getScheduledDeliveryDate(Long packageId, Long deliveryRequestId) {
		// Getting PackageEntity
		PackageEntity packageEntity = packageRepository.findOne(packageId);
		DeliveryRequestEntity deliveryRequestEntity = null;
		DeliveryScheduledDTO deliveryScheduledDTO = null;

		if (deliveryRequestId != null) {
			deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryRequestId);
			DeliveryRequestModel deliveryRequestModel = new DeliveryRequestModel();
			deliveryRequestModel.setPackageId(packageId);
			deliveryRequestModel.setShipmentServiceType(packageEntity.getShipmentServiceType().getType());
			deliveryRequestModel.setPickupDate(deliveryRequestEntity.getPickupdate());
			deliveryScheduledDTO = ProcessSchedulingForRequest(deliveryRequestModel);
		}
		return deliveryScheduledDTO;
	}

	private boolean saveScheduledDateForDelivery(DeliveryScheduledDTO deliveryScheduledDTO) {
		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository
				.findOne(deliveryScheduledDTO.getDeliveryId());
		deliveryRequestEntity.setDeliverydate(deliveryScheduledDTO.getDeliveryDate());
		deliveryRequestEntity.setTimeFrom(deliveryScheduledDTO.getTimeFrom());
		deliveryRequestEntity.setTimeTo(deliveryScheduledDTO.getTimeTo());

		DeliveryRequestEntity deliveryRequestEntity2 = deliveryRequestRepository.save(deliveryRequestEntity);
		if (deliveryRequestEntity2 != null) {
			return true;
		}
		return false;
	}

	private DeliveryScheduledDTO ProcessSchedulingForRequest(DeliveryRequestModel deliveryRequestModel) {

		String shipmentServiceType = deliveryRequestModel.getShipmentServiceType().trim();
		Date schedulingDate = null;

		DeliveryScheduledDTO deliveryScheduledDTO = new DeliveryScheduledDTO();

		if (ShipmentServiceType.NEXT_DAY.getName().equalsIgnoreCase(shipmentServiceType)) {
			schedulingDate = scheduleNextDayForDelivery(deliveryRequestModel.getPickupDate());
		} else if (ShipmentServiceType.TWO_THREE_DAYS.getName().equalsIgnoreCase(shipmentServiceType)) {
			schedulingDate = scheduleTwoThreeDaysForDelivery(deliveryRequestModel.getPickupDate());
		} else if (ShipmentServiceType.SEVEN_DAYS.getName().equalsIgnoreCase(shipmentServiceType)) {
			schedulingDate = scheduleWithInWeekForDelivery(deliveryRequestModel.getPickupDate());
		}
		deliveryScheduledDTO.setDeliveryDate(schedulingDate);
		deliveryScheduledDTO=getBestTimeIntervalForDay(deliveryScheduledDTO);
		return deliveryScheduledDTO;
	}

	private DeliveryScheduledDTO getBestTimeIntervalForDay(DeliveryScheduledDTO deliveryScheduledDTO) {
		List<PickupTimeEntity> pickupTimeEntities = (List<PickupTimeEntity>) pickupTimeRepository.findAll();
		if (pickupTimeEntities.size() > 0) {
			int smallestIndex = 0;
			int smallestValue = getOrdersCountInTimeInterval(deliveryScheduledDTO.getDeliveryDate(), pickupTimeEntities.get(0).getTimeFrom(),
					pickupTimeEntities.get(0).getTimeTo());

			for (int index = 1; index < pickupTimeEntities.size(); index++) {
				int numberOfOrdersMatchTimeInterval = getOrdersCountInTimeInterval(deliveryScheduledDTO.getDeliveryDate(),
						pickupTimeEntities.get(index).getTimeFrom(), pickupTimeEntities.get(index).getTimeTo());
				if (numberOfOrdersMatchTimeInterval < smallestValue) {
					smallestValue = numberOfOrdersMatchTimeInterval;
					smallestIndex = index;
				}
			} // END FOR
			deliveryScheduledDTO.setTimeFrom(pickupTimeEntities.get(smallestIndex).getTimeFrom());
			deliveryScheduledDTO.setTimeTo(pickupTimeEntities.get(smallestIndex).getTimeTo());
		}

		return deliveryScheduledDTO;
	}

	private int getOrdersCountInTimeInterval(Date schedulingDate, String timeFrom, String timeTo) {
		return (getDayPickupMatchTimeInterval(schedulingDate, timeFrom, timeTo)
				+ getDayDeliveryMatchTimeInterval(schedulingDate, timeFrom, timeTo));
	}

	private Date scheduleNextDayForDelivery(Date pickupDate) {

		List<CalendarEntity> calendarEntities = (List<CalendarEntity>) calendarJpaRepository.findAllOrderByIdAsc();

		Date nextDate = findNextWorkingDay(calendarEntities, pickupDate, 1);

		return nextDate;
	}

	private Date scheduleTwoThreeDaysForDelivery(Date pickupDate) {

		List<CalendarEntity> calendarEntities = (List<CalendarEntity>) calendarJpaRepository.findAllOrderByIdAsc();

		List<DeliverySchedulerHolder> schedulerHolders = new ArrayList<>();
		DeliverySchedulerHolder schedulerHolder2Days = new DeliverySchedulerHolder();
		DeliverySchedulerHolder schedulerHolder3Days = new DeliverySchedulerHolder();

		// Decide Dates
		Date next2Day = findNextWorkingDay(calendarEntities, pickupDate, 2);
		Date next3Day = findNextWorkingDay(calendarEntities, pickupDate, 3);
		schedulerHolder2Days.setDate(next2Day);
		schedulerHolder3Days.setDate(next3Day);

		String next2DayString = DateUtil.getWeekDay(next2Day);
		String next3DayString = DateUtil.getWeekDay(next3Day);

		if (next2DayString.equalsIgnoreCase(next3DayString)) {
			return next2Day;
		} else {
			Integer totalCountForNext2Day = getDayPickupAndDeliveryCount(next2Day);
			schedulerHolder2Days.setFrequency(totalCountForNext2Day);
			Integer totalCountForNext3Day = getDayPickupAndDeliveryCount(next3Day);
			schedulerHolder3Days.setFrequency(totalCountForNext3Day);
			// Add To List
			schedulerHolders.add(schedulerHolder2Days);
			schedulerHolders.add(schedulerHolder3Days);

			DeliverySchedulerHolder schedulerHolderDay = decideTheAppropriateDate(schedulerHolders);
			// return Decided Date
			return schedulerHolderDay.getDate();
		}
	}

	private Date scheduleWithInWeekForDelivery(Date pickupDate) {

		List<CalendarEntity> calendarEntities = (List<CalendarEntity>) calendarJpaRepository.findAllOrderByIdAsc();

		List<DeliverySchedulerHolder> schedulerHolders = new ArrayList<>();
		DeliverySchedulerHolder schedulerHolder4Days = new DeliverySchedulerHolder();
		DeliverySchedulerHolder schedulerHolder5Days = new DeliverySchedulerHolder();
		DeliverySchedulerHolder schedulerHolder6Days = new DeliverySchedulerHolder();
		DeliverySchedulerHolder schedulerHolder7Days = new DeliverySchedulerHolder();

		Date next4Day = findNextWorkingDay(calendarEntities, pickupDate, 4);
		Date next5Day = findNextWorkingDay(calendarEntities, pickupDate, 5);
		Date next6Day = findNextWorkingDay(calendarEntities, pickupDate, 6);
		Date next7Day = findNextWorkingDay(calendarEntities, pickupDate, 7);

		schedulerHolder4Days.setDate(next4Day);
		schedulerHolder5Days.setDate(next5Day);
		schedulerHolder6Days.setDate(next6Day);
		schedulerHolder7Days.setDate(next7Day);

		Integer totalCountForNext4Day = getDayPickupAndDeliveryCount(next4Day);
		Integer totalCountForNext5Day = getDayPickupAndDeliveryCount(next5Day);
		Integer totalCountForNext6Day = getDayPickupAndDeliveryCount(next6Day);
		Integer totalCountForNext7Day = getDayPickupAndDeliveryCount(next7Day);

		schedulerHolder4Days.setFrequency(totalCountForNext4Day);
		schedulerHolder5Days.setFrequency(totalCountForNext5Day);
		schedulerHolder6Days.setFrequency(totalCountForNext6Day);
		schedulerHolder7Days.setFrequency(totalCountForNext7Day);

		schedulerHolders.add(schedulerHolder4Days);
		schedulerHolders.add(schedulerHolder5Days);
		schedulerHolders.add(schedulerHolder6Days);
		schedulerHolders.add(schedulerHolder7Days);

		DeliverySchedulerHolder schedulerHolderDay = decideTheAppropriateDate(schedulerHolders);
		// return Decided Date
		return schedulerHolderDay.getDate();
	}

	private Date findNextWorkingDay(List<CalendarEntity> calendarEntities, Date pickupDate, Integer numberOfDaysPlan) {

		Date scheduledDateForDelivery = null;
		Date nextWorkingDate = getNextWorkingDateFromPickUpDate(pickupDate);
		if (numberOfDaysPlan > 1) {
			int remainDaysForPlan = numberOfDaysPlan - 1;
			for (int i = 0; i < remainDaysForPlan; i++) {
				nextWorkingDate = getNextWorkingDateFromPickUpDate(nextWorkingDate);
			}
			scheduledDateForDelivery = nextWorkingDate;
		} else {
			scheduledDateForDelivery = nextWorkingDate;
		}
		return scheduledDateForDelivery;
	}

	private Integer getDayPickupAndDeliveryCount(Date currentDay) {
		Integer numberOfPickup = getDayPickupCount(currentDay);
		Integer numberOfDelivery = getDayDeliveryCount(currentDay);
		Integer TotalNumberOfPickupAndDelivery = numberOfPickup + numberOfDelivery;

		return TotalNumberOfPickupAndDelivery;
	}

	private Integer getDayPickupCount(Date currentDay) {
		return pickupRequestJpaRepository.getAllScheduledForPickup(currentDay).size();
	}

	private Integer getDayDeliveryCount(Date currentDay) {
		return deliveryRequestRepository.getAllScheduledForDelivery(currentDay).size();
	}

	private Integer getDayPickupMatchTimeInterval(Date currentDay, String timeFrom, String timeTo) {
		return pickupRequestJpaRepository.getAllScheduledForPickupWithTimes(currentDay, timeFrom, timeTo).size();
	}

	private Integer getDayDeliveryMatchTimeInterval(Date currentDay, String timeFrom, String timeTo) {
		return deliveryRequestRepository.getAllScheduledForDeliveryWithTimes(currentDay, timeFrom, timeTo).size();
	}

	public Date getNextWorkingDateFromPickUpDate(Date pickupDate) {
		List<CalendarEntity> calendarEntities = (List<CalendarEntity>) calendarJpaRepository.findAllOrderByIdAsc();
		int dayIndex = getCurrentWeekDayIndex(pickupDate, calendarEntities);
		int NumberOfNotWorkingDaysCounter = 0;
		/// DECIDE FROM WHERE WE BEGIN
		if (dayIndex == 6) {
			dayIndex = 0;
		} else {
			dayIndex += 1;
		}

		for (int index = dayIndex; index < calendarEntities.size(); index++) {
			CalendarEntity calendarEntity = calendarEntities.get(index);
			if (!calendarEntity.getStatus().equalsIgnoreCase("working")) {
				NumberOfNotWorkingDaysCounter++;
			} else {
				break;
			}
			if (index == calendarEntities.size() - 1) {
				index = -1;
			}
		}
		Date firstWorkingDayFromPickup = DateUtil.getDateAfterCountDates(pickupDate, NumberOfNotWorkingDaysCounter + 1);
		return firstWorkingDayFromPickup;
	}

	private int getCurrentWeekDayIndex(Date startDate, List<CalendarEntity> calendarEntities) {

		int dayIndex = 0;
		String currentWeekDay = DateUtil.getWeekDay(startDate);
		for (int index = 0; index < calendarEntities.size(); index++) {
			CalendarEntity calendarEntity = calendarEntities.get(index);
			if (calendarEntity.getDayname().equalsIgnoreCase(currentWeekDay)) {
				dayIndex = index;
			}
		}
		return dayIndex;
	}

	private DeliverySchedulerHolder decideTheAppropriateDate(List<DeliverySchedulerHolder> schedulerHolders) {
		int smallestIndex = 0;
		Integer smallest = schedulerHolders.get(smallestIndex).getFrequency();
		for (int index = 1; index < schedulerHolders.size(); index++) {
			int smallesDeliverySchedule = schedulerHolders.get(index).getFrequency();
			if (smallesDeliverySchedule < smallest) {
				smallest = smallesDeliverySchedule;
				smallestIndex = index;
			}
		}
		return schedulerHolders.get(smallestIndex);
	}

}