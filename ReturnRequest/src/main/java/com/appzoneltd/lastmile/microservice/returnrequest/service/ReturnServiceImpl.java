package com.appzoneltd.lastmile.microservice.returnrequest.service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.*;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.*;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.*;
import com.appzoneltd.lastmile.microservice.returnrequest.kafka.model.ResceduledWorkFlowBase;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 3/7/2017 11:52 AM.
 */
@Service
public class ReturnServiceImpl implements ReturnService {

    private final PlanOrderJpaRepository planOrderJpaRepository;
    @Autowired
    private ReturnRequestRepository returnRequestRepository;
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ActiveVehicleJpaRepository activeVehicleRepository;
    @Autowired
    private PrincipalService principalService;

    @Autowired
    private BuildingRepository buildingRepository;

    public ReturnServiceImpl(ReturnRequestRepository returnRequestRepository,
                             DeliveryRequestRepository deliveryRequestRepository, RequestHistoryRepository requestHistoryRepository,
                             PlanOrderJpaRepository planOrderJpaRepository, DTOMapper dtoMapper, KafkaSender kafkaSender) {
        this.returnRequestRepository = returnRequestRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.kafkaSender = kafkaSender;
        this.dtoMapper = dtoMapper;
        mapper = new ObjectMapper();

        this.planOrderJpaRepository = planOrderJpaRepository;
    }

    @Override
    public ReturnRequestEntity saveReturnRequest(ReturnSchedule returnSchedule) throws EntityNotFoundException {
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(returnSchedule.getRequestId());
        if (deliveryRequestEntity == null)
            throw new EntityNotFoundException("request.notfound");

        ReturnRequestEntity returnRequestEntity = this.mapToReturnRequestEntity(deliveryRequestEntity);
        returnRequestEntity.setReturnDate(returnSchedule.getReturnDate());
        returnRequestEntity.setTimeFrom(returnSchedule.getReturnTimeFrom());
        returnRequestEntity.setTimeTo(returnSchedule.getReturnTimeTo());
        returnRequestEntity.setReturnDescription(returnSchedule.getReturnDescription());
        returnRequestEntity.setRequestStatus(ReturnStatus.SCHEDULED_FOR_RETURN.name());

        if (!returnSchedule.isSenderAddress()) {
            returnRequestEntity.setReturnLatitude(returnSchedule.getLocation().getLatitude());
            returnRequestEntity.setReturnLongitude(returnSchedule.getLocation().getLongitude());
            returnRequestEntity.setReturnFormattedAddress(returnSchedule.getLocation().getFormattedAddress());
        }

        if (saveReturnRequestHistory(returnRequestEntity, ReturnStatus.SCHEDULED_FOR_RETURN) == null)
            return null;

        deliveryRequestEntity.setRequestStatus("ENDED");
        deliveryRequestRepository.save(deliveryRequestEntity);
        // deliveryRequestRepository.delete(deliveryRequestEntity);

        return returnRequestRepository.save(returnRequestEntity);
    }

