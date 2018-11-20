package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;


@Service
public class CheckPaymentAtDeliveryDelegate implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CheckPaymentAtDeliveryDelegate >>>>>>>>>>>>>>>>>>>>>>>>");
		Long packageId=(Long) execution.getVariable("packageId");
		
		System.out.println("Check payment at delivery to package "+packageId);
		
		boolean paymentTypeAtDelivery=deliveryRequestService.isPaymentAtDelivery(packageId);
		
		execution.setVariable("paymentAtDelivery", paymentTypeAtDelivery);
		
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> paymentAtDelivery >>>>>>>>>>>>>>>>>>>>>>>>" + paymentTypeAtDelivery);
	}

}