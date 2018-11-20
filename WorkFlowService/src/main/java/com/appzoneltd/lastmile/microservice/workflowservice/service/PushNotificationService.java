package com.appzoneltd.lastmile.microservice.workflowservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.WorkflowServiceProducer;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NotificationModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PushNotificationService {

	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private ObjectMapper mapper;
	
	public void sendPackageAlreadyAssignedNotification(WorkflowDriverResponse workflowDriverResponse){
		/// Generate the Notification Model
	    NotificationModel notificationModel=new NotificationModel(); 
	    notificationModel.setPackageId(workflowDriverResponse.getPackageId());
	    notificationModel.setRecipientType("DRIVER");
	    ////// Filling the drivers 
	    Long[] driversIDs = new Long[1];
	    driversIDs[0]=workflowDriverResponse.getDriverId();
	    // Filling Lsit of Drivers to Array
	    /// Setting the driverIds to Send
	    notificationModel.setUserIds(driversIDs);
	    // Putting the Map
	    Map<String, Object> data = new HashMap<String, Object>();
	    data.put("notification_title", "LastMile");
	    data.put("notification_body", "Package Already Assigned");
	    data.put("notification_item_title", "Package Already Assigned");
	    data.put("notification_item_body", "Package Already Assigned");
	    data.put("type", "");
	    data.put("time", new Date().getTime());
	    data.put("payload", null);
	    // Attach Data Section
	    notificationModel.setData(data);
	    // Produce Message to Kafka 
	    try {
	    	workflowServiceProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
			System.out.println(">>> TO SEND NOTIFICATION : "+notificationModel.toString());
		    System.out.println(">>> Notification Pushed Successfully ");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try_catch Block
	    
	}
	
}
