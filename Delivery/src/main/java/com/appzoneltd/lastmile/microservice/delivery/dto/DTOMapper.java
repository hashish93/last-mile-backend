package com.appzoneltd.lastmile.microservice.delivery.dto;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.*;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.BuildingRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
@Component
@Scope("singleton")
public class DTOMapper {
    private final RequestHistoryRepository requestHistoryRepository;
    private final UsersRepository usersRepository;
    private final BuildingRepository buildingRepository;

    @Autowired
    public DTOMapper(RequestHistoryRepository requestHistoryRepository, UsersRepository usersRepository, BuildingRepository buildingRepository) {
        this.requestHistoryRepository = requestHistoryRepository;
        this.usersRepository = usersRepository;
        this.buildingRepository = buildingRepository;
    }

    public final DeliveryRequest mapToDeliveryRequest(ExtendedDeliveryRequestEntity deliveryRequestEntity) {
        if (deliveryRequestEntity == null)
            return null;

        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setDeliveryDate(deliveryRequestEntity.getDeliverydate());
        deliveryRequest.setDeliveryTimeFrom(deliveryRequestEntity.getTimeFrom());
        deliveryRequest.setDeliveryTimeTo(deliveryRequestEntity.getTimeTo());
        deliveryRequest.setPackageType(getVerifiedPackage(deliveryRequestEntity.getDeliveryRequestId()).getPackageType()
                .getPackagetype());
        deliveryRequest.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
        deliveryRequest.setRecipientAddress(deliveryRequestEntity.getRecipientformatedaddress());
        deliveryRequest.setRequestStatus(deliveryRequestEntity.getRequestStatus());
        deliveryRequest.setSenderAddress(deliveryRequestEntity.getPickupformatedaddress());

        if (deliveryRequestEntity.getHubId() != null) {
            BuildingEntity buildingEntity = buildingRepository.findOne(deliveryRequestEntity.getHubId());
            if (buildingEntity != null) {
                deliveryRequest.setHubId(buildingEntity.getBuildingId());
                deliveryRequest.setHubName(buildingEntity.getBuildingname());
            }
        }

        Boolean isInPlan = deliveryRequestEntity.getActiveVehicleId() != null;
        deliveryRequest.setInTodaysPlan(isInPlan);

        return deliveryRequest;
    }

    private VerifiedPackageEntity getVerifiedPackage(Long requestId) {
        return requestHistoryRepository.findByRequestId(requestId).get(0)
                .getPackageEntity()
                .getVerifiedPackage();
    }

