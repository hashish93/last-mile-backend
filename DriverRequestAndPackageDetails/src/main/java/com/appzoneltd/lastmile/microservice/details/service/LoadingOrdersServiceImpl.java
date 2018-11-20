package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.component.OnDemandPickupRequest;
import com.appzoneltd.lastmile.microservice.details.service.component.RequestChain;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
@Service
public class LoadingOrdersServiceImpl implements LoadingOrdersService {


	private static final String[] statuses = {"CANCELED", "RETURNED", "DELIVERED", "PICKEDUP",
			"CANCELED_DELIVERY",
			"RE_SCHEDULED_FOR_PICKUP", "RESCHEDULED_FOR_DELIVERY", "RESCHEDULED_FOR_RETURN", "ACTION_NEEDED"};
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final PlanJpaRepository planJpaRepository;
	private final PlanOrderJpaRepository planOrderRepository;
	private final UsersJpaRepository usersRepository;
	private final RegistrationRepository registrationRepository;
	private final CouchbaseTemplate couchbaseTemplate;
	private final GeoApiContext geoApiContext;
	private final RequestChain requestChain;
	private final ActiveVehicleJpaRepository activeVehicleRepository;
	private final OnDemandPickupRequest onDemandPickupRequest;
	private final KafkaTemplate<Long, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;
	@Autowired
	private ActiveVehicleCouchbaseRepo activeVehicleCouchbaseRepo;

	@Autowired
	public LoadingOrdersServiceImpl(PlanJpaRepository planJpaRepository, PlanOrderJpaRepository planOrderRepository,
			UsersJpaRepository usersRepository, RegistrationRepository registrationRepository,
			CouchbaseTemplate couchbaseTemplate, GeoApiContext geoApiContext,
			@Qualifier(value = "pickupRequest") RequestChain requestChain,
			ActiveVehicleJpaRepository activeVehicleRepository, OnDemandPickupRequest onDemandPickupRequest,
			KafkaTemplate<Long, String> kafkaTemplate) {
		this.planJpaRepository = planJpaRepository;
		this.planOrderRepository = planOrderRepository;
		this.usersRepository = usersRepository;
		this.registrationRepository = registrationRepository;
		this.couchbaseTemplate = couchbaseTemplate;
		this.geoApiContext = geoApiContext;
		this.requestChain = requestChain;
		this.activeVehicleRepository = activeVehicleRepository;
		this.onDemandPickupRequest = onDemandPickupRequest;
		this.kafkaTemplate = kafkaTemplate;
		objectMapper = new ObjectMapper();
	}

	@Override
	public JobOrdersRoute getJobOrdersRoute(Principal principal) throws Exception {
		Long activeVehicleId = getActiveVehicleId(getUserId(principal.getName()), now());

		if (activeVehicleId == null)
			throw new RuntimeException("not.active.vehicle");

		final RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);

		if (isOnDemandServicesVehicle(registrationModel))
			throw new RuntimeException("not.scheduled.vehicle");

		Long planId = getLatestPlanId(registrationModel.getHubId());

		if (activeVehicleId == null || planId == null)
			return null;

		com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location activeVehicleLocation = getActiveVehicleLocation(
				activeVehicleId);
		if (activeVehicleLocation == null)
			throw new NullPointerException("There is no location for such active vehicle " + activeVehicleId);

		List<PlanOrderEntity> planOrderEntities = planOrderRepository.findAllAcceptedOrders(activeVehicleId, planId);

		List<com.appzoneltd.lastmile.microservice.details.dto.Location> legs = extractLegsFromGoogleDirectionsApi(
				planOrderEntities, activeVehicleLocation);
		List<JobOrder> jobOrders = planOrderEntities.stream().map(requestChain::fillJobOrderDto)
				.collect(Collectors.toList());

		Long nextRequestId = activeVehicleCouchbaseRepo.getNextOrder(activeVehicleId);
		jobOrders = tagExcludedAndNextOrders(jobOrders, nextRequestId);

