package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class MoocDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	System.out.println("************************");
    	System.out.println(">>>>>>>>>>>>>>>>>> mooc delegate");
    	System.out.println("************************");
    	System.out.println(">>> "+execution.getCurrentActivityName());

    }

}
