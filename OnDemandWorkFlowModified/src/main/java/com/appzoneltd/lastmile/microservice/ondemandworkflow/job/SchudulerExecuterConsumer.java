package com.appzoneltd.lastmile.microservice.ondemandworkflow.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.PackageStatusModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

//
///**
// * Created by hashish on 4/6/2017.
// */
@Component
public class SchudulerExecuterConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RequestService requestService;

	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;
	
	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@KafkaListener(topics = { "PACKAGE_ACTION_NEEDED_TRIGGER" })
	private void automaticFireAction(@Payload String payload) throws Exception {
		ActionNeededBase actionNeededBase = mapper.readValue(payload, ActionNeededBase.class);
		
		if(actionNeededBase!=null){
			Long requestId = requestHistoryRepository.getRequestIdFromPackageId(actionNeededBase.getPackageId());
			PackageStatusModel packageStatusModel = new PackageStatusModel();
			packageStatusModel.setPackageId(actionNeededBase.getPackageId());
			packageStatusModel.setStatus(ChangePackageStatusEnum.ACTION_NEEDED.getPackageStatus());
			packageStatusModel.setRequestId(requestId);		
			
			onDemandWorkFlowProducer.sendMessage("STATISTICS_DRIVER_NOT_RESPONDING", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(packageStatusModel));
			
			// CHANGE STATUS
			PackageEntity packageEntity=packageRepository.findOne(actionNeededBase.getPackageId());
			if(packageEntity!=null){
				if("NEW".equalsIgnoreCase(packageEntity.getStatus())){
					requestService.changePackageStatus(packageStatusModel);
				}
			}
		}
	}
}
