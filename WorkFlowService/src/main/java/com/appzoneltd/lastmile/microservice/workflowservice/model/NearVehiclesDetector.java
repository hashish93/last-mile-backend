package com.appzoneltd.lastmile.microservice.workflowservice.model;

import org.springframework.stereotype.Component;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageLocation;

@Component
public class NearVehiclesDetector {
    ///////////////////////////////////////////////////////////////////////////
	
    public Double getDistanceBetweenUs(WorkflowPackageLocation packageLocation,VehicleLocation vehicleLocation){
    	// Calculate the Distance Between the Origin Point and Vehicle Location
    	Double theDistance=distanceBetweenThem(packageLocation, vehicleLocation);
    	/// return result
    	return theDistance;
    } //end isNearVehicle
	
    private double distanceBetweenThem(WorkflowPackageLocation packageLocation,VehicleLocation vehicleLocation){
    	return distance(Double.parseDouble(packageLocation.getLatitude())
    			, Double.parseDouble(packageLocation.getLongitude())
    			, Double.parseDouble(vehicleLocation.getLatitude()), Double.parseDouble(vehicleLocation.getLongitude())
    			, 'K');
    }
    
	 private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }

	    
}
