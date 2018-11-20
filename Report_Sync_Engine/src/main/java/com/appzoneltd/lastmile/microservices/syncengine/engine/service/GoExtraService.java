package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.GoExtraCountDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.GoExtraDetailsDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.PeriodEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.GoExtraDetailsModel;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.GoExtraSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.NearbyVehicleRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.NearbyVehicleEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.GoExtraDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.GoExtraSummeryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.GoExtraDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.GoExtraSummeryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class GoExtraService {

	@Autowired
	private NearbyVehicleRepository nearbyVehicleRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private GoExtraDetailsRepository goExtraDetailsRepository;

	@Autowired
	private GoExtraSummeryRepository goExtraSummeryRepository;
	@Autowired
	private SyncEngineDao syncEngineDao;

	public void getAllGoExtraResponseDetails(Date lastSync) {
		getAllGoExtraDetails(lastSync);
		fillGoExtraDetailsToSummery();
	}

	private void getAllGoExtraDetails(Date lastSync) {
		HashMap<Long, GoExtraDetailsModel> NearByResponseMap = fillAcceptedAndRejectedRequests(lastSync);
		List<GoExtraDetailsDto> goExtraDetailsDtos = new ArrayList<>();
		List<GoExtraDetailsDto> rejectionAndAcceptedList = getAcceptAndRejectEntityDetails(NearByResponseMap, lastSync);
		Collections.sort(rejectionAndAcceptedList, new Comparator<GoExtraDetailsDto>() {
			@Override
			public int compare(GoExtraDetailsDto g1, GoExtraDetailsDto g2) {
				return g1.getUpdated().compareTo(g2.getUpdated());
			}
		});
	
		List<GoExtraDetailsDto> canceledList = fillCanceledRequests(lastSync);
		goExtraDetailsDtos.addAll(rejectionAndAcceptedList);
		goExtraDetailsDtos.addAll(canceledList);
		goExtraDetailsRepository.save(MapGoExtraDtosToEntities(goExtraDetailsDtos));

		List<Date> dates = new ArrayList<>();
		if (rejectionAndAcceptedList.size() > 0) {
			System.out.println("accept and reject date "+rejectionAndAcceptedList.get(rejectionAndAcceptedList.size()-1).getUpdated());
			dates.add(rejectionAndAcceptedList.get(rejectionAndAcceptedList.size()-1).getUpdated());
		}
		if (canceledList.size() > 0) {
			System.out.println("canceled date "+canceledList.get(0).getUpdated());
			dates.add(canceledList.get(0).getUpdated());
		}
		System.out.println("Go Extra effected rows " + goExtraDetailsDtos.size());
		if (dates.size() > 0) {
			
			final Date updatedDate = dates.stream().max(Date::compareTo).get();
			if (updatedDate != null) {
				SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(4L);
				syncEngineEntity.setLastSyncTime(updatedDate);
				syncEngineDao.save(syncEngineEntity);
			}
		}

	}

	private List<GoExtraDetailsEntity> MapGoExtraDtosToEntities(List<GoExtraDetailsDto> goExtraDetailsDtos) {
		List<GoExtraDetailsEntity> goExtraDetailsEntities = new ArrayList<>();
		if (goExtraDetailsDtos != null) {
			for (GoExtraDetailsDto goExtraDetailsDto : goExtraDetailsDtos) {
				GoExtraDetailsEntity goExtraDetailsEntity = new GoExtraDetailsEntity();
				goExtraDetailsEntity.setId(goExtraDetailsDto.getId());
				goExtraDetailsEntity.setRequestId(goExtraDetailsDto.getRequestId());
				goExtraDetailsEntity.setHubId(goExtraDetailsDto.getHubId());
				goExtraDetailsEntity.setRequestStatus(goExtraDetailsDto.getRequestStatus());
				goExtraDetailsEntity.setCreated(goExtraDetailsDto.getCreated());
				goExtraDetailsEntities.add(goExtraDetailsEntity);
			}
		}
		return goExtraDetailsEntities;
	}

	private GoExtraDetailsDto MapGoExtraEntityToDto(GoExtraDetailsEntity extraDetailsEntity) {
		if (extraDetailsEntity == null)
			return null;

		GoExtraDetailsDto goExtraDetailsDto = new GoExtraDetailsDto();
		goExtraDetailsDto.setId(extraDetailsEntity.getId());
		goExtraDetailsDto.setRequestId(extraDetailsEntity.getRequestId());
		goExtraDetailsDto.setHubId(extraDetailsEntity.getHubId());
		goExtraDetailsDto.setRequestStatus(extraDetailsEntity.getRequestStatus());
		goExtraDetailsDto.setCreated(extraDetailsEntity.getCreated());
		return goExtraDetailsDto;

	}

	private List<GoExtraDetailsDto> fillCanceledRequests(Date lastSync) {
		List<GoExtraDetailsDto> goExtraDetailsDtos = new ArrayList<>();
		List<PickupRequestEntity> pickupRequestEntities = null;
		if (lastSync == null) {
			pickupRequestEntities = pickupRequestRepository.getAllCanceledOrdered();
		} else {
			pickupRequestEntities = pickupRequestRepository.getLastUpdatedCanceledOrdered(lastSync);
		}
		for (PickupRequestEntity pickupRequestEntity : pickupRequestEntities) {

			GoExtraDetailsDto goExtraDetailsDto = new GoExtraDetailsDto();
			if (lastSync != null) {
				goExtraDetailsDto = MapGoExtraEntityToDto(goExtraDetailsRepository.findByRequestIdAndRequestStatus(
						pickupRequestEntity.getPickupRequestId(), pickupRequestEntity.getRequestStatus()));
				if (goExtraDetailsDto == null) {
					goExtraDetailsDto = new GoExtraDetailsDto();
				}
			}
			goExtraDetailsDto.setRequestId(pickupRequestEntity.getPickupRequestId());
			goExtraDetailsDto.setRequestStatus(pickupRequestEntity.getRequestStatus());
			goExtraDetailsDto.setCreated(pickupRequestEntity.getCreated());
			goExtraDetailsDto.setUpdated(pickupRequestEntity.getUpdated());
			goExtraDetailsDto.setHubId(pickupRequestEntity.getHubId());
			goExtraDetailsDtos.add(goExtraDetailsDto);
		}
		return goExtraDetailsDtos;
	}

	private HashMap<Long, GoExtraDetailsModel> fillAcceptedAndRejectedRequests(Date lastSync) {
		HashMap<Long, GoExtraDetailsModel> NearByResponseMap = new HashMap<>();
		List<NearbyVehicleEntity> nearbyVehicleEntities = null;
		if (lastSync == null) {
			nearbyVehicleEntities = (List<NearbyVehicleEntity>) nearbyVehicleRepository.getAllNearByOrdered();
		} else {
			nearbyVehicleEntities = (List<NearbyVehicleEntity>) nearbyVehicleRepository
					.getLastUpdatedNearByOrdered(lastSync);
		}
		if (nearbyVehicleEntities != null && nearbyVehicleEntities.size() > 0) {

			for (NearbyVehicleEntity nearbyVehicleEntity : nearbyVehicleEntities) {

				GoExtraDetailsModel goExtraDetailsModel = NearByResponseMap.get(nearbyVehicleEntity.getRequestId());
				if (goExtraDetailsModel == null) {
					goExtraDetailsModel = new GoExtraDetailsModel();
					if(nearbyVehicleEntity.getResponse()== null){
						nearbyVehicleEntity.setResponse("REJECT");
					}
					goExtraDetailsModel.setRequestStatus(nearbyVehicleEntity.getResponse());
					goExtraDetailsModel.setCreated(nearbyVehicleEntity.getCreated());
					goExtraDetailsModel.setUpdated(nearbyVehicleEntity.getUpdated());
				}else{
					if (!"ACCEPT".equalsIgnoreCase(goExtraDetailsModel.getRequestStatus())) {
						if ("ACCEPT".equalsIgnoreCase(nearbyVehicleEntity.getResponse())) {
							goExtraDetailsModel.setRequestStatus("ACCEPT");
						} else {
							goExtraDetailsModel.setRequestStatus("REJECT");
						}
						goExtraDetailsModel.setCreated(nearbyVehicleEntity.getCreated());
						goExtraDetailsModel.setUpdated(nearbyVehicleEntity.getUpdated());
					}	
				}
				
				
				NearByResponseMap.put(nearbyVehicleEntity.getRequestId(), goExtraDetailsModel);
			}
		}

		return NearByResponseMap;
	}

	private List<GoExtraDetailsDto> getAcceptAndRejectEntityDetails(
			HashMap<Long, GoExtraDetailsModel> NearByResponseMap, Date lastSync) {

		List<GoExtraDetailsDto> goExtraDetailsDtos = new ArrayList<>();
		for (HashMap.Entry<Long, GoExtraDetailsModel> entry : NearByResponseMap.entrySet()) {
			Long requestId = entry.getKey();
			GoExtraDetailsModel goExtraDetailsModel = entry.getValue();

			GoExtraDetailsDto goExtraDetailsDto = new GoExtraDetailsDto();
			if (lastSync != null) {
				goExtraDetailsDto = MapGoExtraEntityToDto(goExtraDetailsRepository
						.getDetailsByRequestIdAndNotCanceled(requestId));
				if (goExtraDetailsDto == null) {
					goExtraDetailsDto = new GoExtraDetailsDto();
				}else{
					if("ACCEPT".equalsIgnoreCase(goExtraDetailsDto.getRequestStatus())){
						continue;
					}
				}
			}

			goExtraDetailsDto.setRequestId(requestId);
			goExtraDetailsDto.setCreated(goExtraDetailsModel.getCreated());
			goExtraDetailsDto.setUpdated(goExtraDetailsModel.getUpdated());
			goExtraDetailsDto.setRequestStatus(goExtraDetailsModel.getRequestStatus());

			if(requestId !=null){
				PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
				if (pickupRequestEntity != null) {
					goExtraDetailsDto.setHubId(pickupRequestEntity.getHubId());
				}
				goExtraDetailsDtos.add(goExtraDetailsDto);
			}
		}
		return goExtraDetailsDtos;
	}

	private void fillGoExtraDetailsToSummery() {
		Date start = new Date();
		Date end = getDateFromPreviousDay(30);
		HashMap<Long, GoExtraCountDto> thirtyDaysMap = preparePackageReports(start, end);

		end = getDateFromPreviousDay(7);
		HashMap<Long, GoExtraCountDto> sevenDaysMap = preparePackageReports(start, end);

		goExtraSummeryRepository.deleteAll();
		saveSummeryReport(thirtyDaysMap, PeriodEnum.THIRTY_DAYS.name());
		saveSummeryReport(sevenDaysMap, PeriodEnum.SEVEN_DAYS.name());

	}
	
	private HashMap<Long, GoExtraCountDto> preparePackageReports(Date start, Date end) {
		List<GoExtraSummeryReportModel> goExtraSummeryReportModels =  goExtraDetailsRepository
				.findByHubIdAndRequestStatusGrouped(start , end);
		HashMap<Long, GoExtraCountDto> map = new HashMap<>();
		for(GoExtraSummeryReportModel goExtraSummeryReportModel  : goExtraSummeryReportModels){
			GoExtraCountDto goExtraCountDto = map.get(goExtraSummeryReportModel.getHubId());
			if (goExtraCountDto == null) {
				goExtraCountDto = new GoExtraCountDto(0L, 0L, 0L);
			}
			if("ACCEPT".equalsIgnoreCase(goExtraSummeryReportModel.getRequestStatus())){
				goExtraCountDto.setAcknowledgeCount(goExtraSummeryReportModel.getCount());
			}
			else if("REJECT".equalsIgnoreCase(goExtraSummeryReportModel.getRequestStatus())){
				goExtraCountDto.setRejectedCount(goExtraSummeryReportModel.getCount());
			}
			else if("CANCELED".equalsIgnoreCase(goExtraSummeryReportModel.getRequestStatus())){
				goExtraCountDto.setCanceledCount(goExtraSummeryReportModel.getCount());
			}
			
			
			map.put(goExtraSummeryReportModel.getHubId(), goExtraCountDto);
		}

		return map;
	}
	
	private void saveSummeryReport(HashMap<Long, GoExtraCountDto> map, String withinPeriod) {
		for (HashMap.Entry<Long, GoExtraCountDto> entry : map.entrySet()) {
			Long hubId = entry.getKey();
			GoExtraCountDto goExtraCountDto = entry.getValue();
			GoExtraSummeryEntity goExtraSummeryEntity = new GoExtraSummeryEntity();
			goExtraSummeryEntity.setHubId(hubId);
			goExtraSummeryEntity.setWithPeriod(withinPeriod);
			goExtraSummeryEntity.setRejectCount(goExtraCountDto.getRejectedCount());
			goExtraSummeryEntity.setAcknowledgeCount(goExtraCountDto.getAcknowledgeCount());
			goExtraSummeryEntity.setCanceledCount(goExtraCountDto.getCanceledCount());			
			goExtraSummeryRepository.save(goExtraSummeryEntity);
		}
		
	}


	private Date getDateFromPreviousDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		Date end = cal.getTime();
		return end;
	}


}
