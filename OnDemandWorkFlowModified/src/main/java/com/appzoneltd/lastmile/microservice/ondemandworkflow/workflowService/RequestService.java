package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.UsersRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.JobOrder;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.Location;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.PackageStatusModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.Utils;

@Service
public class RequestService {

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private JobOrdersService jobOrdersService;

	public void changePackageStatus(PackageStatusModel packageStatusModel) {

		PackageEntity packageEntity = packageRepository.findOne(packageStatusModel.getPackageId());
		PickupRequestEntity requestEntity = pickupRequestRepository.findOne(packageStatusModel.getRequestId());
		if ((packageEntity != null) && (requestEntity != null)) {

			packageEntity.setStatus(packageStatusModel.getStatus());
			packageRepository.save(packageEntity);

			requestEntity.setRequestStatus(packageStatusModel.getStatus());
			pickupRequestRepository.save(requestEntity);

			// CREATE HISTORY ROW
			RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
			requestHistoryEntity.setPackageId(packageStatusModel.getPackageId());
			requestHistoryEntity.setRequestStatus(packageStatusModel.getStatus());
			requestHistoryEntity.setRequestId(packageStatusModel.getRequestId());
			requestHistoryEntity.setRequestType("PICKUP");
			requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
			requestHistoryEntity.setCreated(new Date());
			requestHistoryRepository.save(requestHistoryEntity);
		}
	}

	public void assignRequestToDriver(Long requestId, Long driverId, Long packageId) {

		PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
		PackageEntity packageEntity = packageRepository.findOne(packageId);
		if (pickupRequestEntity != null) {
			UserEntity usersEntity = usersRepository.findOne(pickupRequestEntity.getRequesterId());
			// Creating JobOrder
			JobOrder jobOrder = new JobOrder();
			jobOrder.setJobOrderId(pickupRequestEntity.getPickupRequestId());
			jobOrder.setOrderType("PICKUP");
			if (usersEntity != null) {
				jobOrder.setCustomerName(usersEntity.getUsername());
				jobOrder.setCustomerPhone(usersEntity.getPhone());
			}
			// Init the Orginal Location
			Location originalLocation = new Location();
			Location actualLocation = new Location();
			// Setting OriginLocation
			originalLocation.setLatitude(pickupRequestEntity.getPickuplatitude());
			originalLocation.setLongitude(pickupRequestEntity.getPickuplongitude());
			// Setting OriginLocation
			actualLocation.setLatitude(pickupRequestEntity.getPickuplatitude());
			actualLocation.setLongitude(pickupRequestEntity.getPickuplongitude());
			//// assing Locations
			jobOrder.setOriginalLocation(originalLocation);
			jobOrder.setActualLocation(actualLocation);

			jobOrder.setRecepientName(pickupRequestEntity.getRecipientname());
			jobOrder.setRecepientPhone(pickupRequestEntity.getRecipientmobile());
			jobOrder.setOrderStatus(pickupRequestEntity.getRequestStatus());
			if (packageEntity != null) {
				jobOrder.setActualWeight(packageEntity.getActualweight().intValue());
			}
			jobOrder.setPriority(1);
			jobOrdersService.assignJobOrderForDriver(driverId, jobOrder, pickupRequestEntity.getHubId());
		}

	}

	public String setPackageTrackingNumber(Long packageId) {
		String trackingNumber = Utils.generateUUID() + "";
		// Save
		MyPrinter.workflowStep("<<<<<<<<<<<<<<<<<< SET TRACKING NUMBER >>>>>>>>");
		PackageEntity packageEntity = packageRepository.findOne(packageId);
		if (packageEntity != null) {
			packageEntity.setTrackingNumber(trackingNumber);
			MyPrinter.workflowStep("<<<<<<<<<<<<<<<<<< packageEntity >>>>>>>>" + packageEntity.toString());
			packageRepository.save(packageEntity);
		}
		return trackingNumber;
	}

	public Long getRequestHubId(Long requestId) {
		PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
		if (pickupRequestEntity != null) {
			return pickupRequestEntity.getHubId();
		}
		return null;
	}

}
