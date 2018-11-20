package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.PackageCountTypeDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.dto.PackageTypeDto;
import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.PeriodEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.PackageTypeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.PackageRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.VerifiedPackageRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PackageEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.VerifiedPackageEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.PackageTypeDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.PackageTypeSummaryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PackageTypeDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PackageTypeSummaryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class PackageTypeService {

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@Autowired
	private PackageTypeDetailsRepository packageTypeDetailsRepository;

	@Autowired
	private PackageTypeSummaryRepository packageTypeSummaryRepository;

	@Autowired
	private SyncEngineDao syncEngineDao;

	@Autowired
	private VerifiedPackageRepository verifiedPackageRepository;

	public void getAllPackageToDetails(Date lastTimeSync) {
		getAllPackages(lastTimeSync);
		fillPackageTypeDetailsToSummery();
	}

	private void getAllPackages(Date syncTime) {
		List<PackageEntity> packageEntities = new ArrayList<>();
		if (syncTime == null) {
			packageEntities = (List<PackageEntity>) packageRepository.getOrderedPackageEntites();
		} else {
			packageEntities = (List<PackageEntity>) packageRepository.getlastUpdatedPackagesFrom(syncTime);
		}

		System.out.println("PackageType effected rows "+packageEntities.size());
		if (packageEntities != null && packageEntities.size() > 0) {
			for (PackageEntity packageEntity : packageEntities) {
				PackageTypeDetailsEntity packageTypeDetailsEntity = new PackageTypeDetailsEntity();
				if (syncTime != null) {
					packageTypeDetailsEntity = packageTypeDetailsRepository
							.findByPackageId(packageEntity.getPackageId());
					if (packageTypeDetailsEntity == null) {
						packageTypeDetailsEntity = new PackageTypeDetailsEntity();
					}
				}
				Long requestId = requestHistoryRepository.getRequestIdFromPackageId(packageEntity.getPackageId());
				if (requestId != null) {
					Long hubId = pickupRequestRepository.getHubIdFromRequestId(requestId);
					packageTypeDetailsEntity.setHubId(hubId);
				}
				if(packageTypeDetailsEntity.getHubId() ==null)
					continue;
				
				packageTypeDetailsEntity.setPackageId(packageEntity.getPackageId());
				if (packageEntity.getPackageType() != null) {
					packageTypeDetailsEntity.setPackageTypeId(packageEntity.getPackageType().getPackageTypeId());
				}

				packageTypeDetailsEntity.setCreated(packageEntity.getCreated());
				VerifiedPackageEntity verifiedPackageEntity = verifiedPackageRepository
						.findOne(packageEntity.getPackageId());
				Long weight = null;
				if (verifiedPackageEntity != null) {
					weight = verifiedPackageEntity.getActualweight().longValue();
				} else {
					weight = packageEntity.getActualweight().longValue();
				}
				packageTypeDetailsEntity.setPackageValue(weight);
				packageTypeDetailsRepository.save(packageTypeDetailsEntity);
			}
			SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(1L);
			syncEngineEntity.setLastSyncTime(packageEntities.get(0).getUpdated());
			syncEngineDao.save(syncEngineEntity);
		}

	}

	private void fillPackageTypeDetailsToSummery() {
		Date start = new Date();
		Date end = getDateFromPreviousDay(30);
		HashMap<PackageTypeDto, PackageCountTypeDto> thirtyDaysMap = preparePackageReports(start, end);

		end = getDateFromPreviousDay(7);
		HashMap<PackageTypeDto, PackageCountTypeDto> sevenDaysMap = preparePackageReports(start, end);

		packageTypeSummaryRepository.deleteAll();
		saveSummeryReport(thirtyDaysMap, PeriodEnum.THIRTY_DAYS.name());
		saveSummeryReport(sevenDaysMap, PeriodEnum.SEVEN_DAYS.name());
	}

	private HashMap<PackageTypeDto, PackageCountTypeDto> preparePackageReports(Date start, Date end) {
		List<PackageTypeSummeryReportModel> PackageTypeSummeryReportModels = packageTypeDetailsRepository
				.findByHubIdAndPackageTypeIdGrouped(start, end);
		HashMap<PackageTypeDto, PackageCountTypeDto> map = new HashMap<>();
		for (PackageTypeSummeryReportModel packageTypeSummeryReportModel : PackageTypeSummeryReportModels) {
			PackageTypeDto packageTypeDto = new PackageTypeDto();
			packageTypeDto.setHubId(packageTypeSummeryReportModel.getHubId());
			packageTypeDto.setPackageTypeId(packageTypeSummeryReportModel.getPackageTypeId());
			PackageCountTypeDto countTypeDto = map.get(packageTypeDto);
			if (countTypeDto == null) {
				countTypeDto = new PackageCountTypeDto(0L, 0L, 0L);
			}
			if (packageTypeSummeryReportModel.getPackageValue() >= 0 && packageTypeSummeryReportModel.getPackageValue() < 5) {
				countTypeDto.setCount5(countTypeDto.getCount5() + packageTypeSummeryReportModel.getCount());
			}
			if (packageTypeSummeryReportModel.getPackageValue() >= 5 && packageTypeSummeryReportModel.getPackageValue() < 10) {
				countTypeDto.setCount10(countTypeDto.getCount10() + packageTypeSummeryReportModel.getCount());
			}
			if (packageTypeSummeryReportModel.getPackageValue() >= 10 && packageTypeSummeryReportModel.getPackageValue() < 25) {
				countTypeDto.setCount25(countTypeDto.getCount25() + packageTypeSummeryReportModel.getCount());
			}
			map.put(packageTypeDto, countTypeDto);
		}
		return map;

	}

	private void saveSummeryReport(HashMap<PackageTypeDto, PackageCountTypeDto> map, String withinPeriod) {
		for (HashMap.Entry<PackageTypeDto, PackageCountTypeDto> entry : map.entrySet()) {
			PackageTypeDto packageTypeDto = entry.getKey();
			PackageCountTypeDto countTypeDto = entry.getValue();
			PackageTypeSummaryEntity packageTypeSummaryEntity = new PackageTypeSummaryEntity();
			packageTypeSummaryEntity.setHubId(packageTypeDto.getHubId());
			packageTypeSummaryEntity.setPackageTypeId(packageTypeDto.getPackageTypeId());
			packageTypeSummaryEntity.setWithinPeriod(withinPeriod);
			packageTypeSummaryEntity.setPackageLess5Kg(countTypeDto.getCount5());
			packageTypeSummaryEntity.setPackageLess10Kg(countTypeDto.getCount10());
			packageTypeSummaryEntity.setPackageLess5Kg(countTypeDto.getCount5());
			packageTypeSummaryEntity.setPackageLess25Kg(countTypeDto.getCount25());
			packageTypeSummaryRepository.save(packageTypeSummaryEntity);
		}
	}

	private Date getDateFromPreviousDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		Date end = cal.getTime();
		return end;
	}

}
