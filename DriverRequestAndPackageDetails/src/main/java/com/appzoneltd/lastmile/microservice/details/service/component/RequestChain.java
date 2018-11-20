package com.appzoneltd.lastmile.microservice.details.service.component;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.VerifiedPackageEntity;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by alaa.nabil on 3/26/2017.
 */
public interface RequestChain {

	static final String PACKAGE_VERIFICATION_REJECTED = "PACKAGE_VERIFICATION_REJECTED";
	static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";
	static final String INVOICE_REJECTED = "INVOICE_REJECTED";
	static final String SUBMIT_DOCUMENT_REJECTED = "SUBMIT_DOCUMENT_REJECTED";

	ObjectMapper MAPPER = new ObjectMapper();

	static NotificationModel buildPushNotificationModelWithSerializationToSendToCustomer(Long packageId, Long customerId,
			Long requestId, String requestType,
			String latitude, String longitude,
			ActiveVehicleEntity activeVehicleEntity) {

		NotificationModel model = new NotificationModel();

		model.setPackageId(packageId);
		model.setRecipientType("CUSTOMER");

		Long[] userIds = {customerId};
		model.setUserIds(userIds);
		Map<String, Object> notificationData = new HashMap<>();

		notificationData.put("notification_title", "LastMile");
		notificationData.put("notification_body", "Driver on his way");
		notificationData.put("notification_item_title", "Driver on his way");
		notificationData.put("notification_item_body", "Driver on his way, please wait him");
		notificationData.put("type", "onHisWay");
		notificationData.put("time", new Date().getTime());

		PushNotificationPayload payload = new PushNotificationPayload(activeVehicleEntity.getVehicle().getVehicleId(),
				activeVehicleEntity.getVehicle().getBuilding().getBuildingId(), activeVehicleEntity.getDriver().getId(), latitude,
				longitude, activeVehicleEntity.getDriver().getUsers().getUsername(), activeVehicleEntity.getDevices().getPhonenumber(),
				activeVehicleEntity.getDriver().getRating() != null ? activeVehicleEntity.getDriver().getRating().longValue() : 0L,
						activeVehicleEntity.getDriver().getUsers().getPersonalPhotoId(),
						activeVehicleEntity.getVehicle().getModel(), activeVehicleEntity.getVehicle().getPlate(),
						requestId, requestType);

		notificationData.put("payload", payload);
		model.setData(notificationData);
		return model;
	}

	static String getDriverArrivalAcknowledgmentTopic(String requestType) {
		if ("PICKUP".equalsIgnoreCase(requestType))
			return "DRIVER_ARRIVAL_ACKNOLEDGMENT";
		if ("DELIVERY".equalsIgnoreCase(requestType))
			return "DELIVERY_CUSTOMER_FOUND";
		if ("RETURN".equalsIgnoreCase(requestType))
			return "RETURN_CUSTOMER_FOUND";
		if ("TRANSIT".equalsIgnoreCase(requestType))
			return "TRANSIT_DRIVER_ARRIVAL_ACKNOLEDGMENT";

		return null;
	}

	static Invoice buildInvoiceModel(PackageEntity packageEntity, VerifiedPackageEntity verifiedPackageEntity
			, String pickupRequestType, String pickupFormattedAddress, String recipientFormattedAddress, String paymentMethod) {
		final int priceOne = 1000;
		final int priceTwo = 2000;
		DecimalFormat f = new DecimalFormat("000.00");
		int total = priceOne + priceTwo;
		Date date = new Date();

		List<InvoiceService> pickupServices = new ArrayList<>();

		InvoiceService p = new InvoiceService();
		p.setType(pickupRequestType);
		p.setLocation(pickupFormattedAddress);
		p.setQuantity(" 1 " + verifiedPackageEntity.getPackageType().getPackagetype() + " ("
				+ verifiedPackageEntity.getActualweight().toString() + ") ");
		p.setPrice(String.valueOf(priceOne));
		pickupServices.add(p);

		p = new InvoiceService();
		p.setType(packageEntity.getShipmentServiceType().getShipmentService().getService() + " - "
				+ packageEntity.getShipmentServiceType().getType());
		p.setLocation(recipientFormattedAddress);
		p.setQuantity(" 1 " + verifiedPackageEntity.getPackageType().getPackagetype() + " ("
				+ verifiedPackageEntity.getActualweight().toString() + ") ");
		p.setPrice(String.valueOf(priceTwo));
		pickupServices.add(p);

		Invoice invoice = new Invoice();
		invoice.setCode(String.valueOf((int) (99999 * 10000000000L)));
		invoice.setDate(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
		invoice.setPaymentMethod(paymentMethod);
		invoice.setServices(pickupServices);
		invoice.setTaxes(f.format(total * 0.1));
		invoice.setTime(DateFormat.getTimeInstance(DateFormat.SHORT).format(date));
		invoice.setTotalWithoutTaxes(f.format(total * 0.9));
		invoice.setTotalWithTaxes(String.valueOf(total));
		return invoice;
	}

	static String buildNotificationOfInvoice(Long packageId, Long userId, Invoice invoice)
			throws JsonProcessingException {
		NotificationModel model = new NotificationModel();

		model.setPackageId(packageId);
		model.setRecipientType("CUSTOMER");

		Long[] userIds = {userId};
		model.setUserIds(userIds);
		Map<String, Object> notificationData = new HashMap<>();

		notificationData.put("notification_title", "LastMile");
		notificationData.put("notification_body", "Pickup request invoice");
		notificationData.put("notification_item_title", "Pickup request invoice");
		notificationData.put("notification_item_body", "Pickup request invoice");
		notificationData.put("type", "INVOICE");
		notificationData.put("time", new Date().getTime());
		notificationData.put("payload", invoice);

		model.setData(notificationData);
		return MAPPER.writeValueAsString(model);
	}


	LatLng getGoogleLocationLatLng(PlanOrderEntity planOrderEntity);

	String getFormattedAddress(PlanOrderEntity planOrderEntity);

	Location getLocationDto(PlanOrderEntity planOrderEntity);

	BigDecimal getPackageWeight(PlanOrderEntity planOrderEntity);

	String getPackageType(PlanOrderEntity planOrderEntity);

	JobOrder fillCouchbaseJobOrderFromPlanOrder(PlanOrderEntity planOrderEntity);

	Object getNotificationModelWithRequestDetails(Long packageId, Long requestId, String requestType, ActiveVehicleEntity activeVehicleEntity) throws JsonProcessingException;

	PackageDetails toPackageDetails(Long requestId, String requestType, PackageEntity packageEntity, Long driverId) throws JsonProcessingException;

	com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(PlanOrderEntity planOrderEntity);

	boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId) throws EntityNotFoundException, JsonProcessingException;

	Invoice generateInvoice(Long requestId, String requestType) throws EntityNotFoundException;

	void confirmInvoice(Long requestId, String requestType, Long driverId) throws EntityNotFoundException, JsonProcessingException;

	Boolean submitAndAddDocuments(Documents documents, Long activeVehicleId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError;

	RequestDetails fillOrderDetails(Long requestId, String requestType) throws EntityNotFoundException;

	void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException;

	boolean isCompletedOrder(Long orderId, String orderType, TodaySummary todaySummary);
}
