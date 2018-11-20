package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.UsersRepository;
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

    public final CustomerRequestDetails requestToCustomerRequestDetails(ReturnRequestEntity returnRequestEntity) {
        if (returnRequestEntity == null)
            return null;


        PackageEntity packageEntity = requestHistoryJpaRepository.findByRequestId(returnRequestEntity.getReturnRequestId()).get(0).getPackageEntity();

        UsersEntity usersEntity = null;
        if (returnRequestEntity.getRecipient() != null)
            usersEntity = usersRepository.findOne(returnRequestEntity.getRecipient().getCustomerId());

        PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(returnRequestEntity.getReturnRequestId());

        CustomerRequestDetails customerRequestDetails = new CustomerRequestDetails();
        customerRequestDetails.setId(returnRequestEntity.getReturnRequestId());
        customerRequestDetails.setPackageId(packageEntity.getPackageId());
        customerRequestDetails.setPaymentAt(returnRequestEntity.getPaymenttype());
        customerRequestDetails.setPaymentMethod(returnRequestEntity.getPaymentmethod());
        customerRequestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
        customerRequestDetails.setShipmentServiceType(requestHistoryJpaRepository.findByRequestId(returnRequestEntity.getReturnRequestId()).get(0).getPackageEntity().getShipmentServiceType().getType());
        customerRequestDetails.setPickupDate(pickupRequestEntity.getPickupdate());
        customerRequestDetails.setPickupTime(pickupRequestEntity.getTimeFrom() == null ? "Now" : pickupRequestEntity.getTimeFrom() + " - " + pickupRequestEntity.getTimeTo());
        customerRequestDetails.setPickupAddress(returnRequestEntity.getPickupformatedaddress());
        customerRequestDetails.setNickName(packageEntity.getNickname());
        customerRequestDetails.setPackageType(packageEntity.getVerifiedPackage().getPackageType().getPackagetype());
        customerRequestDetails.setPackageValue(packageEntity.getVerifiedPackage().getPackageValue());
        customerRequestDetails.setPackageWeight(packageEntity.getVerifiedPackage().getActualweight().toEngineeringString());
        customerRequestDetails.setWhatInside(packageEntity.getDescription());
        customerRequestDetails.setAdditionalServices(returnRequestEntity.getAdditionalservices());
        customerRequestDetails.setRecipientName(returnRequestEntity.getRecipientname());
        customerRequestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : returnRequestEntity.getRecipientmobile());
        customerRequestDetails.setRecipientLocation(returnRequestEntity.getRecipientformatedaddress());
        customerRequestDetails.setAdditionalNotes(returnRequestEntity.getDescription());
        customerRequestDetails.setReturnDate(returnRequestEntity.getReturnDate());
        customerRequestDetails.setReturnTime(returnRequestEntity.getTimeFrom() + " - " + returnRequestEntity.getTimeTo());
        customerRequestDetails.setImageIds(packageEntity.getListOfPackageImages().stream().map(image -> image.getImageId()).collect(Collectors.toList()));
        customerRequestDetails.setTimeLine(requestHistoryJpaRepository.listRequestHistoryTimeLineByPackage(packageEntity.getPackageId()).stream().map(entity -> new PackageTimeLineDto(entity.getRequestStatus(), entity.getCreated())).collect(Collectors.toList()));
        customerRequestDetails.setRequestType("RETURN");
        customerRequestDetails.setReturnFormattedAddress(returnRequestEntity.getReturnFormattedAddress());
        customerRequestDetails.setLatitude(returnRequestEntity.getReturnLatitude());
        customerRequestDetails.setLongitude(returnRequestEntity.getReturnLongitude());
  
        customerRequestDetails.setSenderId(returnRequestEntity.getRequester().getCustomerId());
        if(returnRequestEntity.getRecipient() !=null){
        	customerRequestDetails.setRecipientId(returnRequestEntity.getRecipient().getCustomerId());
        }
        return customerRequestDetails;
    }


}