    private ReturnRequestEntity mapToReturnRequestEntity(DeliveryRequestEntity deliveryRequestEntity) {
        ReturnRequestEntity returnRequestEntity = new ReturnRequestEntity();
        returnRequestEntity.setReturnRequestId(deliveryRequestEntity.getDeliveryRequestId());
        returnRequestEntity.setRequesttime(deliveryRequestEntity.getRequesttime());
        returnRequestEntity.setPickuplongitude(deliveryRequestEntity.getPickuplongitude());
        returnRequestEntity.setPickuplatitude(deliveryRequestEntity.getPickuplatitude());
        returnRequestEntity.setHubId(deliveryRequestEntity.getHubId());
        returnRequestEntity.setPickupwasellocation(deliveryRequestEntity.getPickupwasellocation());
        returnRequestEntity.setPickupformatedaddress(deliveryRequestEntity.getPickupformatedaddress());
        returnRequestEntity.setPickupdate(deliveryRequestEntity.getPickupdate());
        returnRequestEntity.setRecipientname(deliveryRequestEntity.getRecipientname());
        returnRequestEntity.setRecipientmobile(deliveryRequestEntity.getRecipientmobile());
        returnRequestEntity.setRecipientlongitude(deliveryRequestEntity.getRecipientlongitude());
        returnRequestEntity.setRecipientlatitude(deliveryRequestEntity.getRecipientlatitude());
        returnRequestEntity.setRecipientwasellocation(deliveryRequestEntity.getRecipientwasellocation());
        returnRequestEntity.setRecipientformatedaddress(deliveryRequestEntity.getRecipientformatedaddress());
        returnRequestEntity.setRecipientadditionalinfo(deliveryRequestEntity.getRecipientadditionalinfo());
        returnRequestEntity.setAdditionalservices(deliveryRequestEntity.getAdditionalservices());
        returnRequestEntity.setLabelingtext(deliveryRequestEntity.getLabelingtext());
        returnRequestEntity.setPaymenttype(deliveryRequestEntity.getPaymenttype());
        returnRequestEntity.setPaymentmethod(deliveryRequestEntity.getPaymentmethod());
        returnRequestEntity.setDescription(deliveryRequestEntity.getDescription());
        returnRequestEntity.setVersion(0L);
        returnRequestEntity.setCreated(new Date());
        returnRequestEntity.setRecipient(deliveryRequestEntity.getRecipient());
        returnRequestEntity.setRequester(deliveryRequestEntity.getRequester());
        returnRequestEntity.setReturnLongitude(deliveryRequestEntity.getPickuplongitude());
        returnRequestEntity.setReturnLatitude(deliveryRequestEntity.getPickuplatitude());
        returnRequestEntity.setReturnFormattedAddress(deliveryRequestEntity.getPickupformatedaddress());
        // returnRequestEntity.setRequestStatus();
        // returnRequestEntity.setTimeFrom("");
        // returnRequestEntity.setTimeTo("");
        // returnRequestEntity.setReturnDate(new Date());
        // returnRequestEntity.setReturnDescription("");

        return returnRequestEntity;
    }

    private RequestHistoryEntity saveReturnRequestHistory(ReturnRequestEntity returnRequestEntity,
                                                          ReturnStatus returnStatus) {
        RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
        requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
        requestHistoryEntity.setRequestStatus(returnStatus.name());
        requestHistoryEntity.setCreated(new Date());
        requestHistoryEntity.setRequestId(returnRequestEntity.getReturnRequestId());
        requestHistoryEntity.setVersion(0L);
        requestHistoryEntity.setRequestType("RETURN");
        requestHistoryEntity.setPackageEntity(requestHistoryRepository
                .findByRequestId(returnRequestEntity.getReturnRequestId()).get(0).getPackageEntity());
        return requestHistoryRepository.save(requestHistoryEntity);
    }

    @Override
    public List<ReturnRequest> getReturnRequestsByPageAndOffset(PagingParameters pagingParameters,
                                                                Principal principal) {
        List<Long> hubs = getHubsForUser(principal);
        pagingParameters = formatPagingParamsFromSearch(pagingParameters);
        List<ReturnRequest> returnRequests = returnRequestRepository
                .getReturnRequestForHubsPageable(hubs, pagingParameters.getRequestId(),
                        pagingParameters.getSenderName(), pagingParameters.getSenderPhone(),
                        pagingParameters.getReturnAddress(), pagingParameters.getPackageStatus(),
                        pagingParameters.getFromReturnDate(), pagingParameters.getToReturnDate(),
                        pagingParameters.getPackageType(), pagingParameters)
                .getContent().stream().map(dtoMapper::mapToReturnRequest).collect(Collectors.toList());

        returnRequests.sort((o1, o2) -> ReturnStatus.ACTION_NEEDED.name().equalsIgnoreCase(o1.getRequestStatus())
                || ReturnStatus.ACTION_NEEDED.name().equalsIgnoreCase(o2.getRequestStatus()) ? 1 : -1);

        markInTodaysPlan(returnRequests);

        return returnRequests;
    }

