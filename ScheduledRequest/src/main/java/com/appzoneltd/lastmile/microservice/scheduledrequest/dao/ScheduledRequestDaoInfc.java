package com.appzoneltd.lastmile.microservice.scheduledrequest.dao;

public interface ScheduledRequestDaoInfc {
	public static final String SCHEDULED_PICKUP_FIND_ALL_QUERY = "SELECT pickup_request_id, requestermobile, pickupdate, requestdate,pickuptime,pickupformatedaddress, package.packagetype FROM %SCHEMA%_request.pickup_request request INNER JOIN %SCHEMA%_core.package ON package.package_id=request.package_id WHERE request.isdeleted=0 AND  (requesttype='SCHEDULED' OR pickuptime<>'NOW') ";
	public static final String SCHEDULED_PICKUP_FIND_ALL_COUNT_QUERY = "SELECT COUNT(pickup_request_id) FROM %SCHEMA%_request.pickup_request request INNER JOIN %SCHEMA%_core.package ON package.package_id=request.package_id WHERE request.isdeleted=0 AND (requesttype='SCHEDULED' OR pickuptime<>'NOW') ";

	public final static String SCHEDULED_PICKUP_UPDATE_QUERY = "UPDATE %SCHEMA%_request.pickup_request SET  pickuptime=?, pickupdate=? WHERE pickup_request_id= ";
	

}
