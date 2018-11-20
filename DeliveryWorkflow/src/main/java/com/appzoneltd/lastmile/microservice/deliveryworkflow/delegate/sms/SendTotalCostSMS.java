package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.sms;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.model.SMSModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.sms.service.SMSService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SendTotalCostSMS implements JavaDelegate {

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private SMSService smsService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println(">>>>>>>> SendTotalCostSMS step >>>>>>>>>>>");
        Long packageId = (Long) execution.getVariable("packageId");
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);

        String mobileNumber = "";

        if (deliveryRequestEntity.getCountryCode() != null) {
            mobileNumber = deliveryRequestEntity.getCountryCode() + deliveryRequestEntity.getRecipientmobile();
        } else {
            mobileNumber = deliveryRequestEntity.getRecipientmobile();
        }

        System.out.println(">>>>>>>> mobileNumber >>>>>>>>>>>");

        // TODO: NEEDS TO BE REFACTORED WITH ACUAL CALCULATIONS.

        String smsMessageTemplate = "Package Total Cost is : "+ new Random().nextInt(10000);

        SMSModel smsModel = new SMSModel();
        smsModel.setPhoneNumber(mobileNumber);
        smsModel.setMessage(smsMessageTemplate);
        System.out.println(">>>>>>>> SMSModel >>>>>>>>>>> " + smsModel);
        // SEND SMS
        smsService.sendSMS(smsModel);

    }

}
