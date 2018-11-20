package com.appzoneltd.lastmile.microservice.returnworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SetPackageToDisposableDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long packageId=(Long) execution.getVariable("packageId");
		System.out.println(">>>>>>>>>>>>>>>> package id "+packageId);
		System.out.println(">>>>>>>>>>>>>>>> mark package as Disposable");
		
	}

}
