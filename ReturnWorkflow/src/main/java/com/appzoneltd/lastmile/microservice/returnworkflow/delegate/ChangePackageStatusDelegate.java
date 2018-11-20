package com.appzoneltd.lastmile.microservice.returnworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.PlanOrdersRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnStatus;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.kafka.models.ReturnPackageStatus;

@Service
public class ChangePackageStatusDelegate implements JavaDelegate {

	
	@Autowired
	private ReturnRequestService returnRequestService;
	
	@Autowired
	private RequestHistoryJpaRepository requestHistoryRepository;
	
	@Autowired
	private PlanOrdersRepository planOrdersRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long packageId = (Long) execution.getVariable("packageId");
		ReturnPackageStatus returnPackageStatus = new ReturnPackageStatus();
		returnPackageStatus.setPackageId(packageId);
		 Long returnId = requestHistoryRepository.getPackageReturnId(returnPackageStatus.getPackageId());
		 System.out.println("returnId "+returnId);
		String activityName = execution.getCurrentActivityName();
		if (activityName != null) {
			String packageStatus = activityName.substring(activityName.indexOf('"') + 1, activityName.lastIndexOf('"'))
					.trim();
			if (packageStatus.equals("New")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.NEW_RETURN.name());
			} else if (packageStatus.equals("Scheduled For Return")) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:>>>>>>>>>>>>>>>>>>>>>>>>>>");
				returnPackageStatus.setPackageStatus(ReturnStatus.SCHEDULED_FOR_RETURN.name());
			}else if (packageStatus.equals("Out for Return")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.OUT_FOR_RETURN.name());
			}else if (packageStatus.equals("Action Needed")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.ACTION_NEEDED.name());
			}else if (packageStatus.equals("In Return Verification")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.IN_RETURN_VERIFICATION.name());
			}else if (packageStatus.equals("Returned")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.RETURNED.name());
				if(returnId !=null){
					changePlanOrderToExcluded(returnId);
				}
			}else if (packageStatus.equals("ReScheduled For Return")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.RESCHEDULED_FOR_RETURN.name());
			} else if (packageStatus.equals("canceled")) {
				returnPackageStatus.setPackageStatus(ReturnStatus.CANCELED.name());
				if(returnId !=null){
					changePlanOrderToExcluded(returnId);
				}
			}
			// Change PackageStatus
			returnRequestService.changePackageStatus(returnPackageStatus);
		} 

		
	}
	
	private void changePlanOrderToExcluded(Long requestId){
		System.out.println("change plan order to excluded to order "+requestId);
    	PlanOrderEntity planOrderEntity = planOrdersRepository.findByReturnId(requestId);
		if (planOrderEntity != null) {
			System.out.println("planOrderEntity order Id "+planOrderEntity.getOrderId());
			planOrderEntity.setExcluded(true);
			planOrdersRepository.save(planOrderEntity);

		}
    }

}
