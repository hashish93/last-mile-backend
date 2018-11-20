package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatus;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class ChangePackageStatusConsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RuntimeService runTimeService;

    // private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "ChangePackageStatusResponse", group = "ChangePackageStatusResponse")
    public void receiveMessage(@Payload String payload)
            throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        //// Getting kafka Info
        WorkflowPackageStatus workflowPackageStatus = mapper.readValue(payload, WorkflowPackageStatus.class);
        /// generate Map to Complete Flow
        Map<String, Object> workflowData = new HashMap<String, Object>();
        workflowData.put("packageId", workflowPackageStatus.getPackageId());
        workflowData.put("requestType", workflowPackageStatus.getRequestType());
        // Getting Process Instance Algorithm with PackageId
        ProcessInstance processInstance = null;
        int timer = 0;
        // Wait Until Data Persisted
        while (timer < 10000) {
            // Query the Process Instance
            List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
                    variableValueEquals("packageId", workflowPackageStatus.getPackageId()).list();
            if (processInstances != null && !processInstances.isEmpty())
                processInstance = processInstances.get(0);
            if (processInstance != null) {
                break;
            } else {
                Thread.sleep(100L);
                timer += 100;
            }//end if-Else Block
        }//end while
        ////////////////////////////////////////////////////////
        System.out.println(">>> PROCESS INSTANCE RETURNED IN " + timer + " MS");
        System.out.println(">> processInstance.getProcessInstanceId() " + processInstance.getProcessInstanceId());

        Execution execution = null;
        timer = 0;
        // Response With Status
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.NEW)) {
            // Wait Until Data Persisted
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("waitAssingPackageAsNew").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            // Send Signal
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition
        // Check if Status Waiting_For_Customer_Alternatives
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE) && !workflowPackageStatus.getIsWebUser()) {
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("waitAssingPackageAsWaitingForCustomer").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE) && workflowPackageStatus.getIsWebUser()) {
            workflowData.put("requestId", workflowPackageStatus.getRequestId());
            workflowData.put("requesterId", workflowPackageStatus.getRequesterId());
            workflowData.put("isWebUser", workflowPackageStatus.getIsWebUser());
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitCustomerAlternative").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition

        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.AWAITING_PICKUP)) {
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitForAssingPackageAsAWaitingPickup").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.ACTION_NEEDED)) {
//			Execution execution = runTimeService.createExecutionQuery()
//					.processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitForAssingPackageAsActionNeeded").singleResult();
//			runTimeService.signal(execution.getId(),workflowData);
        }//end if Condition
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.CANCELED)) {

            workflowData.put("admin", workflowPackageStatus.isAdmin());
            workflowData.put("userId", workflowPackageStatus.getUserId());
            workflowData.put("requesterId", workflowPackageStatus.getUserId());
            workflowData.put("isDriver", workflowPackageStatus.getIsDriver());
            workflowData.put("driverId", workflowPackageStatus.getDriverId());

            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitUntilPackageStatusChangedToCancelled").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while

            System.out.println(">>>>>>>>>>>>>>>>>>>> HEREEEEEEEEEE AFTER CANCELLING THE REQUEST : ");
            System.out.println(">> PROCESS INSTANCE " + processInstance.getProcessInstanceId() + " EXECUTION " + execution.getId());
            System.out.println(">>>>>> " + workflowData.toString());
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.PICKEDUP)) {
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitUntilPackageStatusChangedToPickedUp").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition

        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.SCHEDULED_FOR_PICKUP)) {
            workflowData.put("requestId", workflowPackageStatus.getRequestId());
            workflowData.put("requesterId", workflowPackageStatus.getRequesterId());
            workflowData.put("pickupDate", workflowPackageStatus.getPickupDate());
            workflowData.put("from", workflowPackageStatus.getTimeFrom());
            workflowData.put("to", workflowPackageStatus.getTimeTo());
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("waitChangingPackageToScheduledForPickup").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition
        if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.ASSIGNED)) {
            while (timer < 10000) {
                // Query the Process Instance
                execution = runTimeService.createExecutionQuery()
                        .processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitForAssingPackageAsAssigned").singleResult();
                if (execution != null) {
                    break;
                } else {
                    Thread.sleep(100L);
                    timer += 100;
                }//end if-Else Block
            }//end while
            runTimeService.signal(execution.getId(), workflowData);
        }//end if Condition

        // taskService.complete(task.getId());
    }//end receiveMessage Method

}
