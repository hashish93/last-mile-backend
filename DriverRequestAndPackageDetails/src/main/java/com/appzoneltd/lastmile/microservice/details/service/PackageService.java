package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dto.PackageDetails;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.Principal;
import java.util.Map;

public interface PackageService {

    Map<String, Object> startNavigation(Principal driver, Long requestId, String requestType) throws EntityNotFoundException, JsonProcessingException;

    PackageDetails findRequestAndPackageDetailsByPackageId(Principal principal, Long packageId, Long requestId, String requestType)
            throws EntityNotFoundException, JsonProcessingException;

    Long getUserId(String principal);

}
