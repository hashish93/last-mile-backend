package com.appzoneltd.lastmile.microservice.workflow.delegate;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.DeliveryBase;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatus;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatusCouchbase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePackageStatusDelegate implements JavaDelegate {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WorkFlowProducer workFlowProducer;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Getting PackageId to Process it
        Long packageId = (Long) execution.getVariable("packageId");
        Long driverId = (Long) execution.getVariable("driverId");
        Long requestId = (Long) execution.getVariable("requestId");
        Boolean isWebUser = execution.getVariable("isWebUser") != null ? (Boolean) execution.getVariable("isWebUser") : false;
        Boolean isDriver = execution.getVariable("isDriver") != null ? (Boolean) execution.getVariable("isDriver") : false;
        System.out.println(">>>>>>>>>>>>>>>> ******************* ?????????????????????????????????????");
        System.out.println("DATA HERE : PACKAGE ID : " + packageId + "  , DRIVER ID :  " + driverId + " , REQUEST ID : " + requestId + ", ISWEBUSER :" + isWebUser);
        // Init Request
        WorkflowPackageStatus workflowPackageStatus = new WorkflowPackageStatus();
        workflowPackageStatus.setPackageId(packageId);
        workflowPackageStatus.setRequestType((String) execution.getVariable("requestType"));
        // Getting Service Label
        String activityName = execution.getCurrentActivityName();
        // Check if Activity Name Not Empty
        if (activityName != null) {
            String packageStatus = activityName.substring(activityName.indexOf('"') + 1, activityName.lastIndexOf('"'))
                    .trim();
            // Check the Wanted Status
            if (packageStatus.equals("New")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.NEW);

            }
            else if (packageStatus.equals("Waiting for Customer Alternative")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE);
                workflowPackageStatus.setRequestId(requestId);
                workflowPackageStatus.setRequesterId(execution.getVariable("requesterId") != null ? (Long) execution.getVariable("requesterId") : null);
                workflowPackageStatus.setIsWebUser(isWebUser);
            }
            else if (packageStatus.equals("Awaiting Pickup")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.AWAITING_PICKUP);
            } // end if-Else
            else if (packageStatus.equals("Action Needed")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.ACTION_NEEDED);
            } // end if-Else
            else if (packageStatus.equals("Pickup verification")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.IN_PICKUP_VERIFICATION);
                // Sending to Kafka
            } // end if-Else
            else if (packageStatus.equals("Pickuped up")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.PICKEDUP);
                // Send to CouchBase
                WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase = new WorkflowPackageStatusCouchbase();
                DeliveryBase deliveryBase=new DeliveryBase();
                deliveryBase.setPackageId(packageId);
                
                workflowPackageStatusCouchbase.setPackageId(packageId);
                workflowPackageStatusCouchbase.setDriverId(driverId);
                workflowPackageStatusCouchbase.setRequestId(requestId);
                workflowPackageStatusCouchbase.setStatus(ChangePackageStatusEnum.PICKEDUP.getPackageStatus());
                // Sending Kafka
                workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatusCouchbase));
                workFlowProducer.sendMessage("DELIVERY_WORKFLOW_START", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deliveryBase));
                // Sending to Kafka
            }//end if-Else
            else if (packageStatus.equals("Cancelled")) {
                /// gettign data
                Long userId = (Long) execution.getVariable("userId");
                boolean admin = (boolean) execution.getVariable("admin");
                /// Setting WorkFlowPackageStatus
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.CANCELED);
                workflowPackageStatus.setAdmin(admin);
                workflowPackageStatus.setUserId(userId);
                workflowPackageStatus.setIsDriver(isDriver);
                workflowPackageStatus.setDriverId(driverId);
                // Sending request To kafka
                WorkflowPackageStatusCouchbase couchbase = new WorkflowPackageStatusCouchbase();
                couchbase.setRequestId(requestId);
                couchbase.setStatus(ChangePackageStatusEnum.CANCELED.name());
                workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest",
                        mapper.writeValueAsString(couchbase));
            } // end if-Else
            else if (packageStatus.equalsIgnoreCase("Scheduled for pickup")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.SCHEDULED_FOR_PICKUP);
                workflowPackageStatus.setRequestId(execution.getVariable("requestId") == null ? null : (Long) execution.getVariable("requestId"));
                workflowPackageStatus.setRequesterId((Long) execution.getVariable("requesterId"));
                //workflowPackageStatus.setPickupDate(execution.getVariable("pickupDate") == null ? null : (Date) execution.getVariable("pickupDate"));
                workflowPackageStatus.setTimeFrom(execution.getVariable("from") == null ? null : (String) execution.getVariable("from"));
                workflowPackageStatus.setTimeTo(execution.getVariable("to") == null ? null : (String) execution.getVariable("to"));
                workflowPackageStatus.setRequestType(execution.getVariable("requestType") == null ? null : (String) execution.getVariable("requestType"));
                workflowPackageStatus.setIsWebUser(isWebUser);
            } else if (packageStatus.equals("Assigned")) {
                workflowPackageStatus.setStatus(ChangePackageStatusEnum.ASSIGNED);
                // Sending to Kafka
            }
            /// Sending ChangePackageStatusRequest
            workFlowProducer.sendMessage("ChangePackageStatusRequest",
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatus));
        } // end if Condition
    }// end Execute

}
