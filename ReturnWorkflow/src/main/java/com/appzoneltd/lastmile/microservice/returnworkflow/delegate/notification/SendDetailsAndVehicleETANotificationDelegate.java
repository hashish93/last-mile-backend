package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.service.ReturnPushNotificationService;



@Service
public class SendDetailsAndVehicleETANotificationDelegate implements JavaDelegate {

	@Autowired
	private ReturnPushNotificationService notificationService;
	
	@Autowired
	private ReturnRequestService returnRequestService;

	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> start SendDetailsAndVehicleETANotificationDelegate");
		Long packageId = (Long) execution.getVariable("packageId");
		Long receipientId = returnRequestService.getReceipentForPackage(packageId);
		Long driverId=(Long) execution.getVariable("driverId");
		Long requestId=requestHistoryJpaRepository.getPackageReturnId(packageId);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> start SendDetailsAndVehicleETANotificationDelegate");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> packageId"+packageId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> receipientId"+receipientId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> driverId"+driverId);
		if(receipientId!=null){

			NotificationParameter notificationParameter = new NotificationParameter();
			notificationParameter.setPackageId(packageId);
			notificationParameter.setCustomerId(receipientId);
			notificationParameter.setDriverId(driverId);
			notificationParameter.setRequestId(requestId);
		
			notificationService.sendOnHisWayNotification(notificationParameter);
			System.out.println("Notification send to user with ETA and Vehicle");
		}
	}

}