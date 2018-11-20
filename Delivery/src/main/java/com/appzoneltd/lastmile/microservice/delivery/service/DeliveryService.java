package com.appzoneltd.lastmile.microservice.delivery.service;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.delivery.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
public interface DeliveryService {

    List<DeliveryRequest> getDeliveryRequestsByPageAndOffset(PagingParameters pagingParameters, Principal principal);

    List<DeliveryRequest> getDeliveryRequests(PagingParameters pagingParameters, Principal principal);

    Integer getDeliveryRequestsCount(PagingParameters pagingParameters, Principal principal);

    Integer getDeliveryRequestsCountByPage(PagingParameters pagingParameters);

    DeliveryRequestDetails getDeliveryRequestDetailsById(Long deliveryRequestId);

    DeliveryRequestEntity rescheduleDelivery(Long requestId, Date deliveryDate, String timeFrom, String timeTo) throws EntityNotFoundException, JsonProcessingException;

    List<RequestHistoryTimeLine> deliveryRequestTimeLine(Long requestId);

    List<CustomerDeliveryRequest> getRequestForUser(Principal principal);

    Object[] getDeliveryStatus();

    Boolean checkTrackedPickupRequest(Long requestId);

    List<RequestHistoryDTO> fetchArchivedDeliveries(EndPointParameter requestParameter, Principal principal);

    long countArchivedDeliveries(Principal principal);

	Object[] getDeliveryStatusOnly();
}
