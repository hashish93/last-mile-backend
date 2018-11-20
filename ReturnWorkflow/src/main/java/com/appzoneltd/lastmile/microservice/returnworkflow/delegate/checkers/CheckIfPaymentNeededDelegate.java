package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.checkers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckIfPaymentNeededDelegate implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Boolean paymentNeeded = true;
		execution.setVariable("paymentNeeded", paymentNeeded);

	}
}
