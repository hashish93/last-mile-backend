package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PlanOrdersRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryStatus;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models.DeliveryPackageStatus;

@Service
public class ChangePackageStatusDelegate implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;
	
	@Autowired
	private PlanOrdersRepository planOrdersRepository;
	
	@Autowired
	private RequestHistoryJpaRepository requestHistoryRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long packageId = (Long) execution.getVariable("packageId");
		DeliveryPackageStatus deliveryPackageStatus = new DeliveryPackageStatus();
		deliveryPackageStatus.setPackageId(packageId);
		Long deliveryId = requestHistoryRepository.getPackageDeliveryId(deliveryPackageStatus.getPackageId());
		
		String activityName = execution.getCurrentActivityName();
		if (activityName != null) {
			String packageStatus = activityName.substring(activityName.indexOf('"') + 1, activityName.lastIndexOf('"'))
					.trim();
			System.out.println("change package status to " + packageStatus);
			 
			 if (packageStatus.equals("Scheduled For Delivery")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.SCHEDULED_FOR_DELIVERY.name());
			}else if (packageStatus.equals("Out for Delivery")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.OUT_FOR_DELIVERY.name());
			}else if (packageStatus.equals("Action Needed")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.ACTION_NEEDED.name());
			}else if (packageStatus.equals("In Delivery Verification")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.IN_DELIVERY_VERIFICATION.name());
			}else if (packageStatus.equals("Delivered")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.DELIVERED.name());
				if(deliveryId !=null){
					changePlanOrderToExcluded(deliveryId);
				}
			}else if (packageStatus.equals("Canceled Delivery")) {
				deliveryPackageStatus.setPackageStatus(DeliveryStatus.CANCELED_DELIVERY.name());
				if(deliveryId !=null){
					changePlanOrderToExcluded(deliveryId);
				}
			}
			 
			// Change PackageStatus
			 System.out.println("call package status change function  " +packageStatus);
			deliveryRequestService.changePackageStatus(deliveryPackageStatus);
		} 

		
	}
	
	
	  private void changePlanOrderToExcluded(Long requestId){
		  System.out.println("@#@#@#@#@#@#@#@# >>> inside changePlanOrderToExcluded():: for IRDER::: "+requestId);
		  
	    	PlanOrderEntity planOrderEntity = planOrdersRepository.findByDeliveryId(requestId);
	    	
	    	System.out.println("planOrderEntity FOUND !!!!!!!!!!!!!!!!!!!");
	    	
			if (planOrderEntity != null) {
				planOrderEntity.setExcluded(true);
				planOrdersRepository.save(planOrderEntity);
				System.out.println("after saving the planOrderEntity");
			}
			System.out.println("planOrderEntity NOT execluded!!!!!!!!! WTFFFFFFFFFFFF");
	    }

}
