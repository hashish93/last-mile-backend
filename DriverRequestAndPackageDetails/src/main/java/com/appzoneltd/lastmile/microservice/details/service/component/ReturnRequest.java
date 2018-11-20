package com.appzoneltd.lastmile.microservice.details.service.component;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dao.repos.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.UsersJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 3/26/2017.
 */
@Component(value = "returnRequest")
public class ReturnRequest implements RequestChain {

	private final ReturnRequestRepository returnRequestRepository;
	private final RequestHistoryJpaRepository requestHistoryRepository;
	private final UsersJpaRepository usersJpaRepository;
	private final KafkaTemplate<Long, String> kafkaTemplate;
	private final RequestChain next;

	private final ActiveVehicleJpaRepository activeVehicleRepository;

	@Autowired
	public ReturnRequest(ReturnRequestRepository returnRequestRepository, RequestHistoryJpaRepository requestHistoryRepository, UsersJpaRepository usersJpaRepository, KafkaTemplate<Long, String> kafkaTemplate,
			ActiveVehicleJpaRepository activeVehicleRepository, @Qualifier(value = "transitRequest") RequestChain next) {
		this.returnRequestRepository = returnRequestRepository;
		this.requestHistoryRepository = requestHistoryRepository;
		this.usersJpaRepository = usersJpaRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.next = next;

		this.activeVehicleRepository = activeVehicleRepository;
	}


	@Override
	public LatLng getGoogleLocationLatLng(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;

		ReturnRequestEntity returnRequestEntity = null;
		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			returnRequestEntity = returnRequestRepository.findOne(planOrderEntity.getOrderId());
			return new LatLng(Double.parseDouble(returnRequestEntity.getReturnLatitude()), Double.parseDouble(returnRequestEntity.getReturnLongitude()));
		}

