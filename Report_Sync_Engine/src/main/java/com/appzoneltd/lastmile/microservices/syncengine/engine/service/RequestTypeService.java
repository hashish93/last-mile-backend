package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.RequestCountTypeDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.PeriodEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.RequestTypeDetailsModel;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.RequestTypeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.RequestTypeDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.RequestTypeSummeryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.RequestTypeDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.RequestTypeSummeryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class RequestTypeService {

	@Autowired
	private SyncEngineDao syncEngineDao;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;

	@Autowired
	private ReturnRequestRepository returnRequestRepository;

	@Autowired
	private RequestTypeDetailsRepository requestTypeDetailsRepository;
	
	@Autowired
	private RequestTypeSummeryRepository requestTypeSummeryRepository;

	public void getAllRequestDetails(Date lastTimeSync) {
		getRequestTypes(lastTimeSync);
		fillRequestTypeDetailsToSummery();
	}

	private void getRequestTypes(Date lastTimeSync) {
		List<RequestTypeDetailsModel> requestTypeDetailsModels = new ArrayList<>();

		requestTypeDetailsModels.addAll(fetchPickupRequests(lastTimeSync));
		requestTypeDetailsModels.addAll(fetchDeliveryRequests(lastTimeSync));
		requestTypeDetailsModels.addAll(fetchReturnRequests(lastTimeSync));

		System.out.println("Request types effected rows " + requestTypeDetailsModels.size());
		if (requestTypeDetailsModels != null && requestTypeDetailsModels.size() > 0) {
			for (RequestTypeDetailsModel requestTypeDetailsModel : requestTypeDetailsModels) {
				RequestTypeDetailsEntity requestTypeDetailsEntity = null;
				if (lastTimeSync != null) {
					requestTypeDetailsEntity = requestTypeDetailsRepository.findByRequestIdAndRequestType(
							requestTypeDetailsModel.getRequestId(), requestTypeDetailsModel.getRequestType());
				}
				if (requestTypeDetailsEntity == null) {
					requestTypeDetailsEntity = new RequestTypeDetailsEntity();
				}

				requestTypeDetailsEntity.setRequestId(requestTypeDetailsModel.getRequestId());
				requestTypeDetailsEntity.setRequestType(requestTypeDetailsModel.getRequestType());
				requestTypeDetailsEntity.setCreated(requestTypeDetailsModel.getCreated());
				requestTypeDetailsEntity.setHubId(requestTypeDetailsModel.getHubId());
				requestTypeDetailsRepository.save(requestTypeDetailsEntity);

			}

			List<Date> dates = new ArrayList<>();
			if (fetchPickupRequests(lastTimeSync).size() > 0) {
				dates.add(fetchPickupRequests(lastTimeSync).get(0).getUpdated());
			}
			if (fetchDeliveryRequests(lastTimeSync).size() > 0) {
				dates.add(fetchDeliveryRequests(lastTimeSync).get(0).getUpdated());
			}
			if (fetchReturnRequests(lastTimeSync).size() > 0) {
				dates.add(fetchReturnRequests(lastTimeSync).get(0).getUpdated());
			}
			if(dates.size()>0){
				final Date updatedDate = dates.stream().max(Date::compareTo).get();
				if (updatedDate != null) {
					SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(3L);
					syncEngineEntity.setLastSyncTime(updatedDate);
					syncEngineDao.save(syncEngineEntity);
				}
			}
		}
	}

	private List<RequestTypeDetailsModel> fetchPickupRequests(Date lastSync) {
		List<RequestTypeDetailsModel> requestTypeDetailsModels = new ArrayList<>();
		List<PickupRequestEntity> pickupRequestEntities = null;
		if (lastSync == null) {
			pickupRequestEntities = pickupRequestRepository.getAllPickupOrdered();
		} else {
			pickupRequestEntities = pickupRequestRepository.getLastUpdatedAllPickupOrdered(lastSync);
		}

		for (PickupRequestEntity pickupRequestEntity : pickupRequestEntities) {
			RequestTypeDetailsModel requestTypeDetailsModel = new RequestTypeDetailsModel();
			requestTypeDetailsModel.setRequestId(pickupRequestEntity.getPickupRequestId());
			requestTypeDetailsModel.setHubId(pickupRequestEntity.getHubId());
			requestTypeDetailsModel.setRequestType(pickupRequestEntity.getPickupRequestType().getType());
			requestTypeDetailsModel.setCreated(pickupRequestEntity.getCreated());
			requestTypeDetailsModel.setUpdated(pickupRequestEntity.getUpdated());
			requestTypeDetailsModels.add(requestTypeDetailsModel);
		}
		return requestTypeDetailsModels;
	}

	private List<RequestTypeDetailsModel> fetchDeliveryRequests(Date lastSync) {
		List<RequestTypeDetailsModel> requestTypeDetailsModels = new ArrayList<>();
		List<DeliveryRequestEntity> deliveryRequestEntities = null;
		if (lastSync == null) {
			deliveryRequestEntities = deliveryRequestRepository.getAllDeliveryOrdered();
		} else {
			deliveryRequestEntities = deliveryRequestRepository.getLastUpdatedAllDeliveryOrdered(lastSync);
		}
		for (DeliveryRequestEntity deliveryRequestEntity : deliveryRequestEntities) {
			RequestTypeDetailsModel requestTypeDetailsModel = new RequestTypeDetailsModel();
			requestTypeDetailsModel.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
			requestTypeDetailsModel.setHubId(deliveryRequestEntity.getHubId());
			requestTypeDetailsModel.setRequestType("DELIVERY");
			requestTypeDetailsModel.setCreated(deliveryRequestEntity.getCreated());
			requestTypeDetailsModel.setUpdated(deliveryRequestEntity.getUpdated());
			requestTypeDetailsModels.add(requestTypeDetailsModel);
		}
		return requestTypeDetailsModels;
	}

	private List<RequestTypeDetailsModel> fetchReturnRequests(Date lastSync) {
		List<RequestTypeDetailsModel> requestTypeDetailsModels = new ArrayList<>();
		List<ReturnRequestEntity> returnRequestEntities = null;
		if (lastSync == null) {
			returnRequestEntities = returnRequestRepository.getAllReturnOrdered();
		} else {
			returnRequestEntities = returnRequestRepository.getLastUpdatedAllReturnOrdered(lastSync);
		}
		for (ReturnRequestEntity returnRequestEntity : returnRequestEntities) {
			RequestTypeDetailsModel requestTypeDetailsModel = new RequestTypeDetailsModel();
			requestTypeDetailsModel.setRequestId(returnRequestEntity.getReturnRequestId());
			requestTypeDetailsModel.setHubId(returnRequestEntity.getHubId());
			requestTypeDetailsModel.setRequestType("RETURN");
			requestTypeDetailsModel.setCreated(returnRequestEntity.getCreated());
			requestTypeDetailsModel.setUpdated(returnRequestEntity.getUpdated());
			requestTypeDetailsModels.add(requestTypeDetailsModel);
		}
		return requestTypeDetailsModels;
	}

	private void fillRequestTypeDetailsToSummery() {
		Date start = new Date();
		Date end = getDateFromPreviousDay(30);
		HashMap<Long, RequestCountTypeDto> thirtyDaysMap = preparePackageReports(start, end);

		end = getDateFromPreviousDay(7);
		HashMap<Long, RequestCountTypeDto> sevenDaysMap = preparePackageReports(start, end);

		requestTypeSummeryRepository.deleteAll();
		saveSummeryReport(thirtyDaysMap, PeriodEnum.THIRTY_DAYS.name());
		saveSummeryReport(sevenDaysMap, PeriodEnum.SEVEN_DAYS.name());

	}

	private HashMap<Long, RequestCountTypeDto> preparePackageReports(Date start, Date end) {
		List<RequestTypeSummeryReportModel> requestTypeSummeryReportModels =  requestTypeDetailsRepository
				.findByHubIdAndRequestTypeGrouped(start , end);
		HashMap<Long, RequestCountTypeDto> map = new HashMap<>();
		for(RequestTypeSummeryReportModel requestTypeSummeryReportModel : requestTypeSummeryReportModels){
			RequestCountTypeDto requestCountTypeDto = map.get(requestTypeSummeryReportModel.getHubId());
			if (requestCountTypeDto == null) {
				requestCountTypeDto = new RequestCountTypeDto(0L, 0L, 0L,0L);
			}
			if("ON-DEMAND".equalsIgnoreCase(requestTypeSummeryReportModel.getRequestType())){
				requestCountTypeDto.setOnDemandCount(requestTypeSummeryReportModel.getCount());
			}
			else if("SCHEDULED".equalsIgnoreCase(requestTypeSummeryReportModel.getRequestType())){
				requestCountTypeDto.setScheduledCount(requestTypeSummeryReportModel.getCount());
			}
			else if("DELIVERY".equalsIgnoreCase(requestTypeSummeryReportModel.getRequestType())){
				requestCountTypeDto.setDeliveryCount(requestTypeSummeryReportModel.getCount());
			}
			else if("RETURN".equalsIgnoreCase(requestTypeSummeryReportModel.getRequestType())){
				requestCountTypeDto.setReturnCount(requestTypeSummeryReportModel.getCount());
			}
			
			map.put(requestTypeSummeryReportModel.getHubId(), requestCountTypeDto);
		}

		return map;
	}
	
	private void saveSummeryReport(HashMap<Long, RequestCountTypeDto> map, String withinPeriod) {
		for (HashMap.Entry<Long, RequestCountTypeDto> entry : map.entrySet()) {
			Long hubId = entry.getKey();
			RequestCountTypeDto requestCountTypeDto = entry.getValue();
			RequestTypeSummeryEntity requestTypeSummeryEntity = new RequestTypeSummeryEntity();
			requestTypeSummeryEntity.setHubId(hubId);
			requestTypeSummeryEntity.setWithinPeriod(withinPeriod);
			requestTypeSummeryEntity.setOnDemandCount(requestCountTypeDto.getOnDemandCount());
			requestTypeSummeryEntity.setScheduledCount(requestCountTypeDto.getScheduledCount());
			requestTypeSummeryEntity.setDeliveryCount(requestCountTypeDto.getDeliveryCount());
			requestTypeSummeryEntity.setReturnCount(requestCountTypeDto.getReturnCount());
			requestTypeSummeryRepository.save(requestTypeSummeryEntity);
		}
	}

	private Date getDateFromPreviousDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		Date end = cal.getTime();
		return end;
	}

}
