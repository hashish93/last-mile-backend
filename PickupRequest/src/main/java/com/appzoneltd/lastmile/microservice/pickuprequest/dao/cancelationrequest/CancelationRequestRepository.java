package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

public interface CancelationRequestRepository {

	String SQL_SELECT_ALL = "SELECT id, reason FROM lastmile_request.cancelation_reason ORDER BY id ASC";

	String INSERT_SQL = "INSERT INTO lastmile_request.request_cancellation( reason_id,request_id, description)VALUES (?, ?, ?)";

	String SELECT_REASON_BY_REQUEST_ID ="SELECT reason FROM lastmile_request.cancelation_reason INNER JOIN lastmile_request.request_cancellation ON cancelation_reason.id=request_cancellation.reason_id WHERE request_cancellation.request_id=? ";

}