		return new JobOrdersRoute(jobOrders, legs);
	}

	@Override
	public List<JobOrder> getLoadingActivitiesForToday(Principal principal) throws Exception {
		Long activeVehicleId = getActiveVehicleId(getUserId(principal.getName()), now());
		if (activeVehicleId == null)
			throw new RuntimeException("not.active.vehicle");

		final RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);

		if (isOnDemandServicesVehicle(registrationModel))
			throw new RuntimeException("not.scheduled.vehicle");

		Long planId = getLatestPlanId(registrationModel.getHubId());

		if (planId == null)
			return null;

		List<PlanOrderEntity> planOrderEntities = planOrderRepository.findAllLoadingOrders(activeVehicleId, planId);

		automaticAcceptPickupRequests(activeVehicleId, planOrderEntities);


		return planOrderEntities.stream()
				//                .filter(planOrderEntity -> (!"PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())))
				.map(requestChain::fillJobOrderDto).collect(Collectors.toList());
	}

	@Async
	private void automaticAcceptPickupRequests(Long activeVehicleId, List<PlanOrderEntity> planOrderEntities) {
		planOrderEntities.stream()
		.filter(planOrderEntity -> ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())))
		.forEach(planOrderEntity -> {
			if (planOrderEntity.getDriverResponse() == null) {
				planOrderEntity.setDriverResponse(DriverResponse.ACCEPT.name());
				if (planOrderRepository.save(planOrderEntity) != null)
					this.SaveOrderOnCouchbase(activeVehicleId, planOrderEntity);
			}
		});
	}

	@Async
	private void SaveOrderOnCouchbase(Long activeVehicleId, PlanOrderEntity planOrderEntity) {
		RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);
		List<com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder> newJobOrders = registrationModel
				.getJobOrders().stream()
				.filter(jobOrder -> jobOrder.getJobOrderId().intValue() != planOrderEntity.getOrderId())
				.collect(Collectors.toList());
		newJobOrders.add(requestChain.fillCouchbaseJobOrderFromPlanOrder(planOrderEntity));
		registrationModel.setJobOrders(newJobOrders);
		registrationRepository.save(registrationModel);
	}

	@Override
	public Boolean responseLoadingOrder(DriverResponseDto driverResponseDto, Principal principal) throws Exception {
		Long activeVehicleId = getActiveVehicleId(getUserId(principal.getName()), now());
		if (activeVehicleId == null)
			throw new EntityNotFoundException("NO ACTIVE VEHICLE");
		PlanOrderEntity planOrderEntity = planOrderRepository.findByOrderId(driverResponseDto.getLoadingActivityId());
		if (planOrderEntity == null)
			throw new EntityNotFoundException("No Loading Order with Id " + driverResponseDto.getLoadingActivityId());

		if (driverResponseDto.getIsAccept()) {
			SaveOrderOnCouchbase(activeVehicleId, planOrderEntity);
			if (planOrderEntity.getRejectionReasonStr() != null)
				planOrderEntity.setRejectionReasonStr(null);
			planOrderEntity.setDriverResponse(DriverResponse.ACCEPT.name());
		} else {
			planOrderEntity.setDriverResponse(DriverResponse.REJECT.name());
			RemoveOrderOnCouchbase(activeVehicleId, planOrderEntity);
			// planOrderEntity.setRejectionReason(rejectionReasonRepository.findOne(driverResponseDto.getRejectionReasonId()));
			planOrderEntity.setRejectionReasonStr(driverResponseDto.getRejectionReason());
		}

		Map<String, Object> workflowData = new HashMap<>();
		workflowData.put("packageId", planOrderEntity.getPackage().getPackageId());
		workflowData.put("driverAcceptance", driverResponseDto.getIsAccept());

		kafkaTemplate.send(planOrderEntity.getOrderType().toUpperCase() + "_DRIVER_RESPONSE",
				RequestChain.MAPPER.writeValueAsString(workflowData));

		if (planOrderRepository.save(planOrderEntity) != null)
			return true;

		return null;
	}

	private void RemoveOrderOnCouchbase(Long activeVehicleId, PlanOrderEntity planOrderEntity) {
		RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);
		List<com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder> newJobOrders = registrationModel
				.getJobOrders().stream()
				.filter(jobOrder -> jobOrder.getJobOrderId().intValue() != planOrderEntity.getOrderId())
				.collect(Collectors.toList());
		registrationModel.setJobOrders(newJobOrders);
		registrationRepository.save(registrationModel);
	}

	@Override
	public List<JobOrder> getJobOrders(Principal principal) {
		Long activeVehicleId = getActiveVehicleId(getUserId(principal.getName()), now());
		if (activeVehicleId == null)
			return null;

		List<JobOrder> jobOrders = null;
		final RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);

		if (isOnDemandServicesVehicle(registrationModel)) {
			List<com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder> couchBaseJobOrders =
					registrationModel.getJobOrders();

			jobOrders = couchBaseJobOrders.parallelStream().map(onDemandPickupRequest::fillJobOrderDto)
					.collect(Collectors.toList());
			markExecludedOrders(jobOrders);
		} else {
			Long planId = getLatestPlanId(registrationModel.getHubId());
			List<PlanOrderEntity> planOrderEntities = planOrderRepository.findAllAcceptedJobOrders(activeVehicleId,
					planId);

			jobOrders = planOrderEntities.stream().map(requestChain::fillJobOrderDto).collect(Collectors.toList());
		}
		// Long nextRequestId =
		// activeVehicleCouchbaseRepo.getNextOrder(activeVehicleId);
		// return tagExcludedAndNextOrders(jobOrders, nextRequestId);
		markNextOrCurrentOrder(jobOrders);

		return jobOrders;
	}

	private void markExecludedOrders(List<JobOrder> jobOrders) {

		for (JobOrder jobOrder : jobOrders) {

			List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository
					.findByRequestIdOfGivenType(jobOrder.getId(), jobOrder.getRequestType());

			if (requestHistoryEntities != null && !requestHistoryEntities.isEmpty()) {

				RequestHistoryEntity historyEntity = requestHistoryEntities.get(0);
				String requestStatus = historyEntity.getRequestStatus();
				jobOrder.setExcluded(Stream.of(statuses).anyMatch(x -> x.equals(requestStatus)));
				// set all the job orders to next = false, then in the call to
				// the markNext we will find the proper next order
				jobOrder.setNext(false);

			} else {
				// else : if there is no history for that order, then mark it
				// execluded
				jobOrder.setExcluded(true);
				jobOrder.setNext(false);
			}
		}

	}

	/**
	 * assuming that the job orders list is sorted ascending by priority ( that
	 * is how is fetched from db) look for the first non-execluded job order,
	 * this order will be thr next or current order
	 *
	 * @param jobOrders
	 */
	private void markNextOrCurrentOrder(List<JobOrder> jobOrders) {
		for (JobOrder jobOrder : jobOrders) {

			if (!jobOrder.isExcluded()) {
				jobOrder.setNext(true);
				break;
			}
		}

	}

	@Override
	public RequestDetails getJobOrderDetails(Long requestId, String requestType) throws EntityNotFoundException {
		return requestChain.fillOrderDetails(requestId, requestType);
	}

	@Override
	public TodaySummary calculateTodaySummary(Principal principal) throws EntityNotFoundException {
		final Long driverId = getUserId(principal.getName());
		final Long activeVehicleId = getActiveVehicleId(driverId, now());
		if (activeVehicleId == null)
			return new TodaySummary();
		final RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);

		if ("FREELANCER_DRIVER".equalsIgnoreCase(registrationModel.getUserType())
				|| "ON_DEMAND_SERVICES".equalsIgnoreCase(registrationModel.getPurpose())) {

			return onDemandPickupRequest.calculateTotalAndCompletedOrders(registrationModel);
		}
		if ("SCHEDULED_SERVICES".equalsIgnoreCase(registrationModel.getPurpose())) {

			return calculateTotalAndCompletedOrders(activeVehicleId, registrationModel.getHubId());
		}


		return null;
	}

	private TodaySummary calculateTotalAndCompletedOrders(Long activeVehicleId, Long hubId) {
		final Long latestPlanId = getLatestPlanId(hubId);

		final List<PlanOrderEntity> allLoadingOrders = 
				planOrderRepository.findAllLoadingOrders(activeVehicleId,latestPlanId);

		final TodaySummary todaySummary = new TodaySummary()
				.addTotalOrders(allLoadingOrders != null ? allLoadingOrders.size() : 0);

		if (allLoadingOrders != null) {

			for (PlanOrderEntity planOrderEntity : allLoadingOrders) {

				if (requestChain.isCompletedOrder(planOrderEntity.getOrderId(), planOrderEntity.getOrderType(), todaySummary)) {

					todaySummary.addCompletedOrders(1);
				}
				else
					todaySummary.addCompletedOrders(0);
			}
		}
		return todaySummary;
	}

	@Override
	public void startWorkingToActiveVehicle(Principal principal) throws EntityNotFoundException {
		Long activeVehicleId = getActiveVehicleId(getUserId(principal.getName()), now());

		if (activeVehicleId == null)
			throw new EntityNotFoundException("Driver Not Found");

		RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);
		registrationModel.setIsWorking(true);
		registrationRepository.save(registrationModel);
	}

	private Long getActiveVehicleId(Long driverId, Date now) {
		List<N1qlQueryRow> n1qlQueryRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
				+ couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
				+ now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows();


		if (n1qlQueryRows == null || n1qlQueryRows.isEmpty()) return null;

		final JsonObject jsonObject = n1qlQueryRows.get(0).value();

		String stringId = jsonObject.getString("id");

		return Long.parseLong(stringId);
	}

	private Long getUserId(String principal) {
		MyPrincipal myPrincipal = null;
		try {

			myPrincipal = objectMapper.readValue(principal, MyPrincipal.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UsersEntity uE = null;
		if (myPrincipal != null) {
			uE = usersRepository.findOne(myPrincipal.getUserId());
		}
		if (uE == null) {
			return null;
		} else {
			return uE.getUserId();
		}
	}

	private Date now() {
		Date now = new Date();
		now.setYear(1970);
		now.setMonth(1);
		now.setDate(1);
		now.setSeconds(1);
		return now;
	}

	private boolean isOnDemandServicesVehicle(final RegistrationModel registrationModel) {
		return VehiclePurpose.ON_DEMAND_SERVICES.name()
				.equalsIgnoreCase(registrationModel.getPurpose());
	}

	private Long getLatestPlanId(Long hubId) {
		List<PlanEntity> planEntities = planJpaRepository.findAllPlansByCreatedDesc(hubId);
		Long planId = null;
		if (planEntities != null && !planEntities.isEmpty())
			planId = planEntities.get(0).getId();

		return planId;
	}

	private com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location getActiveVehicleLocation(
			Long activeVehicleId) {
		RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);
		if (registrationModel == null)
			return null;
		return registrationModel.getLocation();
	}

	@Async
	private List<com.appzoneltd.lastmile.microservice.details.dto.Location> extractLegsFromGoogleDirectionsApi(
			List<PlanOrderEntity> planOrderEntities,
			com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location activeVehicleLocation)
					throws Exception {
		DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);
		directionsApiRequest.origin(new LatLng(Double.parseDouble(activeVehicleLocation.getLatitude()),
				Double.parseDouble(activeVehicleLocation.getLongitude())));
		directionsApiRequest
		.destination(requestChain.getGoogleLocationLatLng(planOrderEntities.get(planOrderEntities.size() - 1)));
		directionsApiRequest.alternatives(false);
		directionsApiRequest.avoid(DirectionsApi.RouteRestriction.TOLLS, DirectionsApi.RouteRestriction.FERRIES);
		directionsApiRequest.mode(TravelMode.DRIVING);
		directionsApiRequest.optimizeWaypoints(false);
		directionsApiRequest.waypoints(planOrderEntities.stream().map(requestChain::getGoogleLocationLatLng)
				.collect(Collectors.toList()).toArray(new LatLng[planOrderEntities.size()]));
		directionsApiRequest.units(Unit.METRIC);
		DirectionsResult directionsResult = directionsApiRequest.awaitIgnoreError();
		return extractLegs(directionsResult);
	}

	private List<JobOrder> tagExcludedAndNextOrders(List<JobOrder> jobOrders, Long nextJobOrder) {
		String[] statuses = {"CANCELED", "RETURNED", "DELIVERED", "PICKEDUP", "RESCHEDULED_FOR_PICKUP",
				"RESCHEDULED_FOR_DELIVERY", "RESCHEDULED_FOR_RETURN"};
		for (JobOrder jobOrder : jobOrders) {
			if (jobOrder.getId() == nextJobOrder.longValue()) {
				jobOrder.setNext(true);
			}
			List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository
					.findByRequestId(jobOrder.getId());
			if (requestHistoryEntities != null && !requestHistoryEntities.isEmpty()) {
				RequestHistoryEntity historyEntity = requestHistoryEntities.get(0);
				String requestStatus = historyEntity.getRequestStatus();
				jobOrder.setExcluded(Stream.of(statuses).anyMatch(x -> x.equals(requestStatus)));
			}
		}
		return jobOrders;
	}

	private List<com.appzoneltd.lastmile.microservice.details.dto.Location> extractLegs(
			DirectionsResult directionsResult) {
		List<com.appzoneltd.lastmile.microservice.details.dto.Location> legs = new ArrayList<>();
		for (DirectionsRoute route : directionsResult.routes) {
			for (DirectionsLeg leg : route.legs) {
				for (DirectionsStep step : leg.steps) {
					legs.add(new com.appzoneltd.lastmile.microservice.details.dto.Location(step.startLocation.lat,
							step.startLocation.lng));
					legs.add(new com.appzoneltd.lastmile.microservice.details.dto.Location(step.endLocation.lat,
							step.endLocation.lng));
				}
			}
		}
		return legs;
	}


}