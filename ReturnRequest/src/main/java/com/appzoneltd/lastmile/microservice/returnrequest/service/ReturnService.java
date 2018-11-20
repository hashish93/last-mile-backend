package com.appzoneltd.lastmile.microservice.returnrequest.service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
public interface ReturnService {

	ReturnRequestEntity saveReturnRequest(ReturnSchedule returnSchedule) throws EntityNotFoundException;

	List<ReturnRequest> getReturnRequestsByPageAndOffset(PagingParameters pagingParameters, Principal principal);

	List<ReturnRequest> getReturnRequests(Sort sort, Principal principal);

	Integer getReturnRequestsCount(PagingParameters pagingParameters, Principal principal);

	Integer getReturnRequestsCountByPage(PagingParameters pagingParameters);

	ReturnRequestDetails getReturnRequestDetailsById(Long returnRequestId);

	ReturnRequestEntity rescheduleReturn(Long requestId, Date returnDate, String timeFrom, String timeTo)
			throws EntityNotFoundException, JsonProcessingException;

	List<RequestHistoryTimeLine> returnRequestTimeLine(Long requestId);

	List<ArchivedReturn> getArchivedReturnRequests(Principal principal, PagingParameters pagingParameters);

	List<ArchivedReturn> getArchivedReturnRequestsPagable(Principal principal, PagingParameters pagingParameters);

	Object[] getReturnStatus();

	Integer getArchiveReturnRequestsCount(Principal principal);
}
