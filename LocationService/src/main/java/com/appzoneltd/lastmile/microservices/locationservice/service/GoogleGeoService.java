package com.appzoneltd.lastmile.microservices.locationservice.service;

import com.appzoneltd.lastmile.microservices.locationservice.model.LocationModel;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

/**
 * Created by hashish on 5/9/2017.
 */
@Service
public class GoogleGeoService {

    final static String apiKey = "AIzaSyDnbEb9TL06ewYou6G_drCZitrdGhPuo3U";
    private GeoApiContext geoApiContext = new GeoApiContext().setApiKey(apiKey);

    public String getAddressFromGoogleApi(LocationModel locationModel) throws Exception {
        Double latitude = Double.parseDouble(locationModel.getLatitude());
        Double longitude = Double.parseDouble(locationModel.getLongitude());
        if(latitude > -90 && latitude < 90 && longitude > -180 && longitude <180){
            LatLng latLng = new LatLng(latitude , longitude);
            GeocodingResult[] results = GeocodingApi.reverseGeocode(geoApiContext,latLng).await();
            String result=null;
            if(results.length > 0){
                result = results[0].formattedAddress;
                System.out.println(results[0].formattedAddress);
            }
            return result;
        }
       return null;
    }
}
