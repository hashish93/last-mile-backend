package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
public interface LoadingOrdersService {

    JobOrdersRoute getJobOrdersRoute(Principal principal) throws Exception;

    List<JobOrder> getLoadingActivitiesForToday(Principal principal) throws Exception;

    Boolean responseLoadingOrder(DriverResponseDto driverResponseDto, Principal principal) throws Exception;

    List<JobOrder> getJobOrders(Principal principal);

    RequestDetails getJobOrderDetails(Long requestId, String requestType) throws EntityNotFoundException;

    TodaySummary calculateTodaySummary(Principal principal) throws EntityNotFoundException;
    
    void startWorkingToActiveVehicle(Principal principal) throws EntityNotFoundException;
}