    public final DeliveryRequestDetails mapToDeliveryRequestDetails(DeliveryRequestEntity deliveryRequestEntity) {
        if (deliveryRequestEntity == null)
            return null;
        UsersEntity recipient = null;
        VerifiedPackageEntity verifiedPackageEntity = null;
        if (deliveryRequestEntity.getRecipient() != null)
            recipient = usersRepository.findOne(deliveryRequestEntity.getRecipient().getCustomerId());

        verifiedPackageEntity = getVerifiedPackage(deliveryRequestEntity.getDeliveryRequestId());
        UsersEntity requester = usersRepository.findOne(deliveryRequestEntity.getRequester().getCustomerId());
        DeliveryRequestDetails deliveryRequestDetails = new DeliveryRequestDetails();

        deliveryRequestDetails.setDeliveryDate(deliveryRequestEntity.getDeliverydate());
        deliveryRequestDetails.setDeliveryTimeFrom(deliveryRequestEntity.getTimeFrom());
        deliveryRequestDetails.setDeliveryTimeTo(deliveryRequestEntity.getTimeTo());
        deliveryRequestDetails.setPackageType(verifiedPackageEntity.getPackageType().getPackagetype());
        deliveryRequestDetails.setPackageValue(verifiedPackageEntity.getPackageValue());
        deliveryRequestDetails.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
        deliveryRequestDetails.setRecipientAddress(deliveryRequestEntity.getRecipientformatedaddress());
        deliveryRequestDetails.setRequestStatus(deliveryRequestEntity.getRequestStatus());
        deliveryRequestDetails.setSenderAddress(deliveryRequestEntity.getPickupformatedaddress());

        return deliveryRequestDetails.setRequesterId(deliveryRequestEntity.getRequester().getCustomerId())
                .setAdditionalServices(deliveryRequestEntity.getAdditionalservices())
                .setRequestTime(deliveryRequestEntity.getRequesttime())
                .setPickupLatitude(deliveryRequestEntity.getPickuplatitude())
                .setPickupLongitude(deliveryRequestEntity.getPickuplongitude())
                .setPickupWaselLocation(deliveryRequestEntity.getPickupwasellocation())
                .setPickupFormattedAddress(deliveryRequestEntity.getPickupformatedaddress())
                .setRecipientId(recipient != null ? recipient.getUserId() : null)
                .setRecipientName(recipient != null ? recipient.getUsername() : deliveryRequestEntity.getRecipientname())
                .setRecipientMobile(recipient != null ? recipient.getPhone() : deliveryRequestEntity.getRecipientmobile())
                .setRecipientLongitude(deliveryRequestEntity.getRecipientlongitude())
                .setRecipientLatitude(deliveryRequestEntity.getRecipientlatitude())

                .setRecipientWaselLocation(deliveryRequestEntity.getRecipientwasellocation())
                .setRecipientFormattedAddress(deliveryRequestEntity.getRecipientformatedaddress())
                .setRecipientAdditionalInfo(deliveryRequestEntity.getRecipientadditionalinfo())
                .setLabelingText(deliveryRequestEntity.getLabelingtext())
                .setPaymentType(deliveryRequestEntity.getPaymenttype())
                .setPaymentMethod(deliveryRequestEntity.getPaymentmethod())
                .setDescription(deliveryRequestEntity.getDescription())
                .setCreated(deliveryRequestEntity.getCreated())
                .setPackageId(verifiedPackageEntity.getPackageId())
                .setDeliveryStatus(DeliveryStatus.valueOf(deliveryRequestEntity.getRequestStatus()))
                .setRequesterName(requester.getUsername())
                .setRequesterMobile(requester.getPhone())
                .setPickupDate(deliveryRequestEntity.getPickupdate());
    }

    public final RequestHistoryTimeLine mapToTimeLine(RequestHistoryEntity requestHistoryEntity) {
        if (requestHistoryEntity == null)
            return null;
        return new RequestHistoryTimeLine(requestHistoryEntity.getRequestStatus(), requestHistoryEntity.getCreated());
    }

    public final CustomerDeliveryRequest mapToCustomerDelivery(DeliveryRequestEntity deliveryRequestEntity) {
        if (deliveryRequestEntity == null)
            return null;

        CustomerDeliveryRequest customerDeliveryRequest = new CustomerDeliveryRequest();
        customerDeliveryRequest.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
        customerDeliveryRequest.setNickname(getPackage(deliveryRequestEntity.getDeliveryRequestId()).getNickname());
        customerDeliveryRequest.setSenderName(usersRepository.findOne(deliveryRequestEntity.getRequester().getCustomerId()).getUsername());
        customerDeliveryRequest.setSenderAddress(deliveryRequestEntity.getPickupformatedaddress());
        customerDeliveryRequest.setStatus(deliveryRequestEntity.getRequestStatus());
        customerDeliveryRequest.setLongitude(deliveryRequestEntity.getRecipientlongitude());
        customerDeliveryRequest.setLatitude(deliveryRequestEntity.getRecipientlatitude());
        return customerDeliveryRequest;
    }

    private PackageEntity getPackage(Long requestId) {
        return requestHistoryRepository.findByRequestId(requestId).get(0)
                .getPackageEntity();
    }

    public RequestHistoryDTO mapToHistoryDTO(DeliveryRequestEntity deliveryRequestEntity) {
        RequestHistoryDTO requestHistoryDTO = new RequestHistoryDTO();
        requestHistoryDTO.setDeliveryRequestId(deliveryRequestEntity.getDeliveryRequestId());
        requestHistoryDTO.setRequestType("DELIVERY");
        requestHistoryDTO.setPackageType(requestHistoryRepository.findByRequestId(deliveryRequestEntity.getDeliveryRequestId()).get(0).getPackageEntity().getVerifiedPackage().getPackageType().getPackagetype());
        requestHistoryDTO.setDeliveryStatus(deliveryRequestEntity.getRequestStatus());
        requestHistoryDTO.setCancellationReason(null);
        requestHistoryDTO.setHubName(buildingRepository.findOne(deliveryRequestEntity.getHubId()).getBuildingname());
        return requestHistoryDTO;
    }
}