		return next.getGoogleLocationLatLng(planOrderEntity);
	}

	@Override
	public String getFormattedAddress(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;

		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			return returnRequestRepository.findOne(planOrderEntity.getOrderId()).getReturnDescription();
		}
		return next.getFormattedAddress(planOrderEntity);
	}

	@Override
	public Location getLocationDto(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;

		ReturnRequestEntity returnRequestEntity = null;
		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			returnRequestEntity = returnRequestRepository.findOne(planOrderEntity.getOrderId());
			return new Location(Double.parseDouble(returnRequestEntity.getReturnLatitude()), Double.parseDouble(returnRequestEntity.getReturnLongitude()));
		}
		return next.getLocationDto(planOrderEntity);
	}

	@Override
	public BigDecimal getPackageWeight(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;

		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			return planOrderEntity.getPackage().getVerifiedPackage().getActualweight();
		}
		return next.getPackageWeight(planOrderEntity);
	}

	@Override
	public String getPackageType(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;

		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			return planOrderEntity.getPackage().getVerifiedPackage().getPackageType().getPackagetype();
		}
		return next.getPackageType(planOrderEntity);
	}

	@Override
	public JobOrder fillCouchbaseJobOrderFromPlanOrder(PlanOrderEntity planOrderEntity) {
		if (planOrderEntity == null)
			return null;


		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {

			ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(planOrderEntity.getOrderId());
			UsersEntity sender = usersJpaRepository.findOne(returnRequestEntity.getRequester().getCustomerId());

			return new JobOrder(returnRequestEntity.getReturnRequestId(), "RETURN", sender.getUsername(), sender.getPhone()
					, null
					, new com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location(returnRequestEntity.getPickuplatitude(), returnRequestEntity.getPickuplongitude())
					, returnRequestEntity.getRecipientname(), returnRequestEntity.getRecipientmobile(), returnRequestEntity.getRequestStatus(), planOrderEntity.getPriority().intValue()
					, planOrderEntity.getPackage().getVerifiedPackage().getActualweight().intValue());
		}
		return next.fillCouchbaseJobOrderFromPlanOrder(planOrderEntity);
	}

	@Override
	public Object getNotificationModelWithRequestDetails(Long packageId, Long requestId, String requestType, ActiveVehicleEntity activeVehicleEntity) throws JsonProcessingException {
		ReturnRequestEntity returnRequestEntity = null;
		if (packageId == null || requestId == null || requestType == null || activeVehicleEntity == null)
			return null;
		if ("RETURN".equalsIgnoreCase(requestType)) {
			returnRequestEntity = returnRequestRepository.findOne(requestId);
			Map<String, Object> map = new HashMap<>();
			map.put("packageId", packageId);
			map.put("driverId", activeVehicleEntity.getDriver().getId());
			kafkaTemplate.send("RETURN_DRIVER_START_NAVIGATION", MAPPER.writeValueAsString(map));
			return RequestChain.buildPushNotificationModelWithSerializationToSendToCustomer(packageId, returnRequestEntity.getRequester().getCustomerId(), requestId, requestType, returnRequestEntity.getReturnLatitude(), returnRequestEntity.getReturnLongitude(), activeVehicleEntity);
		}
		return next.getNotificationModelWithRequestDetails(packageId, requestId, requestType, activeVehicleEntity);
	}

	@Override
	public PackageDetails toPackageDetails(Long requestId, String requestType, PackageEntity packageEntity, Long driverId) throws JsonProcessingException {
		ReturnRequestEntity returnRequestEntity = null;
		if (requestId == null || requestType == null || packageEntity == null)
			return null;
		if ("RETURN".equalsIgnoreCase(requestType)) {
			returnRequestEntity = returnRequestRepository.findOne(requestId);
			PackageDetails packageDetails = new PackageDetails();
			packageDetails.setPackageId(packageEntity.getPackageId());
			packageDetails.setRequestId(requestId);
			packageDetails.setRequestType(requestType);
			packageDetails.setNickname(packageEntity.getNickname());
			packageDetails.setType(ObjectMapper.toPackageType(packageEntity.getVerifiedPackage().getPackageType()));
			packageDetails.setPackageValue(packageEntity.getVerifiedPackage().getPackageValue());
			packageDetails.setWeight(packageEntity.getVerifiedPackage().getActualweight());
			packageDetails.setDescription(packageEntity.getDescription());
			packageDetails.setWrappingLabel(returnRequestEntity.getLabelingtext());
			packageDetails.setBoxing(returnRequestEntity.getAdditionalservices());
			packageDetails.setPackagingLabelsIds(ObjectMapper.toListOfPackageLabelsIds(packageEntity.getListOfPackageLabel()));
			packageDetails.setPaymentType(returnRequestEntity.getPaymenttype().toUpperCase());
			packageDetails.setPaymentMethod(returnRequestEntity.getPaymentmethod().toUpperCase());
			packageDetails.setImageIds(ObjectMapper.toListOfPackageImagesIds(packageEntity.getListOfStaticContent()));
			packageDetails.setRequesterId(returnRequestEntity.getRequester().getCustomerId());
			packageDetails.setRecipientId(returnRequestEntity.getRecipient() != null ? returnRequestEntity.getRecipient().getCustomerId() : null);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("packageId", packageEntity.getPackageId());
			//added as required at return workflow...
					map.put("driverId", driverId);
			//
			kafkaTemplate.send("RETURN_DRIVER_ARRIVED", MAPPER.writeValueAsString(map));
			return packageDetails;
		}
		return next.toPackageDetails(requestId, requestType, packageEntity, driverId);
	}

	@Override
	public com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(PlanOrderEntity planOrderEntity) {
		ReturnRequestEntity returnRequestEntity = null;
		if (planOrderEntity == null)
			return null;

		if ("RETURN".equalsIgnoreCase(planOrderEntity.getOrderType())) {
			returnRequestEntity = returnRequestRepository.findOne(planOrderEntity.getOrderId());
			com.appzoneltd.lastmile.microservice.details.dto.JobOrder jobOrder = new com.appzoneltd.lastmile.microservice.details.dto.JobOrder();
			jobOrder.setId(planOrderEntity.getOrderId());
			jobOrder.setAddress(returnRequestEntity.getReturnFormattedAddress());
			jobOrder.setLocation(new Location(Double.parseDouble(returnRequestEntity.getReturnLatitude()), Double.parseDouble(returnRequestEntity.getReturnLongitude())));
			jobOrder.setPackageId(planOrderEntity.getPackage().getPackageId());
			jobOrder.setPackageType(this.getPackageType(planOrderEntity));
			jobOrder.setPriority(planOrderEntity.getPriority().intValue());
			jobOrder.setRequestType(planOrderEntity.getOrderType());
			jobOrder.setWeight(this.getPackageWeight(planOrderEntity));
			jobOrder.setTimeFrom(returnRequestEntity.getTimeFrom());
			jobOrder.setTimeTo(returnRequestEntity.getTimeTo());
			jobOrder.setTiming(new JobOrderTiming(planOrderEntity.getDepartureTime(), planOrderEntity.getArrivalTime(),
					null, null, planOrderEntity.getEta(), null));
			jobOrder.setRejectionReason(planOrderEntity.getRejectionReasonStr());
			jobOrder.setResponse(planOrderEntity.getDriverResponse());
			jobOrder.setStatus(returnRequestEntity.getRequestStatus());
			jobOrder.setExcluded(planOrderEntity.getExcluded());
			return jobOrder;
		}

		return next.fillJobOrderDto(planOrderEntity);
	}

	@Override
	public boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId) throws EntityNotFoundException, JsonProcessingException {
		if (!"RETURN".equalsIgnoreCase(packageDetails.getRequestType()))
			return next.verifyDetails(packageDetails, activeVehicleId);
		return true;
	}

	@Override
	public Invoice generateInvoice(Long requestId, String requestType) throws EntityNotFoundException {
		if (!"RETURN".equalsIgnoreCase(requestType))
			return next.generateInvoice(requestId, requestType);
		return null;
	}

	@Override
	public void confirmInvoice(Long requestId, String requestType, Long driverId) throws EntityNotFoundException, JsonProcessingException {
		if (!"RETURN".equalsIgnoreCase(requestType))
			next.confirmInvoice(requestId, requestType, driverId);

		else {
			Map<String, Object> data = new HashMap<>();
			ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(requestId);
			List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
			if (returnRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
				throw new EntityNotFoundException("package.notfound");
			data.put("requestId", requestId);
			data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
			data.put("driverId", driverId);
			data.put("customerApproveInvoice", true);
			kafkaTemplate.send("RETURN_CUSTOMER_COST_APPROVE", MAPPER.writeValueAsString(data));
		}
	}

	@Override
	public Boolean submitAndAddDocuments(Documents documents, Long activeVehicleId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {
		if (!"RETURN".equalsIgnoreCase(documents.getRequestType()))
			return next.submitAndAddDocuments(documents, activeVehicleId);

		Map<String, Object> data = new HashMap<>();
		ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(documents.getRequestId());
		List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(documents.getRequestId());

		if (returnRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
			throw new EntityNotFoundException("package.notfound");

		data.put("requestId", documents.getRequestId());
		data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
		data.put("driverId", activeVehicleRepository.findOne(activeVehicleId).getDriver().getId());
		data.put("customerApproveInvoice", true);
		kafkaTemplate.send("RETURN_CUSTOMER_COST_APPROVE", MAPPER.writeValueAsString(data));

		return true;
	}

	@Override
	public RequestDetails fillOrderDetails(Long requestId, String requestType) throws EntityNotFoundException {
		if (!"RETURN".equalsIgnoreCase(requestType))
			return next.fillOrderDetails(requestId, requestType);

		ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(requestId);
		PackageEntity packageEntity = null;
		UsersEntity usersEntity = null;
		if (returnRequestEntity == null)
			throw new EntityNotFoundException("package.notfound");

		packageEntity = requestHistoryRepository.findByRequestId(returnRequestEntity.getReturnRequestId()).get(0).getPackageEntity();

		if (returnRequestEntity.getRecipient() != null)
			usersEntity = usersJpaRepository.findOne(returnRequestEntity.getRecipient().getCustomerId());

		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setId(returnRequestEntity.getReturnRequestId());
		requestDetails.setPaymentAt(returnRequestEntity.getPaymenttype());
		requestDetails.setPaymentMethod(returnRequestEntity.getPaymentmethod());
		requestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
		requestDetails.setShipmentServiceType(packageEntity.getShipmentServiceType().getType());
		requestDetails.setPickupTime(returnRequestEntity.getTimeFrom() == null ? "Now" : returnRequestEntity.getTimeFrom() + " - " + returnRequestEntity.getTimeTo());
		requestDetails.setPickupAddress(returnRequestEntity.getPickupformatedaddress());
		requestDetails.setNickName(packageEntity.getNickname());
		requestDetails.setPackageType(packageEntity.getVerifiedPackage().getPackageType().getPackagetype());
		requestDetails.setPackageWeight(packageEntity.getVerifiedPackage().getActualweight().toEngineeringString());
		requestDetails.setWhatInside(packageEntity.getVerifiedPackage().getDescription());
		requestDetails.setAdditionalServices(returnRequestEntity.getAdditionalservices());
		requestDetails.setRecipientName(returnRequestEntity.getRecipientname());
		requestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : returnRequestEntity.getRecipientmobile());
		requestDetails.setRecipientLocation(returnRequestEntity.getRecipientformatedaddress());
		requestDetails.setAdditionalNotes(returnRequestEntity.getDescription());
		requestDetails.setImageIds(packageEntity.getVerifiedPackage().getListOfStaticContent().stream().map(image -> image.getContentId()).collect(Collectors.toList()));
		requestDetails.setPackageValue(packageEntity.getPackageValue());

		return requestDetails;
	}

	@Override
	public void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException {
		if (!"RETURN".equalsIgnoreCase(cancelRequest.getRequestType())) {
			next.cancelRequest(cancelRequest);
		} else {
			//            cancelRequest.setRequesterId(returnRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
			//            Map<String, Object> data = new HashMap<>();
			//            data.put("packageId", requestHistoryRepository.findByRequestId(cancelRequest.getRequestId()).get(0).getPackageEntity().getPackageId());
			//            data.put("customerApproveInvoice", false);
			//            kafkaTemplate.send("RETURN_CUSTOMER_COST_APPROVE", MAPPER.writeValueAsString(data));
			//            //        kafkaTemplate.send("CancelReturnRequest", MAPPER.writeValueAsString(cancelRequest));

			switch (cancelRequest.getRejectionPhase()) {
			//in these cases: we will trigger the AcTION_NEEDED of the delivery
			case PACKAGE_VERIFICATION_REJECTED:
			case CUSTOMER_NOT_FOUND:

				cancelRequest.setRequesterId(returnRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
				Map<String, Object> data1 = new HashMap<>();
				data1.put("packageId", requestHistoryRepository.findByRequestId(cancelRequest.getRequestId()).get(0).getPackageEntity().getPackageId());
				data1.put("customerFound", false);
				System.out.println(">>>>>>>>>>>>>>>> sending to topic:: RETURN_CUSTOMER_FOUND :: FALSE - due to rejection phase:: " + cancelRequest.getRejectionPhase());
				kafkaTemplate.send("RETURN_CUSTOMER_FOUND", MAPPER.writeValueAsString(data1));
				break;

				//in this case we will trigger the return flow
			case INVOICE_REJECTED:
			case SUBMIT_DOCUMENT_REJECTED:
				cancelRequest.setRequesterId(returnRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
				Map<String, Object> data = new HashMap<>();
				data.put("packageId", requestHistoryRepository.findByRequestId(cancelRequest.getRequestId()).get(0).getPackageEntity().getPackageId());
				data.put("customerApproveInvoice", false);
				System.out.println("&&&&&&&&&>>>>>>>>>>>>>>>> sending to topic:: RETURN_CUSTOMER_COST_APPROVE - due to rejection phase:: " + cancelRequest.getRejectionPhase());
				kafkaTemplate.send("RETURN_CUSTOMER_COST_APPROVE", MAPPER.writeValueAsString(data));
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean isCompletedOrder(Long orderId, String orderType, TodaySummary todaySummary) {
		if (!"RETURN".equalsIgnoreCase(orderType)) {
			return next.isCompletedOrder(orderId, orderType, todaySummary);
		}
		todaySummary.addReturnOrders(1);
		final ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(orderId);
		if ("RETURNED".equalsIgnoreCase(returnRequestEntity.getRequestStatus())) {
			return true;
		}
		return false;
	}
}
