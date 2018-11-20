package com.appzoneltd.lastmile.microservice.returnrequest.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.ArchivedReturn;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.PagingParameters;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.RequestHistoryTimeLine;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.ReturnRequest;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.ReturnRequestDetails;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.ReturnSchedule;
import com.appzoneltd.lastmile.microservice.returnrequest.service.ReturnService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
@RestController
public class ReturnController {

	private final ReturnService returnService;
	private final MessageSource messageSource;

	@Autowired
	public ReturnController(ReturnService returnService, MessageSource messageSource) {
		this.returnService = returnService;
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/createReturn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createReturnRequest(@RequestBody ReturnSchedule returnSchedule)
			throws EntityNotFoundException {
		ReturnRequestEntity returnRequestEntity = returnService.saveReturnRequest(returnSchedule);
		if (returnRequestEntity == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(
					new Message(MessageType.SUCCESS,
							messageSource.getMessage("create.return.success", null, LocaleContextHolder.getLocale())),
					HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_listreturns')")
	@RequestMapping(value = "/viewReturns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReturnRequest>> getAllReturns(@RequestBody PagingParameters pagingParameters) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if (pagingParameters.getPage() <= 0)
			return new ResponseEntity<>(returnService.getReturnRequests(pagingParameters.getSort(), principal),
					HttpStatus.OK);
		else
			return new ResponseEntity<>(returnService.getReturnRequestsByPageAndOffset(pagingParameters, principal),
					HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_viewarchievedreturnrequests')")
	@RequestMapping(value = "/viewArchivedReturns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ArchivedReturn>> getAllArchivedReturn(@RequestBody PagingParameters pagingParameters) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if (pagingParameters.getPage() <= 0)
			return new ResponseEntity<>(returnService.getArchivedReturnRequests(principal, pagingParameters),
					HttpStatus.OK);
		else
			return new ResponseEntity<>(returnService.getArchivedReturnRequestsPagable(principal, pagingParameters),
					HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_listreturns')")
	@RequestMapping(value = "/countReturns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> countReturns(@RequestBody PagingParameters pagingParameters) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>(
				new Message(MessageType.SUCCESS,
						returnService.getReturnRequestsCount(pagingParameters, principal).toString(), null),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_viewarchievedreturnrequests')")
	@RequestMapping(value = "/countArchivedReturns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> countArchivedReturns() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>(
				new Message(MessageType.SUCCESS,
						returnService.getArchiveReturnRequestsCount(principal).toString(), null),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getReturnDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnRequestDetails> getReturnDetails(@RequestBody PagingParameters pagingParameters) {
		ReturnRequestDetails returnRequestDetails = returnService.getReturnRequestDetailsById(pagingParameters.getId());
		if (returnRequestDetails == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(returnRequestDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/rescheduleReturnRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> rescheduleReturnRequest(@RequestBody ReturnRequest returnRequest) {
		ReturnRequestEntity returnRequestEntity = null;
		try {
			returnRequestEntity = returnService.rescheduleReturn(returnRequest.getRequestId(),
					returnRequest.getReturnDate(), returnRequest.getReturnTimeFrom(), returnRequest.getReturnTimeTo());
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					new Message(MessageType.ERROR,
							messageSource.getMessage("request.notfound", null, LocaleContextHolder.getLocale())),
					HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		if (returnRequestEntity == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new Message(MessageType.SUCCESS,
				messageSource.getMessage("return.rescheduled.successfully", null, LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	// @PreAuthorize("hasRole('ROLE_historyrequest')")
	@RequestMapping(value = "/timeline", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RequestHistoryTimeLine>> timeLineFindAll(
			@RequestBody EndPointParameter requestParameter) {
		List<RequestHistoryTimeLine> timeLineData = returnService.returnRequestTimeLine(requestParameter.getId());
		return new ResponseEntity<>(timeLineData, HttpStatus.OK);

	}

	// @PreAuthorize("hasRole('ROLE_historyrequest')")
	@RequestMapping(value = "/getReturnStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getReturnStatus() {
		return new ResponseEntity<>(returnService.getReturnStatus(), HttpStatus.OK);

	}

}
