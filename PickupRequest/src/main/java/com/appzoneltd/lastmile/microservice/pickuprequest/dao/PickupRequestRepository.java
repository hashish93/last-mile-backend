/*
 * Created on 7 Nov 2016 ( Time 13:02:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

public interface PickupRequestRepository {

	String SQL_SELECT_BY_ID = "SELECT DISTINCT pickup_request_id, request_history.package_id, pickup_request.pickup_request_type_id, "
			+ "pickup_request_type.type, requesttime, requester_id, users.username, users.phone, pickuplongitude, "
			+ "pickuplatitude, pickupwasellocation, pickupformatedaddress, time_from, time_to, pickupdate, recipient_id, recipientname, "
			+ "recipientmobile, recipientlongitude, recipientlatitude, recipientwasellocation, recipientformatedaddress, recipientadditionalinfo, "
			+ "additionalservices, labelingtext, paymenttype, pickup_request.request_status, paymentmethod, pickup_request.description, "
			+ "pickup_request.version, pickup_request.created, actualweight, packagedimension, packagetype, package.package_value ,hub_Id ,building.buildingname, "
			+ "pickup_request.cancellation_reason ,in_plan.active_vehicle_id "
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ "INNER JOIN lastmile_core.building ON building.building_id=pickup_request.hub_id "
			+ "left outer join (select plan_order.order_id as order_id , plan_order.active_vehicle_id  from lastmile_plan.plan_order plan_order , lastmile_plan.plan "
			+ "where plan_order.plan_id = lastmile_plan.plan.id and lastmile_plan.plan.created::date = now()::date) in_plan on pickup_request_id = in_plan.order_id "
			+ " WHERE pickup_request_id = ? ";

	String SQL_INSERT = "INSERT into lastmile_request.pickup_request ( pickup_request_id, pickup_request_type_id, requesttime, requester_id, pickuplongitude, pickuplatitude, pickupwasellocation, pickupformatedaddress, time_from, time_to, pickupdate, recipient_id, recipientname, recipientmobile, recipientlongitude, recipientlatitude, recipientwasellocation, recipientformatedaddress, recipientadditionalinfo, additionalservices, labelingtext, request_status, paymenttype, paymentmethod, description , countrycode ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? )";
	String DELETE_PICKUP_REQUEST = "DELETE FROM lastmile_request.pickup_request WHERE pickup_request_id=?";

	String SQL_COUNT_ALL_BY_REQUEST_TYPE = "SELECT COUNT(DISTINCT pickup_request_id)"
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ " WHERE pickup_request.pickup_request_type_id =:type AND pickup_request.hub_id IN (:hubs) ";

	String SQL_SELECT_ALL_BY_REQUEST_TYPE = "SELECT DISTINCT pickup_request_id, request_history.package_id,pickup_request.pickup_request_type_id, pickup_request_type.type, requesttime, requester_id, users.username, users.phone, pickuplongitude, pickuplatitude, pickupwasellocation, pickupformatedaddress, time_from, time_to, pickupdate, recipient_id, recipientname, recipientmobile, recipientlongitude, recipientlatitude, recipientwasellocation, recipientformatedaddress, recipientadditionalinfo, additionalservices, labelingtext, paymenttype, pickup_request.request_status, paymentmethod, pickup_request.description, pickup_request.version, pickup_request.created, actualweight, packagedimension, packagetype, package.package_value, hub_id,building.buildingname , pickup_request.cancellation_reason "
			+ ",in_plan.active_vehicle_id "
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ "INNER JOIN lastmile_core.building ON building.building_id=pickup_request.hub_id "
			+ "left outer join (select plan_order.order_id as order_id , plan_order.active_vehicle_id  from lastmile_plan.plan_order plan_order , lastmile_plan.plan "
			+ "where plan_order.plan_id = lastmile_plan.plan.id and lastmile_plan.plan.created::date = now()::date) in_plan on pickup_request_id = in_plan.order_id "
			+ " WHERE pickup_request.pickup_request_type_id =:type "
			+ " AND pickup_request.pickup_request_type_id =:type AND pickup_request.hub_id IN (:hubs) ";

	String SQL_SELECT_ALL_HISTORY_BY_REQUEST_TYPE = "SELECT DISTINCT pickup_request_id, request_history.package_id ,pickup_request.pickup_request_type_id, pickup_request_type.type, requesttime, requester_id, users.username, users.phone, pickuplongitude, pickuplatitude, pickupwasellocation, pickupformatedaddress, time_from, time_to, pickupdate, recipient_id, recipientname, recipientmobile, recipientlongitude, recipientlatitude, recipientwasellocation, recipientformatedaddress, recipientadditionalinfo, additionalservices, labelingtext, paymenttype, pickup_request.request_status, paymentmethod, pickup_request.description, pickup_request.version, pickup_request.created, actualweight, packagedimension, packagetype, package.package_value, hub_id , pickup_request.cancellation_reason , b.buildingname "
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ "INNER JOIN lastmile_core.building b  ON pickup_request.hub_id=b.building_id "
			+ " WHERE (pickup_request.pickup_request_type_id =:type1  "
			+ " OR pickup_request.pickup_request_type_id =:type2) AND pickup_request.hub_id IN (:hubs)"
			+ " AND (pickup_request.request_status =:status1 OR pickup_request.request_status =:status2) "
			+ " ORDER BY pickup_request.created ";

	String SQL_COUNT_ALL_HISTORY_BY_REQUEST_TYPE = "SELECT COUNT(DISTINCT pickup_request_id)"
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ " WHERE (pickup_request.pickup_request_type_id =:type1 OR pickup_request.pickup_request_type_id =:type2) AND pickup_request.hub_id IN (:hubs) AND (pickup_request.request_status =:status1 OR pickup_request.request_status =:status2) ";

	String UPDATE_SCHEDUED_REQUEST_PICKUP_DATE = "UPDATE lastmile_request.pickup_request SET time_from=?, time_to=?, pickupdate=? WHERE pickup_request_id=?";
	String CANCEL_SCHEDULED_REQUEST = "UPDATE lastmile_request.pickup_request SET request_status=? WHERE pickup_request_id=?";

	String SQL_SELECT_REQUESTS_FOR_USER = "SELECT DISTINCT pickup_request_id, pickup_request_type.type AS pickupRequestType, package.nickname AS nickname, recipientname AS recipientName, recipientformatedaddress AS recipientAddress, pickup_request.request_status AS status,request_history.request_type AS requesttype, pickuplongitude AS longitude, pickuplatitude AS latitude "
			+ ", lastmile_core.package.package_id as package_id , lastmile_core.package.description as package_description "
			+ ", hub_id "
			+ "FROM lastmile_request.pickup_request "
			+ "INNER JOIN lastmile_request.pickup_request_type ON pickup_request_type.pickup_request_type_id = pickup_request.pickup_request_type_id "
			+ "INNER JOIN lastmile_authorization_server.users ON users.user_id = pickup_request.requester_id "
			+ "INNER JOIN lastmile_request.request_history ON pickup_request.pickup_request_id=request_history.request_id AND pickup_request.request_status=request_history.request_status "
			+ "INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ "INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ " WHERE pickup_request.requester_id = ? ";

	String SQL_SELECT_DELIVERY_REQUESTS_FOR_USER = "SELECT DISTINCT delivery_request_id as pickup_request_id, package.nickname AS nickname, recipientname AS recipientName, recipientformatedaddress AS recipientAddress,delivery_request.request_status AS status,request_history.request_type AS requesttype, pickuplongitude AS longitude, pickuplatitude AS latitude, confirmationcode "
			+ ", lastmile_core.package.package_id as package_id , lastmile_core.package.description as package_description "
			+ " FROM lastmile_request.delivery_request "
			+ " INNER JOIN lastmile_authorization_server.users ON users.user_id = delivery_request.requester_id "
			+ " INNER JOIN lastmile_request.request_history ON delivery_request.delivery_request_id=request_history.request_id AND delivery_request.request_status=request_history.request_status "
			+ " INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ " INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ " WHERE delivery_request.recipient_id = ? ";
	
	String SQL_SELECT_RETURN_REQUESTS_FOR_USER = "SELECT DISTINCT return_request_id as pickup_request_id, package.nickname AS nickname, recipientname AS recipientName, "
			+ "return_formatted_address AS recipientAddress,return_request.request_status AS status, request_history.request_type AS requesttype, "
			+ "return_longitude AS longitude, return_latitude AS latitude "
			+ ", lastmile_core.package.package_id as package_id , lastmile_core.package.description as package_description "
			+ " FROM lastmile_request.return_request "
			+ " INNER JOIN lastmile_authorization_server.users ON users.user_id = return_request.requester_id "
			+ " INNER JOIN lastmile_request.request_history ON return_request.return_request_id=request_history.request_id AND return_request.request_status=request_history.request_status "
			+ " INNER JOIN lastmile_core.package ON request_history.package_id=package.package_id "
			+ " INNER JOIN lastmile_core.package_type ON package_type.package_type_id=package.package_type_id "
			+ " WHERE return_request.requester_id = ? ";
	
	
	String SELECT_COUNT_REQUESTS_WITH_SPECIFIC_STATUSES = "SELECT COUNT(DISTINCT pickup_request_id) "
			+ "FROM lastmile_request.pickup_request "
			+ "WHERE (pickup_request.request_status ='AWAITING_PICKUP' OR pickup_request.request_status ='IN_PICKUP_VERIFICATION') AND pickup_request_id=? ";

	// String PICKUP_REQUEST_FIND_ALL_TODAY_PICKUPS = "SELECT pickup_request_id,
	// pickup_request_type_id, pickup_request_type.type, requesttime,
	// requester_id,
	// users.username, users.phone, pickuplongitude, pickuplatitude,
	// pickupwasellocation, pickupformatedaddress, time_from, time_to,
	// pickupdate, recipient_id, recipientname, recipientmobile,
	// recipientlongitude, recipientlatitude, recipientwasellocation,
	// recipientformatedaddress, recipientadditionalinfo, additionalservices,
	// labelingtext, paymenttype, paymentmethod, request_status, description,
	// version, created "
	// + "FROM lastmile_request.pickup_request "
	// + "INNER JOIN lastmile_request.pickup_request_type ON
	// pickup_request_type.pickup_request_type_id =
	// pickup_request.pickup_request_type_id "
	// + "INNER JOIN lastmile_authorization_server.users ON users.user_id =
	// pickup_request.requester_id "
	// + "WHERE pickupdate = current_date ";

	String SELECT_PICKUP_TYPE_ID = "SELECT pickup_request_type_id FROM lastmile_request.pickup_request_type WHERE type LIKE ?";

	String SELECT_PACKAGE_REQUEST_LOCATION = "select pickupRequest.pickuplatitude ,pickupRequest.pickuplongitude FROM lastmile_request.pickup_request pickupRequest inner join lastmile_request.request_history requestHistory on pickupRequest.pickup_request_id = requestHistory.request_id where requestHistory.package_id = ? order by requestHistory.created DESC";

	String SELECT_PACKAGE_REQUEST_INFO = "select pickupRequest.requester_id , pickupRequest.pickupformatedaddress ,pickupRequest.pickup_request_id FROM lastmile_request.pickup_request pickupRequest inner join lastmile_request.request_history requestHistory on pickupRequest.pickup_request_id = requestHistory.request_id where requestHistory.package_id = ? order by requestHistory.created ";

	String UPDATE_REQUEST_STATUS = "UPDATE lastmile_request.pickup_request SET request_status=? WHERE pickup_request_id=?";

	String UPDATE_REQUEST_STATUS_WITH_REASON= "UPDATE lastmile_request.pickup_request SET request_status=? , cancellation_reason =? WHERE pickup_request_id=?";
	
	String SELECT_REQUEST_BY_PACKAGE_ID = "select pickupRequest.pickup_request_id ,pickupRequest.requester_id, requestHistory.request_type ,pickupRequest.hub_id FROM lastmile_request.pickup_request pickupRequest inner join lastmile_request.request_history requestHistory on pickupRequest.pickup_request_id = requestHistory.request_id where requestHistory.package_id = ? order by requestHistory.created DESC";

	String ASSIGN_PACKAGE_TO_HUB = "UPDATE lastmile_request.pickup_request SET hub_id=? WHERE pickup_request_id=?";

	String SELECT_PACKAGE_ORDER_INFO = "SELECT pickupRequest.pickup_request_id,pickupRequest.recipientname,"
			+ " pickupRequest.recipientmobile,pickupRequest.request_status,pickupRequest.pickuplatitude,"
			+ " pickupRequest.pickuplongitude,packages.actualweight,requestHistory.request_type,"
			+ " users.user_id,users.phone ,users.username FROM lastmile_request.pickup_request pickupRequest"
			+ " INNER JOIN lastmile_request.request_history requestHistory"
			+ " ON pickupRequest.pickup_request_id = requestHistory.request_id"
			+ " INNER JOIN lastmile_core.package packages" + " ON requestHistory.package_id = packages.package_id"
			+ " INNER JOIN lastmile_authorization_server.users users" + " ON pickupRequest.requester_id = users.user_id"
			+ " WHERE requestHistory.package_id = ?" + " ORDER BY requestHistory.created DESC LIMIT 1";

	String SELECT_REQUEST_ID_BY_PACKAGE_ID = "SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE package_id= ?";

	String INSERT_INTO_SERVICE_RATING = "INSERT INTO lastmile_request.service_rating(service_rating_id, request_id, request_type, rating) VALUES (?, ?, ?, ?)";

	String INSERT_INTO_DRIVER_RATING = "INSERT INTO lastmile_request.driver_rating( driver_rating_id, driver_id, request_id, request_type, rating)VALUES (?, ?, ?, ?, ?)";

	String COUNT_NUMBER_OF_RATING_DRIVER = "SELECT COUNT (*) driver_id FROM lastmile_request.driver_rating WHERE driver_id=? ";

	String SUM_RATING_FOR_DRIVER = "SELECT SUM (rating) FROM lastmile_request.driver_rating WHERE driver_id=? ";

	String UPDATE_DRIVER_RATING = "UPDATE lastmile_core.driver SET rating=? WHERE id=?";

	String CANCEL_REQUEST = "UPDATE lastmile_request.pickup_request SET request_status=? WHERE pickup_request_id=?";
	
	String UPDATE_FREELANCER_DRIVER_RATING = "UPDATE lastmile_core.freelancer_driver SET rating=? WHERE id=?";

}
