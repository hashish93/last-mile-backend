package com.appzoneltd.lastmile.microservice.workflow.delegate;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickUpHubDelegate implements JavaDelegate {

    @Autowired
    private WorkFlowProducer workFlowProducer;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(">> 3- Getting PickUp Hub Location According to PackageID");
        Long packageId = (Long) execution.getVariable("packageId");
        String requestType = (String) execution.getVariable("requestType");
        /// Getting PackageId
        WorkflowBase workflowBase = new WorkflowBase();
        workflowBase.setPackageId(packageId);
        workflowBase.setRequestType(requestType);

        System.out.println(">>  PackageID : " + packageId);

        // Sending Pickup request to Service
        workFlowProducer.sendMessage("CheckPackagePickupLocationRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
    }

}
