package com.appzoneltd.lastmile.microservice.delivery.dto;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@Component
public final class CustomerDtoMapper {

    @Autowired
    private RequestHistoryRepository requestHistoryJpaRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PickupRequestJpaRepository pickupRequestJpaRepository;

    public final CustomerRequestDetails requestToCustomerRequestDetails(DeliveryRequestEntity deliveryRequestEntity) {
        if (deliveryRequestEntity == null)
            return null;


        PackageEntity packageEntity = requestHistoryJpaRepository.findByRequestId(deliveryRequestEntity.getDeliveryRequestId()).get(0).getPackageEntity();

        UsersEntity usersEntity = null;
        if (deliveryRequestEntity.getRecipient() != null)
            usersEntity = usersRepository.findOne(deliveryRequestEntity.getRecipient().getCustomerId());

        PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(deliveryRequestEntity.getDeliveryRequestId());

        CustomerRequestDetails customerRequestDetails = new CustomerRequestDetails();
        customerRequestDetails.setId(deliveryRequestEntity.getDeliveryRequestId());
        customerRequestDetails.setPackageId(packageEntity.getPackageId());
        customerRequestDetails.setPaymentAt(deliveryRequestEntity.getPaymenttype());
        customerRequestDetails.setPaymentMethod(deliveryRequestEntity.getPaymentmethod());
        customerRequestDetails.setConfirmationCode(deliveryRequestEntity.getConfirmationCode());
        customerRequestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
        customerRequestDetails.setShipmentServiceType(requestHistoryJpaRepository.findByRequestId(deliveryRequestEntity.getDeliveryRequestId()).get(0).getPackageEntity().getShipmentServiceType().getType());
        customerRequestDetails.setPickupDate(pickupRequestEntity.getPickupdate());
        customerRequestDetails.setPickupTime(pickupRequestEntity.getTimeFrom() == null ? "Now" : pickupRequestEntity.getTimeFrom() + " - " + pickupRequestEntity.getTimeTo());
        customerRequestDetails.setPickupAddress(deliveryRequestEntity.getPickupformatedaddress());
        customerRequestDetails.setNickName(packageEntity.getNickname());
        customerRequestDetails.setPackageType(packageEntity.getVerifiedPackage().getPackageType().getPackagetype());
        customerRequestDetails.setPackageValue(packageEntity.getVerifiedPackage().getPackageValue());
        customerRequestDetails.setPackageWeight(packageEntity.getVerifiedPackage().getActualweight().toEngineeringString());
        customerRequestDetails.setWhatInside(packageEntity.getDescription());
        customerRequestDetails.setAdditionalServices(deliveryRequestEntity.getAdditionalservices());
        customerRequestDetails.setRecipientName(deliveryRequestEntity.getRecipientname());
        customerRequestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : deliveryRequestEntity.getRecipientmobile());
        customerRequestDetails.setRecipientLocation(deliveryRequestEntity.getRecipientformatedaddress());
        customerRequestDetails.setAdditionalNotes(deliveryRequestEntity.getDescription());
        customerRequestDetails.setDeliveryDate(deliveryRequestEntity.getDeliverydate());
        customerRequestDetails.setDeliveryTime(deliveryRequestEntity.getTimeFrom() + " - " + deliveryRequestEntity.getTimeTo());
        customerRequestDetails.setImageIds(packageEntity.getListOfPackageImages().stream().map(image -> image.getImageId()).collect(Collectors.toList()));
        customerRequestDetails.setTimeLine(requestHistoryJpaRepository.listRequestHistoryTimeLine(deliveryRequestEntity.getDeliveryRequestId()).stream().map(entity -> new PackageTimeLineDto(entity.getRequestStatus(), entity.getCreated())).collect(Collectors.toList()));
        customerRequestDetails.setRequestType("DELIVERY");
        customerRequestDetails.setLatitude(deliveryRequestEntity.getRecipientlatitude());
        customerRequestDetails.setLongitude(deliveryRequestEntity.getRecipientlongitude());
        
        customerRequestDetails.setSenderId(deliveryRequestEntity.getRequester().getCustomerId());
        customerRequestDetails.setRecipientId(deliveryRequestEntity.getRecipient().getCustomerId());

        return customerRequestDetails;
    }


}
