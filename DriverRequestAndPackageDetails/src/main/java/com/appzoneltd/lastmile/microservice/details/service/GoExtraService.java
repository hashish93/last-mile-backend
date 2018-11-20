package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dto.JobOrder;

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 5/18/2017.
 */
public interface GoExtraService {
    List<JobOrder> getUnPickedGoExtraOrders(Principal principal);

    boolean isActionNeededPackage(Long packageId);

    boolean isAcceptedPackage(Long packageId);

    Long getUserId(String name);
}
