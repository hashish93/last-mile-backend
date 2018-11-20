package com.appzoneltd.lastmile.microservice.pickuphistory.controller;

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
import com.appzoneltd.lastmile.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.pickuphistory.model.PickupHistory;
import com.appzoneltd.lastmile.microservice.pickuphistory.model.RequestParameter;
import com.appzoneltd.lastmile.microservice.pickuphistory.service.PickupHistoryFindAll;

@RestController
public class PickupHistoryFindAllController {
	
	
	
	@Autowired
	private PickupHistoryFindAll pickupHistoryFindAll ;
	
	@PreAuthorize("hasRole('ROLE_historyrequest')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PickupHistory>> pickupHistoryFindAll(@RequestBody RequestParameter requestParameter) {
		
		List<PickupHistory> PickupHistoryData=null;
		
		
		
		try {
			if ("ASC".equalsIgnoreCase(requestParameter.getOrderType()))
				PickupHistoryData = pickupHistoryFindAll.pickUpHistoryFindAll(requestParameter.getCompanyId(), requestParameter.getPageCount(), requestParameter.getPageNum(),  OrderBy.ASC);

			
			if ("DESC".equalsIgnoreCase(requestParameter.getOrderType()))
				PickupHistoryData = pickupHistoryFindAll.pickUpHistoryFindAll(requestParameter.getCompanyId(), requestParameter.getPageCount(), requestParameter.getPageNum(),  OrderBy.DESC);
			
			
			if (PickupHistoryData == null)		
				return new ResponseEntity<List<PickupHistory>>(PickupHistoryData, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
			catch (Exception e)	{
							
				return new ResponseEntity<List<PickupHistory>>(PickupHistoryData, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
			return new ResponseEntity<>(PickupHistoryData, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> countAllPickupHistory(@RequestBody RequestParameter requestParameter){
		
		Integer countAll=pickupHistoryFindAll.countAllPickUpHistory(requestParameter.getCompanyId());
		return new ResponseEntity<Object>(countAll,(HttpStatus.OK));
		
	}
	
	
	
	
	
	

}
