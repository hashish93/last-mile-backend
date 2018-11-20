package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.VerifiedPackageEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.BuildingRepository;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.UsersRepository;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
@Component
@Scope("singleton")
public final class DTOMapper {
	private final RequestHistoryRepository requestHistoryRepository;
	private final UsersRepository usersRepository;
	private final BuildingRepository buildingRepository;

	@Autowired
	public DTOMapper(RequestHistoryRepository requestHistoryRepository, UsersRepository usersRepository,
			BuildingRepository buildingRepository) {
		this.requestHistoryRepository = requestHistoryRepository;
		this.usersRepository = usersRepository;
		this.buildingRepository = buildingRepository;
	}

	public final ArchivedReturn mapToArchivedReturn(ReturnRequestEntity entity) {
		if (entity == null)
			return null;

		ArchivedReturn archivedReturn = new ArchivedReturn();

		archivedReturn.setRequestId(entity.getReturnRequestId());
		archivedReturn.setRequestStatus(entity.getRequestStatus());
		archivedReturn.setRequestDate(entity.getReturnDate());
		archivedReturn.setPackageType(getPackage(entity.getReturnRequestId()).getPackageType().getPackagetype());
		//TODO waiting for implementation cancellation reason story to return 
		archivedReturn.setCancellationReason("");
		archivedReturn.setRequestType("RETURN");
		if (entity.getHubId() != null) {
			archivedReturn.setHubName(getBuildingInfo(entity.getHubId()).getBuildingname());
		}		
		

		return archivedReturn;

	}

	public final ReturnRequest mapToReturnRequest(ReturnRequestEntity returnRequestEntity) {
		if (returnRequestEntity == null)
			return null;

		UsersEntity requester = usersRepository.findOne(returnRequestEntity.getRequester().getCustomerId());

		ReturnRequest returnRequest = new ReturnRequest();
		returnRequest.setReturnDate(returnRequestEntity.getReturnDate());
		returnRequest.setReturnTimeFrom(returnRequestEntity.getTimeFrom());
		returnRequest.setReturnTimeTo(returnRequestEntity.getTimeTo());
		returnRequest
				.setPackageType(getPackage(returnRequestEntity.getReturnRequestId()).getPackageType().getPackagetype());
		returnRequest.setRequestId(returnRequestEntity.getReturnRequestId());
		returnRequest.setReturnAddress(returnRequestEntity.getReturnFormattedAddress());
		returnRequest.setRequestStatus(returnRequestEntity.getRequestStatus());
		returnRequest.setRequesterName(requester.getUsername());
		returnRequest.setRequesterMobile(requester.getPhone());
		if (returnRequestEntity.getHubId() != null) {
			BuildingEntity buildingEntity = getBuildingInfo(returnRequestEntity.getHubId());
			if (buildingEntity != null) {
				returnRequest.setHubId(buildingEntity.getBuildingId());
				returnRequest.setHubName(buildingEntity.getBuildingname());
			}
		}
		return returnRequest;
	}

	private BuildingEntity getBuildingInfo(Long hubId) {
		BuildingEntity buildingEntity = buildingRepository.findOne(hubId);
		return buildingEntity;
	}

	private VerifiedPackageEntity getPackage(Long requestId) {
		return requestHistoryRepository.findByRequestId(requestId).get(0).getPackageEntity().getVerifiedPackage();
	}

	public final ReturnRequestDetails mapToReturnRequestDetails(ReturnRequestEntity returnRequestEntity) {
		if (returnRequestEntity == null)
			return null;
		UsersEntity recipient = null;
		VerifiedPackageEntity verifiedPackageEntity = null;
		if (returnRequestEntity.getRecipient() != null)
			recipient = usersRepository.findOne(returnRequestEntity.getRecipient().getCustomerId());

		verifiedPackageEntity = getPackage(returnRequestEntity.getReturnRequestId());
		UsersEntity requester = usersRepository.findOne(returnRequestEntity.getRequester().getCustomerId());
		ReturnRequestDetails returnRequestDetails = new ReturnRequestDetails();

		returnRequestDetails.setReturnDate(returnRequestEntity.getReturnDate());
		returnRequestDetails.setReturnTimeFrom(returnRequestEntity.getTimeFrom());
		returnRequestDetails.setReturnTimeTo(returnRequestEntity.getTimeTo());
		returnRequestDetails.setPackageType(verifiedPackageEntity.getPackageType().getPackagetype());
		returnRequestDetails.setPackageValue(verifiedPackageEntity.getPackageValue());
		returnRequestDetails.setRequestId(returnRequestEntity.getReturnRequestId());
		returnRequestDetails.setReturnAddress(returnRequestEntity.getReturnFormattedAddress());
		returnRequestDetails.setRequestStatus(returnRequestEntity.getRequestStatus());
		returnRequestDetails.setRequesterName(requester.getUsername());
		returnRequestDetails.setRequesterMobile(requester.getPhone());

		return returnRequestDetails.setRequesterId(returnRequestEntity.getRequester().getCustomerId())
				.setAdditionalServices(returnRequestEntity.getAdditionalservices())
				.setRequestTime(returnRequestEntity.getRequesttime())
				.setPickupLatitude(returnRequestEntity.getPickuplatitude())
				.setPickupLongitude(returnRequestEntity.getPickuplongitude())
				.setPickupWaselLocation(returnRequestEntity.getPickupwasellocation())
				.setPickupFormattedAddress(returnRequestEntity.getPickupformatedaddress())
				.setRecipientId(recipient != null ? recipient.getUserId() : null)
				.setRecipientName(recipient != null ? recipient.getUsername() : returnRequestEntity.getRecipientname())
				.setRecipientMobile(recipient != null ? recipient.getPhone() : returnRequestEntity.getRecipientmobile())
				.setRecipientLongitude(returnRequestEntity.getRecipientlongitude())
				.setRecipientLatitude(returnRequestEntity.getRecipientlatitude())
				.setRecipientWaselLocation(returnRequestEntity.getRecipientwasellocation())
				.setRecipientFormattedAddress(returnRequestEntity.getRecipientformatedaddress())
				.setRecipientAdditionalInfo(returnRequestEntity.getRecipientadditionalinfo())
				.setLabelingText(returnRequestEntity.getLabelingtext())
				.setPaymentType(returnRequestEntity.getPaymenttype())
				.setPaymentMethod(returnRequestEntity.getPaymentmethod())
				.setDescription(returnRequestEntity.getDescription()).setCreated(returnRequestEntity.getCreated())
				.setPackageId(verifiedPackageEntity.getPackageId())
				.setReturnStatus(ReturnStatus.valueOf(returnRequestEntity.getRequestStatus()))
				.setPickupDate(returnRequestEntity.getPickupdate())
				.setReturnLatitude(returnRequestEntity.getReturnLatitude())
				.setReturnLongitude(returnRequestEntity.getReturnLongitude())
				.setReturnDescription(returnRequestEntity.getReturnDescription());
	}

	public final RequestHistoryTimeLine mapToTimeLine(RequestHistoryEntity requestHistoryEntity) {
		if (requestHistoryEntity == null)
			return null;
		return new RequestHistoryTimeLine(requestHistoryEntity.getRequestStatus(), requestHistoryEntity.getCreated());
	}
}
