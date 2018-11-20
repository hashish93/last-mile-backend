package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.PickupStatisticCountDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.PickupStatisticsDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.PeriodEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.PickupStatisticsDetailsReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.pickupStatisticsSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.OutOfCoverageRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.OutOfCoverageRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.PickupStatisticsDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.PickupStatisticsSummeryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PickupStatisticsDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PickupStatisticsSummeryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class PickupStatisticsService {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Autowired
	private OutOfCoverageRequestRepository outOfCoverageRequestRepository;
	
	@Autowired
	private PickupStatisticsSummeryRepository pickupStatisticsSummeryRepository;
	
	@Autowired
	private SyncEngineDao syncEngineDao; 
	
	@Autowired
	private PickupStatisticsDetailsRepository pickupStatisticsDetailsRepository;

	public void getAllPickupStatisticsDetails(Date lastSync) {
		getPickupStatisticsDetails(lastSync);
		fillPickupStatisticsReportDetailsToSummery();
	}

	private void getPickupStatisticsDetails(Date lastSync) {

		List<PickupRequestEntity> pickupRequestEntities = null;
		List<OutOfCoverageRequestEntity> outOfCoverageRequestEntities = null;
		if (lastSync == null) {
			pickupRequestEntities = pickupRequestRepository.getAllPickedupAndCanceledOrdered();
			outOfCoverageRequestEntities =  (List<OutOfCoverageRequestEntity>) outOfCoverageRequestRepository.findAll();
		} else {
			pickupRequestEntities = pickupRequestRepository.getLastUpdatedPickedupAndCanceledOrdered(lastSync);
			outOfCoverageRequestEntities = outOfCoverageRequestRepository.getLastUpdatedEntites(lastSync);
		}
		List<PickupStatisticsDetailsReportModel> canceledAndPickedupModels = getPickupStatisticsDetailsFromEntites(pickupRequestEntities);
		List<PickupStatisticsDetailsReportModel> outOfCoveredRequestsModel = getPickupStatisticsDetailsFromOutRangeEntites(outOfCoverageRequestEntities);
		
		List<PickupStatisticsDetailsReportModel> detailsReportModels = new ArrayList<>();
		detailsReportModels.addAll(canceledAndPickedupModels);
		detailsReportModels.addAll(outOfCoveredRequestsModel);
		System.out.println("number of canceled and picked up "+canceledAndPickedupModels.size());
		System.out.println("number of out of range "+outOfCoveredRequestsModel.size());
		savedetailsReport(detailsReportModels);
		
		List<Date> dates = new ArrayList<>();
		if (canceledAndPickedupModels.size() > 0) {
			dates.add(canceledAndPickedupModels.get(0).getUpdated());
		}
		if (outOfCoveredRequestsModel.size() > 0) {
			dates.add(outOfCoveredRequestsModel.get(0).getUpdated());
		}
		if(dates.size()>0){
			final Date updatedDate = dates.stream().max(Date::compareTo).get();
			if (updatedDate != null) {
				 SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(5L);
				 syncEngineEntity.setLastSyncTime(updatedDate);
				 syncEngineDao.save(syncEngineEntity);
			}
		}
		
	}

	private void savedetailsReport(List<PickupStatisticsDetailsReportModel> detailsReportModels) {
		for(PickupStatisticsDetailsReportModel detailsReportModel : detailsReportModels){
			
			PickupStatisticsDetailsEntity pickupStatisticsDetailsEntity = pickupStatisticsDetailsRepository.findByRequestId(detailsReportModel.getRequestId());
			if(pickupStatisticsDetailsEntity == null){
				pickupStatisticsDetailsEntity = new PickupStatisticsDetailsEntity();
			}
			pickupStatisticsDetailsEntity.setHubId(detailsReportModel.getHubId());
			pickupStatisticsDetailsEntity.setRequestId(detailsReportModel.getRequestId());
			pickupStatisticsDetailsEntity.setRequestStatus(detailsReportModel.getRequestStatus());
			pickupStatisticsDetailsEntity.setRequestType(detailsReportModel.getRequestType());
			pickupStatisticsDetailsEntity.setCreated(detailsReportModel.getCreated());
			pickupStatisticsDetailsRepository.save(pickupStatisticsDetailsEntity);
		}
	}

	private List<PickupStatisticsDetailsReportModel> getPickupStatisticsDetailsFromOutRangeEntites(
			List<OutOfCoverageRequestEntity> outOfCoverageRequestEntities) {
		List<PickupStatisticsDetailsReportModel> statisticsDetailsReportModels = new ArrayList<>();

		if (outOfCoverageRequestEntities == null) {
			return statisticsDetailsReportModels;
		}

		for (OutOfCoverageRequestEntity outOfCoverageRequestEntity : outOfCoverageRequestEntities) {
			PickupStatisticsDetailsReportModel statisticsDetailsReportModel = new PickupStatisticsDetailsReportModel();
			statisticsDetailsReportModel.setHubId(null);
			statisticsDetailsReportModel.setRequestId(outOfCoverageRequestEntity.getId());
			statisticsDetailsReportModel.setRequestStatus("NO_COVERAGE");
			statisticsDetailsReportModel.setRequestType(outOfCoverageRequestEntity.getType());
			statisticsDetailsReportModel.setCreated(outOfCoverageRequestEntity.getCreated());
			statisticsDetailsReportModel.setUpdated(outOfCoverageRequestEntity.getCreated());

			statisticsDetailsReportModels.add(statisticsDetailsReportModel);
		}
		return statisticsDetailsReportModels;
	}

	private List<PickupStatisticsDetailsReportModel> getPickupStatisticsDetailsFromEntites(
			List<PickupRequestEntity> pickupRequestEntities) {

		List<PickupStatisticsDetailsReportModel> statisticsDetailsReportModels = new ArrayList<>();

		if (pickupRequestEntities == null) {
			return statisticsDetailsReportModels;
		}

		for (PickupRequestEntity pickupRequestEntity : pickupRequestEntities) {
			PickupStatisticsDetailsReportModel statisticsDetailsReportModel = new PickupStatisticsDetailsReportModel();
			statisticsDetailsReportModel.setHubId(pickupRequestEntity.getHubId());
			statisticsDetailsReportModel.setRequestId(pickupRequestEntity.getPickupRequestId());
			statisticsDetailsReportModel.setRequestStatus(checkRequestStatus(pickupRequestEntity));
			if (pickupRequestEntity.getPickupRequestType() != null) {
				statisticsDetailsReportModel.setRequestType(pickupRequestEntity.getPickupRequestType().getType());
			}
			statisticsDetailsReportModel.setCreated(pickupRequestEntity.getCreated());
			statisticsDetailsReportModel.setUpdated(pickupRequestEntity.getUpdated());

			statisticsDetailsReportModels.add(statisticsDetailsReportModel);
		}
		return statisticsDetailsReportModels;
	}

	private String checkRequestStatus(PickupRequestEntity pickupRequestEntity) {
		if ("CANCELED".equalsIgnoreCase(pickupRequestEntity.getRequestStatus())) {
			if ("NO_CAPACITY".equalsIgnoreCase(pickupRequestEntity.getCancellationReason())
					|| "DRIVER_NO_CAPACITY".equalsIgnoreCase(pickupRequestEntity.getCancellationReason())) {
				return "NO_CAPACITY";
			}
		}
		return pickupRequestEntity.getRequestStatus();
	}

	private void fillPickupStatisticsReportDetailsToSummery() {
		Date start = new Date();
	
		Date end = getDateFromPreviousDay(30);
		HashMap<PickupStatisticsDto, PickupStatisticCountDto> thirtyDaysMap = preparePickupStatisticsReports(start, end);

		end = getDateFromPreviousDay(7);
		HashMap<PickupStatisticsDto, PickupStatisticCountDto> sevenDaysMap = preparePickupStatisticsReports(start, end);

		pickupStatisticsSummeryRepository.deleteAll();
		saveSummeryReport(thirtyDaysMap, PeriodEnum.THIRTY_DAYS.name());
		saveSummeryReport(sevenDaysMap, PeriodEnum.SEVEN_DAYS.name());

	}

	private HashMap<PickupStatisticsDto, PickupStatisticCountDto> preparePickupStatisticsReports(Date start, Date end) {
		List<pickupStatisticsSummeryReportModel> pickupStatisticsDetailsReportModels=  pickupStatisticsDetailsRepository
				.findByHubIdAndRequestTypeAndRequestStatusGrouped(start , end);
		HashMap<PickupStatisticsDto, PickupStatisticCountDto> map = new HashMap<>();
		for(pickupStatisticsSummeryReportModel pickupStatisticsSummeryReportModel : pickupStatisticsDetailsReportModels){
			PickupStatisticsDto pickupStatisticsDto = new PickupStatisticsDto();
			if(pickupStatisticsSummeryReportModel.getHubId() !=null){
				pickupStatisticsDto.setHubId(pickupStatisticsSummeryReportModel.getHubId());
			}
			
			pickupStatisticsDto.setRequestType(pickupStatisticsSummeryReportModel.getRequestType());
			PickupStatisticCountDto pickupStatisticCountDto = map.get(pickupStatisticsDto);
			if (pickupStatisticCountDto == null) {
				pickupStatisticCountDto = new PickupStatisticCountDto(0L, 0L, 0L,0L);
			}
			
			if ("PICKEDUP".equalsIgnoreCase(pickupStatisticsSummeryReportModel.getRequestStatus())) {
				pickupStatisticCountDto.setPickedupCount(pickupStatisticCountDto.getPickedupCount() + pickupStatisticsSummeryReportModel.getCount());
			}
			if ("CANCELED".equalsIgnoreCase(pickupStatisticsSummeryReportModel.getRequestStatus())) {
				pickupStatisticCountDto.setCanceledCount(pickupStatisticCountDto.getCanceledCount() + pickupStatisticsSummeryReportModel.getCount());
			}
			if ("NO_CAPACITY".equalsIgnoreCase(pickupStatisticsSummeryReportModel.getRequestStatus())) {
				pickupStatisticCountDto.setNoCapacityCount(pickupStatisticCountDto.getNoCapacityCount() + pickupStatisticsSummeryReportModel.getCount());
			}
			if ("NO_COVERAGE".equalsIgnoreCase(pickupStatisticsSummeryReportModel.getRequestStatus())) {
				pickupStatisticCountDto.setNoCoverageCount(pickupStatisticCountDto.getNoCoverageCount() + pickupStatisticsSummeryReportModel.getCount());
			}
			
			map.put(pickupStatisticsDto, pickupStatisticCountDto);
		}

		return map;
	}
	
	private void saveSummeryReport(HashMap<PickupStatisticsDto, PickupStatisticCountDto> map, String withinPeriod) {
		for (HashMap.Entry<PickupStatisticsDto, PickupStatisticCountDto> entry : map.entrySet()) {
			PickupStatisticsDto pickupStatisticsDto = entry.getKey();
			PickupStatisticCountDto pickupStatisticCountDto = entry.getValue();
			PickupStatisticsSummeryEntity pickupStatisticsSummeryEntity = new PickupStatisticsSummeryEntity();
			pickupStatisticsSummeryEntity.setHubId(pickupStatisticsDto.getHubId());
			pickupStatisticsSummeryEntity.setRequestType(pickupStatisticsDto.getRequestType());
			pickupStatisticsSummeryEntity.setPickedupCount(pickupStatisticCountDto.getPickedupCount());
			pickupStatisticsSummeryEntity.setCanceledCount(pickupStatisticCountDto.getCanceledCount());
			pickupStatisticsSummeryEntity.setNoCapacityCount(pickupStatisticCountDto.getNoCapacityCount());
			pickupStatisticsSummeryEntity.setNoCoverageCount(pickupStatisticCountDto.getNoCoverageCount());
			pickupStatisticsSummeryEntity.setWithinPeriod(withinPeriod);
			pickupStatisticsSummeryRepository.save(pickupStatisticsSummeryEntity);
		}
	}

	private Date getDateFromPreviousDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		Date end = cal.getTime();
		return end;
	}
	
}