    @Override
    public List<ReturnRequest> getReturnRequests(Sort sort, Principal principal) {
        List<Long> hubs = getHubsForUser(principal);
        List<ReturnRequest> returnRequests = ((List<ReturnRequestEntity>) returnRequestRepository
                .getReturnRequestForHubs(hubs)).stream().map(dtoMapper::mapToReturnRequest)
                .collect(Collectors.toList());

        returnRequests.sort((o1, o2) -> ReturnStatus.ACTION_NEEDED.name().equalsIgnoreCase(o1.getRequestStatus())
                || ReturnStatus.ACTION_NEEDED.name().equalsIgnoreCase(o2.getRequestStatus()) ? 1 : -1);

        markInTodaysPlan(returnRequests);

        return returnRequests;
    }

    @Override
    public Integer getReturnRequestsCount(PagingParameters pagingParameters, Principal principal) {
        List<Long> hubs = getHubsForUser(principal);
        pagingParameters = formatPagingParamsFromSearch(pagingParameters);
        return ((List<ReturnRequestEntity>) returnRequestRepository.getReturnRequestForHubsNotPageable(hubs,
                pagingParameters.getRequestId(), pagingParameters.getSenderName(), pagingParameters.getSenderPhone(),
                pagingParameters.getReturnAddress(), pagingParameters.getPackageStatus(),
                pagingParameters.getFromReturnDate(), pagingParameters.getToReturnDate(),
                pagingParameters.getPackageType())).size();
    }

    @Override
    public Integer getReturnRequestsCountByPage(PagingParameters pagingParameters) {
        return returnRequestRepository.findAll(pagingParameters).getContent().size();
    }

    @Override
    public ReturnRequestDetails getReturnRequestDetailsById(Long returnRequestId) {
        if (returnRequestId == null)
            return null;
        return dtoMapper.mapToReturnRequestDetails(returnRequestRepository.findOne(returnRequestId));
    }

