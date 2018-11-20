/**
 * Jun 15, 20161:38:09 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.scheduledrequest.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.core.AbstractDao;
import com.appzoneltd.lastmile.enums.OrderBy;
import com.appzoneltd.lastmile.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.scheduledrequest.model.ScheduledRequest;

/**
 * @author alaa.nabil
 *
 */
@Repository
public class ScheduledRequestDao extends AbstractDao implements ScheduledRequestDaoInfc {

//	/** Method to view all Scheduled Requests in grid */
//	public List<ScheduledRequest> findAllScheduledRequest(Long companyId) {
//		List<ScheduledRequest> scheduledRequests = query(SCHEDULED_PICKUP_FIND_ALL_QUERY, companyId,
//				new ScheduledRequest());
//		return scheduledRequests;
//	}

	/**
	 * Method to view Scheduled Requests with paging and maximum rows
	 * 
	 * @throws ParseException
	 */
//	public List<ScheduledRequest> findAllScheduledRequestsByPage(Long companyId, int page, int maxResult,
//			OrderBy orderType, String requestId, String requesterMobile, String fromRequestDateStr,
//			String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr) throws ParseException {
//		StringBuilder sqlQuery = buildQuery(SCHEDULED_PICKUP_FIND_ALL_QUERY, requestId, requesterMobile,
//				fromRequestDateStr, toRequestDateStr, fromPickupDateStr, toPickupDateStr);
//		sqlQuery.append("ORDER BY request.created ");
//		List<ScheduledRequest> scheduledRequests = queryWithPaging(sqlQuery.toString(), companyId,
//				new ScheduledRequest(), page, maxResult, orderType);
//		return scheduledRequests;
//	}
//
//	private StringBuilder buildQuery(String query, String requestId, String requesterMobile, String fromRequestDateStr,
//			String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr) throws ParseException {
//		StringBuilder sqlQuery = new StringBuilder(query);
//
//		if (requestId != null && !requestId.equalsIgnoreCase("")) {
//			sqlQuery.append("AND pickup_request_id=");
//			sqlQuery.append(requestId);
//			sqlQuery.append(" ");
//		}
//
//		if (requesterMobile != null && !requesterMobile.equalsIgnoreCase("")) {
//			sqlQuery.append("AND requestermobile=");
//			sqlQuery.append("'");
//			sqlQuery.append(requesterMobile);
//			sqlQuery.append("'");
//			sqlQuery.append(" ");
//		}
//
//		if (fromRequestDateStr != null && !fromRequestDateStr.equalsIgnoreCase("")) {
//			sqlQuery.append("AND requestdate>=");
//			sqlQuery.append("'");
//			sqlQuery.append(fromRequestDateStr);
//			sqlQuery.append("'");
//			sqlQuery.append(" ");
//		}
//
//		if (toRequestDateStr != null && !toRequestDateStr.equalsIgnoreCase("")) {
//			sqlQuery.append("AND requestdate<=");
//			sqlQuery.append("'");
//			sqlQuery.append(toRequestDateStr);
//			sqlQuery.append("'");
//			sqlQuery.append(" ");
//		}
//
//		if (fromPickupDateStr != null && !fromPickupDateStr.equalsIgnoreCase("")) {
//			sqlQuery.append("AND pickupdate>=");
//			sqlQuery.append("'");
//			sqlQuery.append(fromPickupDateStr);
//			sqlQuery.append("'");
//			sqlQuery.append(" ");
//		}
//
//		if (toPickupDateStr != null && !toPickupDateStr.equalsIgnoreCase("")) {
//			sqlQuery.append("AND pickupdate<=");
//			sqlQuery.append("'");
//			sqlQuery.append(toPickupDateStr);
//			sqlQuery.append("'");
//			sqlQuery.append(" ");
//		}
//
//		return sqlQuery;
//	}

	/**
	 * Method to count all Scheduled Requests rows in database
	 * 
	 * @throws ParseException
	 */
//	public int countAllScheduledRequest(Long companyId, String requestId, String requesterMobile,
//			String fromRequestDateStr, String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr)
//			throws ParseException {
//		StringBuilder sqlQuery = buildQuery(SCHEDULED_PICKUP_FIND_ALL_COUNT_QUERY, requestId, requesterMobile,
//				fromRequestDateStr, toRequestDateStr, fromPickupDateStr, toPickupDateStr);
//
//		return count(sqlQuery.toString(), companyId);
//	}

	@Transactional
//	public int updateScheduledRequest(ScheduledRequest scheduledRequest)
//			throws DuplicatedKeyException, ParseException {
//		int result = 0;
//
//		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//		String currentDate = format.format(new Date());
//
//		Date outputCurrentDate = format.parse(currentDate);
//
//		String pickupDate = format.format(scheduledRequest.getPickupDate());
//		Date outputPickupDate = format.parse(pickupDate);
//
//		if (outputPickupDate.after(outputCurrentDate)) {
//			if (!isAlreadyExists(scheduledRequest.getCompanyId(), "request", "pickup_request", "pickup_request_id",
//					scheduledRequest.getPickupRequestId()))
//				throw new DuplicatedKeyException("Pickuprequest.request.notExist", "requestername",
//						scheduledRequest.getPickupRequestId().toString());
//
//			result = update(SCHEDULED_PICKUP_UPDATE_QUERY + scheduledRequest.getPickupRequestId(),
//					scheduledRequest.getCompanyId(), scheduledRequest.getPickupTime(),
//					scheduledRequest.getPickupDate());
//		}
//
//		else if (outputPickupDate.before(outputCurrentDate)) {
//			throw new DuplicatedKeyException("scheduledRequest.Date.update", "requestername",
//					scheduledRequest.getPickupRequestId().toString());
//		}
//
//		else if (outputPickupDate.compareTo(outputCurrentDate) == 0) {
//			throw new DuplicatedKeyException("scheduledRequest.Date.update", "requestername",
//					scheduledRequest.getPickupRequestId().toString());
//		}
//
//		return result;
//
//	}

//	@Transactional
//	public int cancelScheduledRequest(ScheduledRequest scheduledRequest) throws DuplicatedKeyException {
//		int result = 0;
//
//		if (!isAlreadyExists(scheduledRequest.getCompanyId(), "request", "pickup_request", "pickup_request_id",
//				scheduledRequest.getPickupRequestId()))
//			throw new DuplicatedKeyException("Pickuprequest.request.notExist", "pickup_request_id",
//					scheduledRequest.getPickupRequestId().toString());
//
//		result = update(SCHEDULED_PICKUP_CANCEL_QUERY + scheduledRequest.getPickupRequestId(),
//				scheduledRequest.getCompanyId());
//
//		return result;
//
//	}

}
