package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;

@Service
public class SaveScheduledPickupDateTimeDelegate implements JavaDelegate{

	@Autowired
	private PickupRequestRepository pickupRequestJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long requestId=(Long) execution.getVariable("requestId");
		Date pickupDate=(Date)execution.getVariable("pickupDate");
		String timeFrom=(String)execution.getVariable("from");
		String timeTo=(String)execution.getVariable("to");
		
		if(requestId !=null){
			PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(requestId);
			if(pickupRequestEntity !=null){
				pickupRequestEntity.setTimeFrom(timeFrom);
				pickupRequestEntity.setTimeTo(timeTo);
				pickupRequestEntity.setPickupdate(pickupDate);
				pickupRequestEntity.setPickRequestTypeId(2L);
				pickupRequestJpaRepository.save(pickupRequestEntity);
			}
		}
		
		
	}
	
	
	
}
