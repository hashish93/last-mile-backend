package dummies;

public interface OnDemandRequestDaoInfo {
	public final static String request_type = "ON-DEMAND";

	public static final String SQL_SELECT_ALL = "select "
			+ "pickup.pickup_request_id pickup_request_id,"
			+ "pickup.pickupgooglelocation pickupgooglelocation , "
			+ "DATE_PART('day', current_timestamp - pickup.requestdate::timestamp) * 24 *60 + DATE_PART('hour', current_time - pickup.requesttime::time ) * 60 + DATE_PART('minute', current_time - pickup.requesttime::time ) as recievedfrom  ,  "
			+ "pickup.pickupformatedaddress pickupformatedaddress , "
			+ "status.color color , "
			+ "pickup.pickupstatus pickupstatus , "
			+ "package.packagedimension packagedimension , "
			+ "package.packagetype packagetype , "
			+ "package.actualweight actualweight "
			+ "from "
				+ "%SCHEMA%_request.pickup_request pickup , "
				+ "%SCHEMA%_request.request_status status , "
				+ "%SCHEMA%_core.package package "
			+ "where "
				+ "pickup.pickupstatus_id = status.id AND "
				+ "pickup.package_id = package.package_id AND "
				+ "pickup.isdeleted = 0 AND "
				+ "pickup.requesttype = '"+ request_type + "' "
			+ "order by pickup.created ";
	
	public static final String SELECT_BY_ID_SQL = "select "
			+ "pickup.pickup_request_id pickup_request_id , "
			+ "pickup.pickupgooglelocation pickupgooglelocation , "
			+ "DATE_PART('day', current_timestamp - pickup.requestdate::timestamp) * 24 *60 + DATE_PART('hour', current_time - pickup.requesttime::time ) * 60 + DATE_PART('minute', current_time - pickup.requesttime::time ) as recievedfrom  ,  "
			+ "pickup.pickupformatedaddress pickupformatedaddress , "
			+ "status.color color , "
			+ "pickup.pickupstatus pickupstatus , "
			+ "package.packagedimension packagedimension , "
			+ "package.packagetype packagetype , package.actualweight actualweight "
			+ "from "
				+ "%SCHEMA%_request.pickup_request pickup , "
				+ "%SCHEMA%_request.request_status status , "
				+ "%SCHEMA%_core.package package "
			+ "where "
			+ "pickup.pickupstatus_id = status.id AND "
			+ "pickup.package_id = package.package_id AND "
			+ "pickup.isdeleted=0 AND "
			+ "pickup.requesttype ='" + request_type + "' AND "
			+ "pickup_request_id= ";
	
	public static final String SQL_COUNT_ALL = "select "
			+ "count(pickup_request_id) "
			+ "from "
				+ "%SCHEMA%_request.pickup_request "
			+ "where "
			+ "isdeleted=0 AND "
			+ "requesttype ='"+ request_type + "' ";
	
	public static final String SQL_SELECT_UNTAKEN = "select "
			+ "pickup.pickup_request_id pickup_request_id , "
			+ "pickup.pickupgooglelocation pickupgooglelocation, "
			+ "DATE_PART('day', current_timestamp - pickup.requestdate::timestamp) * 24 *60 + DATE_PART('hour', current_time - pickup.requesttime::time ) * 60 + DATE_PART('minute', current_time - pickup.requesttime::time ) as recievedfrom  ,  "
			+ "pickup.pickupformatedaddress pickupformatedaddress , "
			+ "status.color color , "
			+ "pickup.pickupstatus pickupstatus , "
			+ "package.packagedimension packagedimension , "
			+ "package.packagetype packagetype , "
			+ "package.actualweight actualweight "
			+ "from "
				+ "%SCHEMA%_request.pickup_request pickup , "
				+ "%SCHEMA%_request.request_status status , "
				+ "%SCHEMA%_core.package package "
			+ "where "
			+ "pickup.pickupstatus_id = status.id AND "
			+ "pickup.package_id = package.package_id AND "
			+ "pickup.isdeleted=0 AND "
			+ "pickup.requesttype ='"+ request_type+ "' AND "
			+ "(pickup.pickupstatus_id != 5 AND pickup.pickupstatus_id != 2) "
			+ "order by  pickup.created "; // UNTAKEN
	
	public static final String SQL_SELECT_TAKEN = "select "
			+ "pickup.pickup_request_id pickup_request_id , "
			+ "pickup.pickupgooglelocation pickupgooglelocation , "
			+ "DATE_PART('day', current_date - pickup.requestdate::timestamp) * 24 *60 + DATE_PART('hour', current_time - pickup.requesttime::time ) * 60 + DATE_PART('minute', current_time - pickup.requesttime::time ) as recievedfrom  ,  "
			+ "pickup.pickupformatedaddress pickupformatedaddress , "
			+ "status.color color , "
			+ "pickup.pickupstatus pickupstatus , "
			+ "package.packagedimension packagedimension , "
			+ "package.packagetype packagetype , "
			+ "package.actualweight actualweight "
			+ "from "
				+ "%SCHEMA%_request.pickup_request pickup , "
				+ "%SCHEMA%_request.request_status status , "
				+ "%SCHEMA%_core.package package "
			+ "where "
			+ "pickup.pickupstatus_id = status.id AND "
			+ "pickup.package_id = package.package_id AND "
			+ "pickup.isdeleted=0 AND "
			+ "pickup.requesttype ='"+ request_type + "' AND "
			+ "(pickup.pickupstatus_id = 5 OR pickup.pickupstatus_id = 2)  "
			+ "order by pickup.created "; // GRABBED OR ASSIGNED

}