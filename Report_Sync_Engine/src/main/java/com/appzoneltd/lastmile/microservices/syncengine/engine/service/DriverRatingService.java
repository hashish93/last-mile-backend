package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.DriverRatingCountDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.DriverRatingDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.DriverDetailsModel;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.DriverRatingSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.DriverRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.FreelancerDriverRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.UserRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.DriverEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.FreelancerDriverEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.UserEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.DriverRatingDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.DriverRatingSummeryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.DriverRatingDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.DriverRatingSummeryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class DriverRatingService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private FreelancerDriverRepository freelancerDriverRepository;

	@Autowired
	private SyncEngineDao syncEngineDao;

	@Autowired
	private DriverRatingDetailsRepository driverRatingDetailsRepository;

	@Autowired
	private DriverRatingSummeryRepository driverRatingSummeryRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void getAllDriverRatingDetails(Date lastSyncTime) {
		// TODO Auto-generated method stub
		getDriverRatings(lastSyncTime);
		fillCustomerRatingDetailsToSummery();
	}

	private void getDriverRatings(Date lastSyncTime) {
		List<DriverDetailsModel> driverDetailsModels = new ArrayList<>();
		List<DriverEntity> driverEntities = null;
		List<FreelancerDriverEntity> freelancerDriverEntities = null;
		if (lastSyncTime == null) {
			driverEntities = driverRepository.getAllDriversOrdered();
			freelancerDriverEntities = freelancerDriverRepository.getAllFreelancerDriversOrdered();
		} else {
			driverEntities = driverRepository.getLastUpdatedDrivers(lastSyncTime);
			freelancerDriverEntities = freelancerDriverRepository.getLastUpdatedFreelancerDrivers(lastSyncTime);
		}

		driverDetailsModels.addAll(mapDriverEntitesToDriverDetailsDto(driverEntities));
		driverDetailsModels.addAll(mapFreelancerDriverEntitesToDriverDetailsDto(freelancerDriverEntities));

		System.out.println("driverDetailsModels size " + driverDetailsModels.size());
		saveDriverDetails(driverDetailsModels);
		List<Date> dates = new ArrayList<>();
		if (driverEntities.size() > 0) {
			dates.add(driverEntities.get(0).getUpdated());
		}
		if (freelancerDriverEntities.size() > 0) {
			dates.add(freelancerDriverEntities.get(0).getUpdated());
		}
		if (dates.size() > 0) {
			final Date updatedDate = dates.stream().max(Date::compareTo).get();
			if (updatedDate != null) {
				SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(6L);
				syncEngineEntity.setLastSyncTime(updatedDate);
				syncEngineDao.save(syncEngineEntity);
			}
		}
	}

	private void saveDriverDetails(List<DriverDetailsModel> driverDetailsModels) {
		for (DriverDetailsModel driverDetailsModel : driverDetailsModels) {
			DriverRatingDetailsEntity driverRatingDetailsEntity = driverRatingDetailsRepository
					.findByDriverId(driverDetailsModel.getDriverId());
			if (driverRatingDetailsEntity == null) {
				driverRatingDetailsEntity = new DriverRatingDetailsEntity();
			}
			driverRatingDetailsEntity.setDriverId(driverDetailsModel.getDriverId());
			driverRatingDetailsEntity.setDriverType(driverDetailsModel.getDriverType());
			driverRatingDetailsEntity.setHubId(driverDetailsModel.getHubId());
			if (driverDetailsModel.getRating() == null) {
				driverDetailsModel.setRating(new BigDecimal(0));
			}
			driverRatingDetailsEntity.setDriverRating(driverDetailsModel.getRating());
			driverRatingDetailsRepository.save(driverRatingDetailsEntity);
		}

	}

	private List<DriverDetailsModel> mapDriverEntitesToDriverDetailsDto(List<DriverEntity> driverEntities) {
		List<DriverDetailsModel> driverDetailsModels = new ArrayList<>();
		for (DriverEntity driverEntity : driverEntities) {
			DriverDetailsModel DriverDetailsModel = new DriverDetailsModel();
			DriverDetailsModel.setDriverId(driverEntity.getId());
			DriverDetailsModel.setDriverType("CORPORATE_DRIVER");
			DriverDetailsModel.setRating(driverEntity.getRating());
			UserEntity userEntity = userRepository.findOne(driverEntity.getId());
			if(userEntity !=null){
				if(userEntity.getListOfUserHub() != null && userEntity.getListOfUserHub().size() > 0){
					DriverDetailsModel.setHubId(userEntity.getListOfUserHub().get(0).getHubId());
				}
				
			}
			DriverDetailsModel.setUpdated(driverEntity.getUpdated());
			driverDetailsModels.add(DriverDetailsModel);
		}
		return driverDetailsModels;
	}

	private List<DriverDetailsModel> mapFreelancerDriverEntitesToDriverDetailsDto(
			List<FreelancerDriverEntity> freelancerDriverEntities) {
		List<DriverDetailsModel> driverDetailsModels = new ArrayList<>();
		for (FreelancerDriverEntity freelancerDriverEntity : freelancerDriverEntities) {
			DriverDetailsModel DriverDetailsModel = new DriverDetailsModel();
			DriverDetailsModel.setDriverId(freelancerDriverEntity.getId());
			DriverDetailsModel.setDriverType("FREELANCER_DRIVER");
			DriverDetailsModel.setHubId(null);
			DriverDetailsModel.setRating(freelancerDriverEntity.getRating());
			DriverDetailsModel.setUpdated(freelancerDriverEntity.getUpdated());
			driverDetailsModels.add(DriverDetailsModel);
		}
		return driverDetailsModels;
	}

	private void fillCustomerRatingDetailsToSummery() {
		driverRatingSummeryRepository.deleteAll();
		HashMap<DriverRatingDto, DriverRatingCountDto> map = prepareDriverReports();		
		saveSummeryReport(map);
	}

	private HashMap<DriverRatingDto, DriverRatingCountDto> prepareDriverReports() {
		List<DriverRatingSummeryReportModel>  driverRatingSummeryReportModels=driverRatingDetailsRepository
				.findByRateAndHubIdGrouped();
		HashMap<DriverRatingDto, DriverRatingCountDto> map = new HashMap<>();
		for (DriverRatingSummeryReportModel driverRatingSummeryReportModel : driverRatingSummeryReportModels) {
			DriverRatingDto driverRatingDto = new DriverRatingDto();
			if(driverRatingSummeryReportModel.getHubId() !=null){
				driverRatingDto.setHubId(driverRatingSummeryReportModel.getHubId());
			}
			driverRatingDto.setDriverType(driverRatingSummeryReportModel.getType());
			DriverRatingCountDto driverRatingCountDto = map.get(driverRatingDto);
			if (driverRatingCountDto == null) {
				driverRatingCountDto = new DriverRatingCountDto(0L, 0L, 0L, 0L , 0L );
			}
			
			Double rate = 0.0;
			if(driverRatingSummeryReportModel.getRate() !=null){
				rate = driverRatingSummeryReportModel.getRate().doubleValue();
			}
			if(rate > 0 && rate <= 1){
				driverRatingCountDto.setOneStar(driverRatingCountDto.getOneStar() + driverRatingSummeryReportModel.getCount());
			}else if(rate > 1 && rate <= 2){
				driverRatingCountDto.setTwoStars(driverRatingCountDto.getTwoStars() + driverRatingSummeryReportModel.getCount());
			}else if(rate > 2 && rate <= 3){
				driverRatingCountDto.setThreeStars(driverRatingCountDto.getThreeStars() + driverRatingSummeryReportModel.getCount());
			}else if(rate > 3 && rate <= 4){
				driverRatingCountDto.setFourStars(driverRatingCountDto.getFourStars() + driverRatingSummeryReportModel.getCount());
			}else if(rate > 4 && rate <=5){
				driverRatingCountDto.setFiveStars(driverRatingCountDto.getFiveStars() + driverRatingSummeryReportModel.getCount());
			}
			map.put(driverRatingDto, driverRatingCountDto);
		}
		return map;
	}
	
	
	private void saveSummeryReport(HashMap<DriverRatingDto, DriverRatingCountDto> map) {
		for (HashMap.Entry<DriverRatingDto, DriverRatingCountDto> entry : map.entrySet()) {
			DriverRatingDto driverRatingDto = entry.getKey();
			DriverRatingCountDto driverRatingCountDto = entry.getValue();
			DriverRatingSummeryEntity driverRatingSummeryEntity = new DriverRatingSummeryEntity();
			driverRatingSummeryEntity.setHubId(driverRatingDto.getHubId());
			driverRatingSummeryEntity.setDriverType(driverRatingDto.getDriverType());
			driverRatingSummeryEntity.setOneStar(driverRatingCountDto.getOneStar());
			driverRatingSummeryEntity.setTwoStar(driverRatingCountDto.getTwoStars());
			driverRatingSummeryEntity.setThreeStar(driverRatingCountDto.getThreeStars());
			driverRatingSummeryEntity.setFourStar(driverRatingCountDto.getFourStars());
			driverRatingSummeryEntity.setFiveStar(driverRatingCountDto.getFiveStars());
			driverRatingSummeryRepository.save(driverRatingSummeryEntity);
		}
		
	}
}

