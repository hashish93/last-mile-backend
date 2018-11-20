package com.appzoneltd.lastmile.microservice.pickuphistory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.core.Service;
import com.appzoneltd.lastmile.enums.OrderBy;

import com.appzoneltd.lastmile.microservice.pickuphistory.dao.PickupHistoryDaoImp;
import com.appzoneltd.lastmile.microservice.pickuphistory.model.PickupHistory;

@RestController
public class PickupHistoryFindAll extends Service{
	
Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private PickupHistoryDaoImp pickupHistoryDaoImp ;
	
	public List<PickupHistory> pickUpHistoryFindAll(long companyId, int pageCount, int pageNum, OrderBy orderType) {

		List<PickupHistory> pickUpHistory = null;
		pickUpHistory = pickupHistoryDaoImp.findAllPickUpHistoryByPage(companyId, pageCount, pageNum, orderType);
		return pickUpHistory;

	}
	
	
	public int countAllPickUpHistory (Long companyId){
		
		return pickupHistoryDaoImp.countAllPickUpHistory(companyId);
	}
	

}
