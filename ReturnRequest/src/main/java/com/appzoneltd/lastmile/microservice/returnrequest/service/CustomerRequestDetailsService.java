package com.appzoneltd.lastmile.microservice.returnrequest.service;

import com.appzoneltd.lastmile.microservice.returnrequest.dto.CustomerRequestDetails;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
public interface CustomerRequestDetailsService {

    CustomerRequestDetails getRequestDetailsForCustomer(Long requestId);
    
    boolean checkTrackedPickupRequest(Long requestId);

}
