package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.sms;

import java.text.DateFormat;
import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.model.SMSModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.service.SMSService;

@Service
public class SendDeliveryDateSMS implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Autowired
	private SMSService smsService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);
		String senderFullName=deliveryRequestService.getSenderFullName(packageId);
		
		String mobileNumber = "";

		if (deliveryRequestEntity.getCountryCode() != null) {
			mobileNumber = deliveryRequestEntity.getCountryCode() + deliveryRequestEntity.getRecipientmobile();
		} else {
			mobileNumber = deliveryRequestEntity.getRecipientmobile();
		}

		Date deliveryDate = deliveryRequestEntity.getDeliverydate();

		String smsMessageTemplate = "Dear Customer, A new package is sent to you from "+senderFullName
				+ " . It will be delivered on " +  DateFormat.getDateInstance(DateFormat.MEDIUM).format(deliveryDate)
				+ " From "+deliveryRequestEntity.getTimeFrom()+" - "+deliveryRequestEntity.getTimeTo()
				+ " for rescheduling that date, please call \"1999\".";
		
		SMSModel smsModel = new SMSModel();
		smsModel.setPhoneNumber(mobileNumber);
		smsModel.setMessage(smsMessageTemplate);

		System.out.println("SEND SMS WITH MODEL " + smsModel.toString());

		// SEND SMS
		smsService.sendSMS(smsModel);

	}

}