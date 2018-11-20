package com.appzoneltd.lastmile.microservice.pickuphistory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.core.AbstractDao;
import com.appzoneltd.lastmile.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.pickuphistory.model.PickupHistory;


@Repository
public class PickupHistoryDaoImp extends AbstractDao implements PickupHistoryDao {
	
	public List<PickupHistory> findAllPickUpHistoryByPage(Long companyId, int pageCount, int pageNum, OrderBy orderType) {

		List<PickupHistory> pickupHistory = queryWithPaging(SELECT_ALL_PICKUP_HISTORY, companyId, new PickupHistory(), pageNum, pageCount,
				orderType);
		return pickupHistory;

	}
	
	
	public int countAllPickUpHistory(Long companyId){
		
		int count = count(COUNT_ALL_PICKUP_HISTORY, companyId);
		return count ;
		
		
	}
	
	
	
	
	
	
	
	
	

}
