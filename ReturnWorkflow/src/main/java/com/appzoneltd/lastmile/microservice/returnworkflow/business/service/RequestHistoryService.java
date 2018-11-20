package com.appzoneltd.lastmile.microservice.returnworkflow.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.RequestHistoryEntity;


@Service
public class RequestHistoryService {
	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;
	
	public Long getDeliveryIdForPackage(Long packageId){
		List<RequestHistoryEntity> requestHistoryEntities=requestHistoryJpaRepository.getRequestsForPackage(packageId, "DELIVERY");		
		if(requestHistoryEntities.size()>0){
			return requestHistoryEntities.get(0).getRequestId();
		}
		return null;
	}
	
	
	public Long getPickupIdForPackage(Long packageId){
		List<RequestHistoryEntity> requestHistoryEntities=requestHistoryJpaRepository.getRequestsForPackage(packageId, "PICKUP");		
		if(requestHistoryEntities.size()>0){
			return requestHistoryEntities.get(0).getRequestId();
		}
		return null;
	}
}
