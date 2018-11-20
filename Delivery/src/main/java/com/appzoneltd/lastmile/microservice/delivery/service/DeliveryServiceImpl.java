package com.appzoneltd.lastmile.microservice.delivery.service;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.*;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.*;
import com.appzoneltd.lastmile.microservice.delivery.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ExtendedDeliveryRequestRepository adminDeliveryRequestRepository;

    @Override
    public List<DeliveryRequest> getDeliveryRequestsByPageAndOffset(PagingParameters pagingParameters, Principal principal) {
        List<ExtendedDeliveryRequestEntity> deliveryRequestEntities = new ArrayList<>();
        List<Long> hubs = getHubsForUser(principal);
        pagingParameters = formatPagingParamsFromSearch(pagingParameters);
        
        String recipientAddressformatted = formatStringForSearch(pagingParameters.getRecipientAddress());
        
        
        deliveryRequestEntities = adminDeliveryRequestRepository.getDeliveryRequestsPagedFiltered(
                pagingParameters.getRequestId(), 
                recipientAddressformatted,
                pagingParameters.getFromDeliveryDate(), pagingParameters.getToDeliveryDate(),
                pagingParameters.getTimeFrom(), pagingParameters.getTimeTo(), pagingParameters.getPackageType(), pagingParameters.getRequestStatus(), hubs, pagingParameters);

        List<DeliveryRequest> deliveryRequests = deliveryRequestEntities
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getRequestStatus().equalsIgnoreCase(o2.getRequestStatus()))
                        return (o1.getCreated().getTime() > o2.getCreated().getTime()) ? 1 : -1;
                    else
                        return o1.getRequestStatus().compareTo(o2.getRequestStatus());
                })
                .map(dtoMapper::mapToDeliveryRequest)
                .collect(Collectors.toList());

        return deliveryRequests;
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
        if (pagingParameters.getRecipientAddress() != null)
            parameters.setRecipientAddress("%" + pagingParameters.getRecipientAddress() + "%");
        if (pagingParameters.getTimeFrom() != null)
            parameters.setTimeFrom("%" + pagingParameters.getTimeFrom() + "%");
        if (pagingParameters.getTimeTo() != null)
            parameters.setTimeTo("%" + pagingParameters.getTimeTo() + "%");
        if (pagingParameters.getPackageType() != null)
            parameters.setPackageType("%" + pagingParameters.getPackageType() + "%");
        if (pagingParameters.getRequestStatus() != null)
            parameters.setRequestStatus(pagingParameters.getRequestStatus());

        if (pagingParameters.getFromDeliveryDate() != null)
            parameters.setFromDeliveryDate(pagingParameters.getFromDeliveryDate());
        else {
            try {
                parameters.setFromDeliveryDate(dateFormat.parse(from));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (pagingParameters.getToDeliveryDate() != null)
            parameters.setToDeliveryDate(pagingParameters.getToDeliveryDate());
        else {
            try {
                parameters.setToDeliveryDate(dateFormat.parse(to));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        parameters.setMaxResult(pagingParameters.getMaxResult());
        parameters.setOrderBy(pagingParameters.getOrderBy());
        parameters.setPage(pagingParameters.getPage());
        return parameters;
    }

    @Override
    public List<DeliveryRequest> getDeliveryRequests(PagingParameters pagingParameters, Principal principal) {
        List<ExtendedDeliveryRequestEntity> deliveryRequestEntities = new ArrayList<>();
        List<Long> hubs = getHubsForUser(principal);
        pagingParameters = formatPagingParamsFromSearch(pagingParameters);
        System.out.println(pagingParameters.toString());
        
        String recipientAddressformatted = formatStringForSearch(pagingParameters.getRecipientAddress());
        
        deliveryRequestEntities = adminDeliveryRequestRepository.getDeliveryRequestsFiltered(
                pagingParameters.getRequestId(), 
                recipientAddressformatted,
                pagingParameters.getFromDeliveryDate(), pagingParameters.getToDeliveryDate(),
                pagingParameters.getTimeFrom(), pagingParameters.getTimeTo(), 
                pagingParameters.getPackageType(), 
                pagingParameters.getRequestStatus(), 
                hubs);

        List<DeliveryRequest> deliveryRequests = deliveryRequestEntities.stream()
                .sorted((o1, o2) -> {
                    if (o1.getRequestStatus().equalsIgnoreCase(o2.getRequestStatus()))
                        return (o1.getCreated().getTime() > o2.getCreated().getTime()) ? 1 : -1;
                    else
                        return o1.getRequestStatus().compareTo(o2.getRequestStatus());
                })
                .map(dtoMapper::mapToDeliveryRequest)
                .collect(Collectors.toList());

        return deliveryRequests;
    }

    private String formatStringForSearch(String stringToFormat) {
		
    	String formattedString = "%"+ stringToFormat.toLowerCase().replace(" ", "%")+"%";
    	
    	System.out.println("formattedString ::: "+ formattedString);
    	
    	return formattedString ;
	}

	@Override
    public Integer getDeliveryRequestsCount(PagingParameters pagingParameters, Principal principal) {
        pagingParameters = formatPagingParamsFromSearch(pagingParameters);
        List<Long> hubs = getHubsForUser(principal);
        return adminDeliveryRequestRepository.getDeliveryRequestsFiltered(
                pagingParameters.getRequestId(), pagingParameters.getRecipientAddress(),
                pagingParameters.getFromDeliveryDate(), pagingParameters.getToDeliveryDate(),
                pagingParameters.getTimeFrom(), pagingParameters.getTimeTo(), pagingParameters.getPackageType(), pagingParameters.getRequestStatus(), hubs).size();


    }

    @Override
    public Integer getDeliveryRequestsCountByPage(PagingParameters pagingParameters) {
        return deliveryRequestRepository.findAll(pagingParameters).getContent().size();
    }

    @Override
    public DeliveryRequestDetails getDeliveryRequestDetailsById(Long deliveryRequestId) {
        if (deliveryRequestId == null)
            return null;
        return dtoMapper.mapToDeliveryRequestDetails(deliveryRequestRepository.findOne(deliveryRequestId));
    }

    @Override
    public DeliveryRequestEntity rescheduleDelivery(Long requestId, Date deliveryDate, String timeFrom, String timeTo) throws EntityNotFoundException, JsonProcessingException {
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
        if (deliveryRequestEntity == null)
            throw new EntityNotFoundException("request.notfound");
        deliveryRequestEntity.setDeliverydate(deliveryDate);
        deliveryRequestEntity.setTimeFrom(timeFrom);
        deliveryRequestEntity.setTimeTo(timeTo);
        kafkaSender.send("push-notification", mapper.writeValueAsString(buildRescheduleDeliveryNotificationModel
                (requestHistoryRepository.findByRequestId(deliveryRequestEntity.getDeliveryRequestId()).get(0).getPackageEntity().getPackageId(), deliveryDate, timeFrom, timeTo
                        , deliveryRequestEntity.getRequester().getCustomerId(), deliveryRequestEntity.getRecipient() != null ? deliveryRequestEntity.getRecipient().getCustomerId() : null)));
        return deliveryRequestRepository.save(deliveryRequestEntity);
    }

    @Override
    public List<RequestHistoryTimeLine> deliveryRequestTimeLine(Long requestId) {
        return requestHistoryRepository.listRequestHistoryTimeLine(requestId).stream().map(dtoMapper::mapToTimeLine)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDeliveryRequest> getRequestForUser(Principal principal) {
        Long customerId = this.getUserId(principal.getName());
        if (customerId == null)
            return null;
        CustomerEntity customerEntity = customerRepository.findOne(customerId);
        return deliveryRequestRepository.findByRecipient(customerEntity).parallelStream()
                .map(dtoMapper::mapToCustomerDelivery)
                .collect(Collectors.toList());
    }

    @Override
    public Object[] getDeliveryStatus() {
        return DeliveryStatus.values();
    }
    
    
    @Override
    public Object[] getDeliveryStatusOnly() {
        return DeliveryStatusOnly.values();
    }

    @Override
    public Boolean checkTrackedPickupRequest(Long requestId) {
        Boolean isTracked = false;
        Integer count = deliveryRequestRepository.trackedDeliveryRequest(requestId).size();
        if (count != null && count > 0)
            isTracked = true;

        return isTracked;
    }

    @Override
    public List<RequestHistoryDTO> fetchArchivedDeliveries(EndPointParameter requestParameter, Principal principal) {
        final List<Long> hubs = principalService.getHubs(principal);
        return requestParameter.getPage() == 0 ? deliveryRequestRepository.findAllByHubIdInAndRequestStatusIn(hubs, Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name())
                , new Sort(Sort.Direction.fromString(requestParameter.getOrderBy().getOrderBy()), "created"))
                .stream().map(dtoMapper::mapToHistoryDTO).collect(Collectors.toList())
                : deliveryRequestRepository.findAllByHubIdInAndRequestStatusIn(hubs, Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name())
                , new PageRequest(requestParameter.getPage() - 1, requestParameter.getMaxResult(), new Sort(Sort.Direction.fromString(requestParameter.getOrderBy().getOrderBy()), "created")))
                .stream().map(dtoMapper::mapToHistoryDTO).collect(Collectors.toList());
    }

    @Override
    public long countArchivedDeliveries(Principal principal) {
        final List<Long> hubs = principalService.getHubs(principal);
        return deliveryRequestRepository.countAllByHubIdInAndRequestStatusIn(hubs, Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name()));
    }

    private Long getUserId(String arg) {
        UsersEntity usersEntity = usersRepository.findByPhoneOrEmail(arg, arg);
        if (usersEntity == null)
            return null;
        return usersEntity.getUserId();
    }

    private NotificationModel buildRescheduleDeliveryNotificationModel(Long packageId, Date deliveryDate, String timeFrom, String timeTo, Long... userIds) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setRecipientType("CUSTOMER");
        notificationModel.setUserIds(userIds);
        notificationModel.setPackageId(packageId);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "Rescheduled Delivery");
        data.put("notification_body", "Delivery rescheduled");
        data.put("notification_item_title", "Delivery rescheduled");
        data.put("notification_item_body", "Delivery rescheduled on " + DateFormat.getDateInstance(DateFormat.FULL).format(deliveryDate) + " within " + timeFrom + " to " + timeTo);
        data.put("type", "DELIVERY_RESCHEDULED");
        data.put("time", new Date().getTime());

        return notificationModel;
    }
}
