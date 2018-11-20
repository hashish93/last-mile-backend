package com.appzoneltd.lastmile.microservice.workflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class MoocDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" ***************************************************");
		System.out.println(" > > > MOOC ");
		System.out.println(" ***************************************************");
		System.out.println(" > >  "+execution.getCurrentActivityName());
		
	}
	
}
