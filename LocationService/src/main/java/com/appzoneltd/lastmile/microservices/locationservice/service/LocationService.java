package com.appzoneltd.lastmile.microservices.locationservice.service;

import com.appzoneltd.lastmile.microservices.locationservice.model.LocationModel;

/**
 * Created by hashish on 5/9/2017.
 */
public interface LocationService {
    String getAddressFromLocation(LocationModel locationModel) throws Exception;
}
