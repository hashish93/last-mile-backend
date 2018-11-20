package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestTypeEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageDetailsDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageRequestDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageTimeLineDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PagingParameters;

import java.util.List;

public interface PackageRequestInfoService {

    List<PackageRequestDto> getPackageRequests(PagingParameters pagingParameters);

    List<PackageRequestDto> getAllPackageRequest(PagingParameters pagingParameters, List<Long> hubs);

    Object[] getRequestTypes();

    Object[] getPackageStatus();

    List<RequestTypeEntity> getRequestTypeStatus();

    List<PackageDetailsDto> getPackageRequestDetails(Long packageId);

    List<PackageTimeLineDto> getTimeLineForPackage(Long packageId);

}
