package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.sms;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.model.SMSModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.service.SMSService;

@Service
public class SendConfirmationCodeSMS implements JavaDelegate {

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Autowired
	private SMSService smsService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);
		
		String mobileNumber="";
		
		if(deliveryRequestEntity.getCountryCode()!=null){
			 mobileNumber =deliveryRequestEntity.getCountryCode() + deliveryRequestEntity.getRecipientmobile();
		}else{
			 mobileNumber = deliveryRequestEntity.getRecipientmobile();
		}
		
		
		String confirmationCode= deliveryRequestEntity.getConfirmationCode();

		String smsMessageTemplate = "Your Delivery Confirmation Code is " + confirmationCode;

		SMSModel smsModel = new SMSModel();
		smsModel.setPhoneNumber(mobileNumber);
		smsModel.setMessage(smsMessageTemplate);

		// SEND SMS
		smsService.sendSMS(smsModel);

	}

}