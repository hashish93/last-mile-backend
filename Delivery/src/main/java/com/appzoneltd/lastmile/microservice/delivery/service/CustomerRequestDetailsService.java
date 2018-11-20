package com.appzoneltd.lastmile.microservice.delivery.service;

import com.appzoneltd.lastmile.microservice.delivery.dto.CustomerRequestDetails;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
public interface CustomerRequestDetailsService {

    CustomerRequestDetails getRequestDetailsForCustomer(Long requestId);

}
