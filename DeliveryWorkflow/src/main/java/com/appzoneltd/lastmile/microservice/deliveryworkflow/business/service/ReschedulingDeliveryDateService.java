package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.repository.ActiveVehicleCouchbaseRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.ActiveVehicleRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.BuildingDao;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.CalendarJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.HubCalendarDao;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PlanOrdersRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.HubCalendarEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryStatus;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.utility.DateUtil;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.DeliveryPushNotificationService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.model.SMSModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.service.SMSService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ReschedulingDeliveryDateService {

	@Autowired
	private DeliveryPushNotificationService notificationService;
	@Autowired
	private CalendarJpaRepository calendarJpaRepository;

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PlanOrdersRepository planOrdersRepository;

	@Autowired
	private ActiveVehicleCouchbaseRepository activeVehicleCouchbaseRepository;

	@Autowired
	private ActiveVehicleRepository activeVehicleRepository;

	@Autowired
	private RequestHistoryService requestHistoryService;

	@Autowired
	private SMSService smsService;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private HubCalendarDao hubCalendarDao;

	public List<Message> rescheduleDelivery(Long packageId, Date rescheduleDeliveryDate, String timeFrom, String timeTo)
			throws JsonProcessingException {
		List<Message> messages = new ArrayList<Message>();

		Long requestId = requestHistoryService.getDeliveryIdForPackage(packageId);
		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
		Long driverId = getActiveVehicleForDeliveryOrderInPlan(requestId);

		if (!rescheduleDeliveryDate.after(deliveryRequestEntity.getDeliverydate())) {

			messages.add(new Message(MessageType.ERROR, "rescheduleDeliveryDate", messageSource.getMessage(
					"rescheduleDeliveryDate.update.rescheduleDelivery", null, LocaleContextHolder.getLocale())));
		}

		if (!checkDateAvalibilty(rescheduleDeliveryDate, deliveryRequestEntity.getHubId())) {
			messages.add(new Message(MessageType.ERROR, "rescheduleDeliveryDateAvalibale",
					messageSource.getMessage("rescheduleDeliveryDateAvalibale.update.rescheduleDelivery", null,
							LocaleContextHolder.getLocale())));
		}

		if (messages.isEmpty()) {
			// if package is outing for delivery and already in plan
			if (driverId != null) {
				NotificationParameter notificationParameter = new NotificationParameter();
				notificationParameter.setDriverId(driverId);
				notificationParameter.setPackageId(packageId);
				notificationParameter.setRequestId(requestId);
				sendReScheduleDeliveryNotificationForDriver(notificationParameter);
			}
			Long recepeintId = null;

			changeStatusForDeliveryOrder(packageId);
			activeVehicleCouchbaseRepository.changeRequestStatus(driverId, requestId,
					DeliveryStatus.RESCHEDULED_FOR_DELIVERY.name());
			deliveryRequestEntity = deliveryRequestService.savingRescheduleDeliveryDate(packageId,
					rescheduleDeliveryDate, timeFrom, timeTo);

			if (deliveryRequestEntity != null) {
				if (deliveryRequestEntity.getRecipientId() != null) {
					recepeintId = deliveryRequestEntity.getRecipientId();
					System.out.println(
							">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>recepeintId " + recepeintId);
					sendNotificationToCustomerForReschedulingDeliveryDate(packageId, rescheduleDeliveryDate,
							recepeintId);
				} else {
					sendSmsToCustomerForReschedulingDeliveryDate(packageId, rescheduleDeliveryDate);
				}

			}

		}

		return messages;
	}

	public boolean checkDateAvalibilty(Date rescheduleDelivery, Long hubId) {
		boolean isAvalibable = false;
		String dayName = DateUtil.getWeekDay(rescheduleDelivery);
		if (hubId != null) {
			BuildingEntity buildingEntity = buildingDao.findOne(hubId);
			if (buildingEntity != null) {
				HubCalendarEntity hubCalendarEntity = hubCalendarDao.findHubCalendarwithCalendarDayName(hubId, dayName);
				if (hubCalendarEntity != null) {
					if (hubCalendarEntity.getStatus().equalsIgnoreCase("working")) {
						isAvalibable = true;
					}
				}
			}
		}
		return isAvalibable;
	}

	public Long getActiveVehicleForDeliveryOrderInPlan(Long orderId) {
		Long DriverId = null;
		
		PlanOrderEntity planOrderEntity = planOrdersRepository.findByDeliveryId(orderId);
		if (planOrderEntity != null) {
			ActiveVehicleEntity activeVehicleEntity = planOrderEntity.getActiveVehicle();
			if(activeVehicleEntity !=null && activeVehicleEntity.getDriver() !=null){
				DriverId = activeVehicleEntity.getDriver().getId();
			}
		}
		return DriverId;
	}

	public Long getDriverInfoForActiveVehicle(Long activeVehicleId) {
		return activeVehicleRepository.findById(activeVehicleId).getDriver().getId();

	}

	public void changeStatusForDeliveryOrder(Long packageId) {

		String status = DeliveryStatus.RESCHEDULED_FOR_DELIVERY.name();
		Long requestId = requestHistoryService.getDeliveryIdForPackage(packageId);

		PackageEntity packageEntity = packageRepository.findOne(packageId);
		if (packageEntity != null) {
			packageEntity.setStatus(status);
			packageRepository.save(packageEntity);
		}

		PlanOrderEntity planOrderEntity = planOrdersRepository.findByDeliveryId(requestId);
		if (planOrderEntity != null) {
			planOrderEntity.setExcluded(true);
			planOrdersRepository.save(planOrderEntity);

		}

		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
		if (deliveryRequestEntity != null) {
			deliveryRequestEntity.setRequestStatus(status);
			deliveryRequestRepository.save(deliveryRequestEntity);
		}

		requestHistoryService.updateRequestHistoryStatus(packageId);

	}

	public void sendNotificationToCustomerForReschedulingDeliveryDate(Long packageId, Date rescheduleDeliveryDate,
			Long recepientId) throws JsonProcessingException {
		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);
		NotificationParameter notificationParameter = new NotificationParameter();
		notificationParameter.setPackageId(packageId);
		notificationParameter.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
		notificationParameter.setCustomerId(recepientId);
		notificationParameter.setDeliveryDate(rescheduleDeliveryDate);
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendNotificationToCustomerForReschedulingDeliveryDate");
		System.out.println("notificationParameter " + notificationParameter);
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendNotificationToCustomerForReschedulingDeliveryDate");
		notificationService.sendDeliveryDateNotification(notificationParameter);

	}

	public void sendReScheduleDeliveryNotificationForDriver(NotificationParameter notificationParameter)
			throws JsonProcessingException {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> sendReScheduleDeliveryNotificationForDriver step ");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> driver Id " + notificationParameter.getDriverId());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> packageId " + notificationParameter.getPackageId());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> requestId " + notificationParameter.getRequestId());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> sendReScheduleDeliveryNotificationForDriver step ");

		notificationService.sendReScheduleDeliveryNotificationForDriver(notificationParameter);
	}

	public void sendSmsToCustomerForReschedulingDeliveryDate(Long packageId, Date rescheduleDeliveryDate) {

		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);
		String mobileNumber = "";

		if (deliveryRequestEntity.getCountryCode() != null) {
			mobileNumber = deliveryRequestEntity.getCountryCode() + deliveryRequestEntity.getRecipientmobile();
		} else {
			mobileNumber = deliveryRequestEntity.getRecipientmobile();
		}

		Date newDeliveryDate = rescheduleDeliveryDate;

		String smsMessageTemplate = "Dear Customer, your package It rescheduled to be delivered on  "
				+ DateFormat.getDateInstance(DateFormat.MEDIUM).format(newDeliveryDate);

		SMSModel smsModel = new SMSModel();
		smsModel.setPhoneNumber(mobileNumber);
		smsModel.setMessage(smsMessageTemplate);

		System.out.println("SEND SMS WITH MODEL " + smsModel.toString());

		// SEND SMS
		smsService.sendSMS(smsModel);

	}

}
