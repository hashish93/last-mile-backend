package com.appzoneltd.lastmile.microservices.locationservice.service;

import com.appzoneltd.lastmile.microservices.locationservice.model.LocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hashish on 5/9/2017.
 */
@Service
public class LocationServiceImp implements LocationService {
    @Autowired
    GoogleGeoService googleGeoService;

    @Override
    public String getAddressFromLocation(LocationModel locationModel) throws Exception {

        String Address = googleGeoService.getAddressFromGoogleApi(locationModel);
        return Address;
    }
}
