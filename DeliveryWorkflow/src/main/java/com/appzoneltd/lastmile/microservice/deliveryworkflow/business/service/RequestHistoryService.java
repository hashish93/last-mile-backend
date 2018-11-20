package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryStatus;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class RequestHistoryService {

	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;

	public Long getDeliveryIdForPackage(Long packageId) {
		List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository.getRequestsForPackage(packageId,
				"DELIVERY");
		if (requestHistoryEntities.size() > 0) {
			return requestHistoryEntities.get(0).getRequestId();
		}
		return null;
	}

	public Long getPickupIdForPackage(Long packageId) {
		List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository.getRequestsForPackage(packageId,
				"PICKUP");
		if (requestHistoryEntities.size() > 0) {
			return requestHistoryEntities.get(0).getRequestId();
		}
		return null;
	}

	public void updateRequestHistoryStatus(Long packageId) {
		Long requestId = getDeliveryIdForPackage(packageId);

		RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
		requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
		requestHistoryEntity.setPackageId(packageId);
		requestHistoryEntity.setRequestId(requestId);
		requestHistoryEntity.setRequestStatus(DeliveryStatus.RESCHEDULED_FOR_DELIVERY.name());
		requestHistoryEntity.setRequestType("DELIVERY");
		requestHistoryEntity.setCreated(new Date());
		requestHistoryJpaRepository.save(requestHistoryEntity);

	}
}
