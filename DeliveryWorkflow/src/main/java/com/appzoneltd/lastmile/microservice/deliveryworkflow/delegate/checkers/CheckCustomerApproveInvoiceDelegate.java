package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Service
public class CheckCustomerApproveInvoiceDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		boolean customerApproveInvoice=(boolean)execution.getVariable("customerApproveInvoice");
		Long packageId = (Long) execution.getVariable("packageId");
		System.out.println("CheckCustomerApproveInvoiceDelegate >>>>>>>>>>>>>>>>>>>>>> ");
		System.out.println("packageId >>>>>>>>>>>>>>>>>>>>>> "+packageId);
		System.out.println("customerApproveInvoice >>>>>>>>>>>>>>>>>>>>>> "+customerApproveInvoice);
		execution.setVariable("customerApprove", customerApproveInvoice);
		execution.setVariable("packageId", packageId);

	}

}