    @Override
    public ReturnRequestEntity rescheduleReturn(Long requestId, Date returnDate, String timeFrom, String timeTo)
            throws EntityNotFoundException, JsonProcessingException {
        ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(requestId);
        if (returnRequestEntity == null)
            throw new EntityNotFoundException("request.notfound");
        ReturnStatus returnStatus = ReturnStatus.SCHEDULED_FOR_RETURN;

        final long countAllByRequestTypeAndRequestIdAndRequestStatusIn = requestHistoryRepository.countAllByRequestTypeAndRequestIdAndRequestStatusIn("RETURN", requestId, Arrays.asList(ReturnStatus.SCHEDULED_FOR_RETURN.name(), ReturnStatus.RESCHEDULED_FOR_RETURN.name()));

        if (countAllByRequestTypeAndRequestIdAndRequestStatusIn > 0) {
            returnStatus = ReturnStatus.RESCHEDULED_FOR_RETURN;
        } else {
            returnStatus = ReturnStatus.SCHEDULED_FOR_RETURN;
        }

        returnRequestEntity.setReturnDate(returnDate);
        returnRequestEntity.setTimeFrom(timeFrom);
        returnRequestEntity.setTimeTo(timeTo);
        returnRequestEntity.setRequestStatus(returnStatus.name());
        updateRequestStatus(requestId, returnStatus);

        Long packageId = requestHistoryRepository.getPackageId(requestId);

        kafkaSender.send("push-notification",
                mapper.writeValueAsString(buildRescheduleDeliveryNotificationModel(packageId, returnDate, timeFrom,
                        timeTo, returnRequestEntity.getRequester().getCustomerId())));

        if ("RESCHEDULED_FOR_RETURN".equalsIgnoreCase(returnRequestEntity.getRequestStatus())) {

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> packageId " + packageId);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> requestId " + requestId);
            ResceduledWorkFlowBase resceduledWorkFlowBase = new ResceduledWorkFlowBase();

            resceduledWorkFlowBase.setPackageId(packageId);

            Long driverId = getDriverIdForRequestId(requestId);
            if (driverId != null) {
                resceduledWorkFlowBase.setRequestType("RETURN");
                resceduledWorkFlowBase.setRequestId(requestId);
                resceduledWorkFlowBase.setPackageId(packageId);
                resceduledWorkFlowBase.setDriverId(driverId);
                resceduledWorkFlowBase.setRequestStatus(returnRequestEntity.getRequestStatus());
                resceduledWorkFlowBase.setSenderType("ADMIN");
                System.out.println("resceduledWorkFlowBase>>>>>>>>>>>>>>>>" + resceduledWorkFlowBase);
                setRequestToExcludedOnPlan(requestId);
                kafkaSender.send("push-notification", mapper
                        .writeValueAsString(buildRescheduleDeliveryNotificationModelToDriver(resceduledWorkFlowBase)));
            }

        }
        return returnRequestRepository.save(returnRequestEntity);
    }

    private void updateRequestStatus(Long requestId, ReturnStatus returnStatus) {
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (requestHistoryEntities != null && requestHistoryEntities.size() > 0) {
            PackageEntity packageEntity = requestHistoryEntities.get(0).getPackageEntity();
            RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
            if (packageEntity != null) {
                requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
                requestHistoryEntity.setRequestId(requestId);
                requestHistoryEntity.setRequestStatus(returnStatus.name());
                requestHistoryEntity.setRequestType("RETURN");
                requestHistoryEntity.setPackageEntity(packageEntity);
                requestHistoryEntity.setCreated(new Date());
                requestHistoryEntity.setVersion(0L);
                packageEntity.setStatus(returnStatus.name());

                requestHistoryRepository.save(requestHistoryEntity);
                packageRepository.save(packageEntity);
            }

        }
    }

    private NotificationModel buildRescheduleDeliveryNotificationModel(Long packageId, Date returnDate, String timeFrom,
                                                                       String timeTo, Long... userIds) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setRecipientType("CUSTOMER");
        notificationModel.setUserIds(userIds);
        notificationModel.setPackageId(packageId);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "Rescheduled Return");
        data.put("notification_body", "Return rescheduled");
        data.put("notification_item_title", "Return rescheduled");
        data.put("notification_item_body",
                "Return rescheduled on " + DateFormat.getDateInstance(DateFormat.FULL).format(returnDate) + " within "
                        + timeFrom + " to " + timeTo);
        data.put("type", "RETURN_RESCHEDULED");
        data.put("time", new Date().getTime());
        notificationModel.setData(data);

        return notificationModel;
    }

    private Long getDriverIdForRequestId(Long requestId) {
        Long DriverId = null;
        Long activeVehicleId = null;
        PlanOrderEntity planOrderEntity = planOrderJpaRepository.findByReturnId(requestId);
        if (planOrderEntity != null) {
            activeVehicleId = planOrderEntity.getActiveVehicleId();
            if (activeVehicleId != null) {
                ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);
                if (activeVehicleEntity != null && activeVehicleEntity.getDriver() != null) {
                    DriverId = activeVehicleEntity.getDriver().getId();
                }
            }
        }
        return DriverId;
    }

    private void setRequestToExcludedOnPlan(Long requestId) {
        PlanOrderEntity planOrderEntity = planOrderJpaRepository.findByReturnId(requestId);
        if (planOrderEntity != null) {
            planOrderEntity.setExcluded(true);
            planOrderJpaRepository.save(planOrderEntity);
        }
    }

    private NotificationModel buildRescheduleDeliveryNotificationModelToDriver(
            ResceduledWorkFlowBase resceduledWorkFlowBase) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setRecipientType("DRIVER");
        Long[] userIds = new Long[1];
        userIds[0] = resceduledWorkFlowBase.getDriverId();
        notificationModel.setUserIds(userIds);
        notificationModel.setPackageId(resceduledWorkFlowBase.getPackageId());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "Rescheduled Return");
        data.put("notification_body", "Return rescheduled");
        data.put("notification_item_title", "Return rescheduled");
        data.put("notification_item_body", "Return rescheduled");
        ;
        data.put("type", "RETURN_RESCHEDULED");
        data.put("time", new Date().getTime());
        notificationModel.setData(data);

        return notificationModel;
    }

    @Override
    public List<RequestHistoryTimeLine> returnRequestTimeLine(Long requestId) {
        return requestHistoryRepository.listRequestHistoryTimeLine(requestId).stream().map(dtoMapper::mapToTimeLine)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArchivedReturn> getArchivedReturnRequests(Principal principal, PagingParameters pagingParameters) {
        List<ArchivedReturn> result = new ArrayList<>();
        List<Long> hubIds = getHubsForUser(principal);

        if (!pagingParameters.getHubIds().isEmpty())
            hubIds = pagingParameters.getHubIds();

        result = returnRequestRepository.getArchivedReturns(hubIds).stream().map(dtoMapper::mapToArchivedReturn)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ArchivedReturn> getArchivedReturnRequestsPagable(Principal principal,
                                                                 PagingParameters pagingParameters) {
        List<ArchivedReturn> result = new ArrayList<>();
        List<Long> hubIds = getHubsForUser(principal);

        if (!pagingParameters.getHubIds().isEmpty())
            hubIds = pagingParameters.getHubIds();

        result = ((List<ArchivedReturn>) returnRequestRepository.getArchivedReturnsPageable(hubIds, pagingParameters)
                .getContent().stream().map(dtoMapper::mapToArchivedReturn).collect(Collectors.toList()));
        return result;
    }

    @Override
    public Object[] getReturnStatus() {
        return ReturnStatus.values();
    }

    @Override
    public Integer getArchiveReturnRequestsCount(Principal principal) {
        List<Long> hubIds = getHubsForUser(principal);
        return returnRequestRepository.getArchivedReturns(hubIds).size();
    }

    private List<Long> getHubsForUser(Principal principal) {
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        List<Long> hubs = new ArrayList<>();
        if (principalService.isSuperAdmin(principal)) {
            buildingEntities = buildingRepository.getAllActiveHubs();
            for (BuildingEntity buildingEntity : buildingEntities) {
                hubs.add(buildingEntity.getBuildingId());
            }
        } else {
            hubs = principalService.getHubs(principal);
        }
        return hubs;
    }

    private PagingParameters formatPagingParamsFromSearch(PagingParameters pagingParameters) {
        PagingParameters parameters = new PagingParameters();
        String from = "1970-01-01";
        String to = "2970-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");

        if (pagingParameters.getRequestId() != null)
            parameters.setRequestId("%" + pagingParameters.getRequestId() + "%");
        if (pagingParameters.getSenderName() != null)
            parameters.setSenderName("%" + pagingParameters.getSenderName() + "%");
        if (pagingParameters.getSenderPhone() != null)
            parameters.setSenderPhone("%" + pagingParameters.getSenderPhone() + "%");
        if (pagingParameters.getReturnAddress() != null)
            parameters.setReturnAddress("%" + pagingParameters.getReturnAddress() + "%");

        // if (pagingParameters.getTimeFrom() != null)
        // parameters.setTimeFrom("%" + pagingParameters.getTimeFrom() + "%");
        // if (pagingParameters.getTimeTo() != null)
        // parameters.setTimeTo("%" + pagingParameters.getTimeTo() + "%");
        if (pagingParameters.getPackageType() != null)
            parameters.setPackageType("%" + pagingParameters.getPackageType() + "%");
        if (pagingParameters.getPackageStatus() != null)
            parameters.setPackageStatus(pagingParameters.getPackageStatus());
        //
        if (pagingParameters.getFromReturnDate() != null)
            parameters.setFromReturnDate(pagingParameters.getFromReturnDate());
        else {
            try {
                parameters.setFromReturnDate(dateFormat.parse(from));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (pagingParameters.getToReturnDate() != null)
            parameters.setToReturnDate(pagingParameters.getToReturnDate());
        else {
            try {
                parameters.setToReturnDate(dateFormat.parse(to));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        parameters.setMaxResult(pagingParameters.getMaxResult());
        parameters.setOrderBy(pagingParameters.getOrderBy());
        parameters.setPage(pagingParameters.getPage());
        return parameters;
    }

    private void markInTodaysPlan(List<ReturnRequest> returnRequests) {

        DateTime start = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        DateTime end = DateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);

        List<PlanOrderEntity> planOrders = planOrderJpaRepository.findByOrderTypeAndCreatedBetween("RETURN",
                start.toDate(), end.toDate());

        Map<Long, Long> result1 = planOrders.stream()
                .collect(Collectors.toMap(PlanOrderEntity::getOrderId, PlanOrderEntity::getId));

        for (ReturnRequest currentRequest : returnRequests) {

            if (result1.get(currentRequest.getRequestId()) != null) {

                currentRequest.setInTodaysPlan(true);
            } else {
                currentRequest.setInTodaysPlan(false);
            }
        }

    }
}
