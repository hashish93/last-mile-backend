package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;

@Service
public class SendGoExtraNotificationDelegate implements JavaDelegate {

	@Autowired
	private OnDemandNotificationService notificationService;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long requestId = (Long) execution.getVariable("requestId");
		Long packageId = (Long) execution.getVariable("packageId");
		List<Long> nearByVehicles=(List<Long>) execution.getVariable("nearByVehicles");
		
		PackageEntity packageEntity = packageRepository.findOne(packageId);
		PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
		if ((packageEntity != null) && (pickupRequestEntity != null)) {
			String formattedRequestWeight = packageEntity.getPackageType().getPackagetype() + "("
					+ packageEntity.getActualweight().intValue() + "Kg)";
			WorkflowNearByVehicles workflowNearByVehicles = new WorkflowNearByVehicles();
			workflowNearByVehicles.setPackageId(packageId);
			workflowNearByVehicles.setRequestId(requestId);
			workflowNearByVehicles.setRequestAddress(pickupRequestEntity.getPickupformatedaddress());
			workflowNearByVehicles.setRequestWeight(formattedRequestWeight);
			workflowNearByVehicles.setVehicles(nearByVehicles);
			workflowNearByVehicles.setPackageType(packageEntity.getPackageType().getPackagetype());
			MyPrinter.print("MODULE NOTIFIATION",workflowNearByVehicles.toString());
			notificationService.sendGoExtraNotification(workflowNearByVehicles);
		}
	}

}
