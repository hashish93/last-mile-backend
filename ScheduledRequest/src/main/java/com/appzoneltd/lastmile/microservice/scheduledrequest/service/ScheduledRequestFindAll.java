/**
 * Jun 15, 20161:57:12 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.scheduledrequest.service;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.core.Message;
import com.appzoneltd.lastmile.enums.MessageType;
import com.appzoneltd.lastmile.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.scheduledrequest.dao.ScheduledRequestDao;
import com.appzoneltd.lastmile.microservice.scheduledrequest.json.RequestParameters;
import com.appzoneltd.lastmile.microservice.scheduledrequest.model.ScheduledRequest;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class ScheduledRequestFindAll {
	/**
	 * Autowiring our Scheduled Requests DAO
	 * 
	 **/
	@Autowired
	private ScheduledRequestDao scheduledRequestDao;

	/**
	 * Injecting MessageSource Class to perform Localization Messages .
	 * 
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * Injecting Locale Class to get Current local .
	 * 
	 */
	@Autowired
	private Locale locale;

	/**
	 * @param companyId
	 * @param page
	 * @param pageSize
	 * @param OrderTypeStr
	 * @return
	 */
	
	
	
	 
	
//	@PreAuthorize("hasRole('ROLE_listonschedule')")
//	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<ScheduledRequest>> scheduledRequestFindAll(	@RequestBody RequestParameters  requestparameters) {
//
//		List<ScheduledRequest> scheduledRequests = null;
//		try {
//			if ("ASC".equalsIgnoreCase(requestparameters.getOrderTypeStr()))
//				scheduledRequests = scheduledRequestDao.findAllScheduledRequestsByPage(requestparameters.getCompanyId(), requestparameters.getPage(), requestparameters.getPagesize(),
//						OrderBy.ASC, requestparameters.getRequestId(), requestparameters.getRequesterMobile(), requestparameters.getFromRequestDate(), requestparameters.getToRequestDate(), requestparameters.getFromPickupDate(),
//						requestparameters.getToPickupDate());
//
//			if ("DESC".equalsIgnoreCase(requestparameters.getOrderTypeStr()))
//				scheduledRequests = scheduledRequestDao.findAllScheduledRequestsByPage(requestparameters.getCompanyId(), requestparameters.getPage(), requestparameters.getPagesize(),
//						OrderBy.DESC, requestparameters.getRequestId(), requestparameters.getRequesterMobile(), requestparameters.getFromRequestDate(), requestparameters.getToRequestDate(), requestparameters.getFromPickupDate(),
//						requestparameters.getToPickupDate());
//
//			if (scheduledRequests == null)
//				return new ResponseEntity<List<ScheduledRequest>>(scheduledRequests, HttpStatus.INTERNAL_SERVER_ERROR);
//		} catch (Exception e) {
//			return new ResponseEntity<List<ScheduledRequest>>(scheduledRequests, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<List<ScheduledRequest>>(scheduledRequests, HttpStatus.OK);
//	}

	/**
	 * Method scheduledRequestsCountAll to return count of all scheduledRequests
	 * request method POST
	 * 
	 * @throws ParseException
	 * 
	 * @params companyid
	 * 
	 * 
	 **/
//	@RequestMapping(value = "/findallcount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> shceudledRequestsCountAll(@RequestBody RequestParameters  requestparameters) throws ParseException {
//		Integer count = scheduledRequestDao.countAllScheduledRequest(requestparameters.getCompanyId(), requestparameters.getRequestId(), requestparameters.getRequesterMobile(),
//				requestparameters.getFromRequestDate(), requestparameters.getToRequestDate(), requestparameters.getFromPickupDate(), requestparameters.getToPickupDate());
//		return new ResponseEntity<Object>(
//				new Message(MessageType.SUCCESS, Integer.toString(count), messageSource.getMessage(
//						"schedledrequests.countall.success", null, "schedledrequests.countall.success", locale)),
//				HttpStatus.OK);
//	}
}
