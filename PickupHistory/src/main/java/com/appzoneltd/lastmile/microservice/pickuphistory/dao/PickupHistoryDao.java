package com.appzoneltd.lastmile.microservice.pickuphistory.dao;

public interface PickupHistoryDao {

	
	String SELECT_ALL_PICKUP_HISTORY="SELECT pickup_request_id, requesttype, pickupstatus, packagetype FROM %SCHEMA%_request.pickup_request request  INNER JOIN %SCHEMA%_core.package package ON package.package_id=request.package_id WHERE request.isdeleted=0 AND (pickupstatus='Success' OR pickupstatus='Cancelled') ORDER BY request.created ";
	
	String COUNT_ALL_PICKUP_HISTORY="SELECT COUNT(pickup_request_id) FROM %SCHEMA%_request.pickup_request request INNER JOIN %SCHEMA%_core.package ON package.package_id=request.package_id WHERE request.isdeleted=0 AND (pickupstatus='Success' OR pickupstatus='Cancelled')";
	
	
}
