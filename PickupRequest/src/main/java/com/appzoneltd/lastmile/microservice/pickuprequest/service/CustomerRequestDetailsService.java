package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.pickuprequest.dto.CustomerRequestDetails;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.DriverInfo;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
public interface CustomerRequestDetailsService {

    CustomerRequestDetails getRequestDetailsForCustomer(Long requestId);

    CustomerRequestDetails tracePackageWithTrackingNumberOrNickname(String identifier, String principal);

    DriverInfo getDriverInfo(Long requestId);
}
