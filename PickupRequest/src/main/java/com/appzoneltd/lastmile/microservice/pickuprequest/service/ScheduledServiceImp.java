package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OrderStatus;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PackageJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.PackagePickupProducer;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.ResceduledWorkFlowBase;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledServiceImp {
    private final PickupRequestRepositoryImp requestRepository;
    private final RequestHistoryRepositoryImp historyRepositoryImp;
    private final ObjectMapper objectMapper;
    @Autowired
    private RequestHistoryJpaRepository requestHistoryJpaRepository;
    @Autowired
    private PickupRequestJpaRepository pickupRequestJpaRepository;
    @Autowired
    private PackageJpaRepository packageJpaRepository;
    @Autowired
    private PackagePickupProducer packagePickupProducer;
    

    @Autowired
    public ScheduledServiceImp(PickupRequestRepositoryImp requestRepository,
                               RequestHistoryRepositoryImp historyRepositoryImp) {
        this.requestRepository = requestRepository;
        this.historyRepositoryImp = historyRepositoryImp;
        objectMapper = new ObjectMapper();
    }

    public int updateScheduledRequest(PickupRequest scheduledRequest)
            throws ParseException, EntityNotFoundException, DuplicatedKeyException {
        int result = 0;

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = format.format(new Date());

        Date outputCurrentDate = format.parse(currentDate);

        String pickupDate = format.format(scheduledRequest.getPickupDate());
        Date outputPickupDate = format.parse(pickupDate);

        if (outputPickupDate.after(outputCurrentDate)) {
            if (!requestRepository.isAlreadyExistsWithoutStatus("lastmile_request.pickup_request", "pickup_request_id",
                    scheduledRequest.getPickupRequestId()))
                throw new EntityNotFoundException("Pickuprequest.request.notExist");

            // result =
            // requestRepository.updateScheduledPickupTimeAndDate(scheduledRequest.getTimeFrom(),
            // scheduledRequest.getTimeTo(), scheduledRequest.getPickupDate(),
            // scheduledRequest.getPickupRequestId());

            PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository
                    .findOne(scheduledRequest.getPickupRequestId());

            pickupRequestEntity.setTimeFrom(scheduledRequest.getTimeFrom());
            pickupRequestEntity.setTimeTo(scheduledRequest.getTimeTo());
            pickupRequestEntity.setPickupdate(scheduledRequest.getPickupDate());

            if (pickupRequestEntity.getRequestStatus().equalsIgnoreCase(OrderStatus.SCHEDULED_FOR_PICKUP.name())) {
            	pickupRequestEntity.setRequestStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
            }else if(pickupRequestEntity.getRequestStatus().equalsIgnoreCase(OrderStatus.RE_SCHEDULED_FOR_PICKUP.name())){
            	pickupRequestEntity.setRequestStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
            }
            else {
            	pickupRequestEntity.setRequestStatus(OrderStatus.SCHEDULED_FOR_PICKUP.getOrderStatus());
            }

            if (pickupRequestJpaRepository.save(pickupRequestEntity) != null) {
                result = 1;
            }

            List<RequestHistoryEntity> historyEntities = requestHistoryJpaRepository
                    .findByRequestId(scheduledRequest.getPickupRequestId());

            if (historyEntities != null && !historyEntities.isEmpty()) {
                PackageEntity packageEntity = historyEntities.get(0).getPackageEntity();
                if (historyEntities.get(0).getRequestStatus().equalsIgnoreCase(OrderStatus.SCHEDULED_FOR_PICKUP.name())) {
                	packageEntity.setStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
                }else if(historyEntities.get(0).getRequestStatus().equalsIgnoreCase(OrderStatus.RE_SCHEDULED_FOR_PICKUP.name())){
                	packageEntity.setStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
                }
                else {
                	packageEntity.setStatus(OrderStatus.SCHEDULED_FOR_PICKUP.getOrderStatus());
                }
               
                packageEntity = packageJpaRepository.save(packageEntity);

                RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
                requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
                if (historyEntities.get(0).getRequestStatus().equalsIgnoreCase(OrderStatus.SCHEDULED_FOR_PICKUP.name())) {
                	requestHistoryEntity.setRequestStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
                }else if(historyEntities.get(0).getRequestStatus().equalsIgnoreCase(OrderStatus.RE_SCHEDULED_FOR_PICKUP.name())){
                	requestHistoryEntity.setRequestStatus(OrderStatus.RE_SCHEDULED_FOR_PICKUP.getOrderStatus());
                }
                else {
                	requestHistoryEntity.setRequestStatus(OrderStatus.SCHEDULED_FOR_PICKUP.getOrderStatus());
                }
                requestHistoryEntity.setRequestType("PICKUP");
                requestHistoryEntity.setRequestId(scheduledRequest.getPickupRequestId());
                requestHistoryEntity.setCreated(new Date());
                requestHistoryEntity.setPackageEntity(packageEntity);
                requestHistoryEntity.setVersion(0L);

                requestHistoryJpaRepository.save(requestHistoryEntity);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> requestHistoryEntity >>>>>>>>>>>>>>");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> packageId >>>>>>>>>>>>>> "+requestHistoryEntity.getPackageEntity().getPackageId());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> requestId >>>>>>>>>>>>>> "+requestHistoryEntity.getRequestId());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> status >>>>>>>>>>>>>> "+requestHistoryEntity.getRequestStatus());
                if("RE_SCHEDULED_FOR_PICKUP".equalsIgnoreCase(requestHistoryEntity.getRequestStatus())){
                	ResceduledWorkFlowBase resceduledWorkFlowBase = new ResceduledWorkFlowBase();
                    if(requestHistoryEntity.getPackageEntity() !=null){
                    	resceduledWorkFlowBase.setPackageId(requestHistoryEntity.getPackageEntity().getPackageId());
                    }
                    resceduledWorkFlowBase.setRequestType(requestHistoryEntity.getRequestType());
                    resceduledWorkFlowBase.setRequestId(requestHistoryEntity.getRequestId());
                    resceduledWorkFlowBase.setRequestStatus(requestHistoryEntity.getRequestStatus());
                    resceduledWorkFlowBase.setSenderType("ADMIN");
                    try {
    					packagePickupProducer.sendMessage("SCHEDULED_PICKUP_REQUEST_NOTIFIER",
    							objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resceduledWorkFlowBase));
    				} catch (JsonProcessingException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
            }

        } else if (outputPickupDate.before(outputCurrentDate)) {
            throw new DuplicatedKeyException("scheduledRequest.Date.update", "requestername",
                    scheduledRequest.getPickupRequestId().toString());
        } else if (outputPickupDate.compareTo(outputCurrentDate) == 0) {
            throw new DuplicatedKeyException("scheduledRequest.Date.update", "requestername",
                    scheduledRequest.getPickupRequestId().toString());
        }

        return result;

    }

    /**
     * @param packageId
     * @param scheduledRequestId
     * @return
     * @throws EntityNotFoundException saving in History
     */
    public int cancelScheduledRequest(Long packageId, Long scheduledRequestId) throws EntityNotFoundException {
        int result = 0;
        if (!requestRepository.isAlreadyExistsWithoutStatus("lastmile_request.pickup_request", "pickup_request_id",
                scheduledRequestId))
            throw new EntityNotFoundException("Pickuprequest.request.notExist");

        final PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(scheduledRequestId);
        if (!OrderStatus.ACTION_NEEDED.name().equalsIgnoreCase(pickupRequestEntity.getRequestStatus()) && !OrderStatus.NEW.name().equalsIgnoreCase(pickupRequestEntity.getRequestStatus()))
            throw new EntityNotFoundException("request.cancellation.error");;

        result = requestRepository.cancelScheduledRequest(scheduledRequestId);
        if (result == 1)
            result = historyRepositoryImp.save(Utils.generateUUID(), packageId, OrderStatus.CANCELED.getOrderStatus(),
                    scheduledRequestId, "SCHEDULED");
        return result;
    }

    public List<PickupRequestInfo> findAllScheduledRequestsByPage(int page, int maxResult, OrderBy orderType,
                                                                  Long requestId, String requesterMobile, String fromRequestDateStr, String toRequestDateStr,
                                                                  String fromPickupDateStr, String toPickupDateStr) throws ParseException {
        final MyPrincipal myPrincipal = getAuthenticatedUser();
        return requestRepository.findAllScheduledRequestsByPage(page, maxResult, orderType, requestId, requesterMobile,
                fromRequestDateStr, toRequestDateStr, fromPickupDateStr, toPickupDateStr, myPrincipal.getHubs());
    }

    private MyPrincipal getAuthenticatedUser() {
        MyPrincipal myPrincipal = null;
        try {

            myPrincipal = objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPrincipal;
    }

    public int countAllScheduledRequest(Long requestId, String requesterMobile, String fromRequestDateStr,
                                        String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr) throws ParseException {
        final MyPrincipal myPrincipal = getAuthenticatedUser();
        return requestRepository.countAllScheduledRequest(requestId, requesterMobile, fromRequestDateStr,
                toRequestDateStr, fromPickupDateStr, toPickupDateStr, myPrincipal.getHubs());
    }

}
