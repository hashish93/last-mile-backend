package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Service
public class CheckCustomerApproveInvoiceDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(">>>>>>>>>>>> CheckCustomerApproveInvoiceDelegate");
		Long packageId=(Long)execution.getVariable("packageId");
		boolean customerApproveInvoice=(boolean)execution.getVariable("customerApproveInvoice");
		System.out.println(">>>>>>>>>>>> customerApproveInvoice"+customerApproveInvoice);
		execution.setVariable("customerApproveInvoice", customerApproveInvoice);
		execution.setVariable("packageId", packageId);

	}

}
