package com.appzoneltd.lastmile.microservice.returnworkflow.delegate;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;

@Service
public class ScheduleDateReturnRequestDelegate implements JavaDelegate {


	@Autowired
	private RequestHistoryJpaRepository requestHistoryRepository;
	@Autowired
	private ReturnRequestRepository returnRequestRepository;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long packageId=(Long) execution.getVariable("packageId");
		Date returnDate = (Date) execution.getVariable("returnDate");
		String Latitude = (String) execution.getVariable("returnLatitude");
		String longitude = (String) execution.getVariable("returnLongitude");
		System.out.println("package id >>>>>>>>>>>>>>>>>"+packageId);
		System.out.println("return date >>>>>>>>>>>>>>>"+returnDate);
		System.out.println("latitude >>>>>>>>>>>>>>>>>"+Latitude);
		System.out.println("longitude >>>>>>>>>>>>>>>"+longitude);
		
		if(packageId!=null){
			Long requestId = requestHistoryRepository.getPackagePickupId(packageId);
			if(requestId !=null){
				ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(requestId);
				if(returnRequestEntity !=null){
					returnRequestEntity.setReturnDate(returnDate);
					returnRequestEntity.setReturnLatitude(Latitude);
					returnRequestEntity.setReturnLongitude(longitude);
					returnRequestRepository.save(returnRequestEntity);
				}
					
				
			}
			
		}
	}

}
