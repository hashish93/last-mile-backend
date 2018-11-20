package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.checkers;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckDriverNavigateToPackageDelegate implements JavaDelegate {

    @Autowired
    private RequestHistoryJpaRepository requestHistoryJpaRepository;
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        boolean systemRecepient = false;
        Long deliveryId = requestHistoryJpaRepository.getPackageDeliveryId((Long) execution.getVariable("packageId"));
        Long activeVehicleId = (Long) execution.getVariable("activeVehicleId");

        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);

        if (deliveryRequestEntity.getRecipientId() != null) {
            systemRecepient = true;
        }
        execution.setVariable("systemRecepient", systemRecepient);
        execution.setVariable("activeVehicleId", activeVehicleId);
    }

}
