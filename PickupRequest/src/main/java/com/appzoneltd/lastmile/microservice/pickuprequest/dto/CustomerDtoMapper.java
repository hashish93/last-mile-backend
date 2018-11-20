package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle.RegistrationModel;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.UsersRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.activevehicle.ActiveVehicleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@Component
public final class CustomerDtoMapper {
    private final RequestHistoryJpaRepository requestHistoryJpaRepository;
    private final UsersRepository usersRepository;
    private final ActiveVehicleJpaRepository activeVehicleRepository;

    @Autowired
    public CustomerDtoMapper(RequestHistoryJpaRepository requestHistoryJpaRepository, UsersRepository usersRepository, ActiveVehicleJpaRepository activeVehicleRepository) {
        this.requestHistoryJpaRepository = requestHistoryJpaRepository;
        this.usersRepository = usersRepository;
        this.activeVehicleRepository = activeVehicleRepository;
    }

    public final CustomerRequestDetails requestToCustomerRequestDetails(PickupRequestEntity pickupRequestEntity) {
        PackageEntity packageEntity = null;
        UsersEntity usersEntity = null;
        if (pickupRequestEntity != null)
            packageEntity = requestHistoryJpaRepository.findByRequestId(pickupRequestEntity.getPickupRequestId()).get(0).getPackageEntity();


        if (pickupRequestEntity.getRecipientId() != null)
            usersEntity = usersRepository.findOne(pickupRequestEntity.getRecipientId());

        CustomerRequestDetails customerRequestDetails = new CustomerRequestDetails();
        customerRequestDetails.setId(pickupRequestEntity.getPickupRequestId());
        customerRequestDetails.setPackageId(packageEntity.getPackageId());
        customerRequestDetails.setPickupRequestType(pickupRequestEntity.getPickupRequestType().getType());
        customerRequestDetails.setPaymentAt(pickupRequestEntity.getPaymenttype());
        customerRequestDetails.setPaymentMethod(pickupRequestEntity.getPaymentmethod());
        customerRequestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
        customerRequestDetails.setShipmentServiceType(requestHistoryJpaRepository.findByRequestId(pickupRequestEntity.getPickupRequestId()).get(0).getPackageEntity().getShipmentServiceType().getType());
        customerRequestDetails.setDate(pickupRequestEntity.getPickupdate());
        customerRequestDetails.setPickupTime(pickupRequestEntity.getTimeFrom() == null ? "Now" : pickupRequestEntity.getTimeFrom() + " - " + pickupRequestEntity.getTimeTo());
        customerRequestDetails.setPickupAddress(pickupRequestEntity.getPickupformatedaddress());
        customerRequestDetails.setNickName(packageEntity.getNickname());
        customerRequestDetails.setPackageType(packageEntity.getPackageType().getPackagetype());
        customerRequestDetails.setPackageValue(packageEntity.getPackageValue());
        customerRequestDetails.setPackageWeight(packageEntity.getActualweight().toEngineeringString());
        customerRequestDetails.setWhatInside(packageEntity.getDescription());
        customerRequestDetails.setAdditionalServices(pickupRequestEntity.getAdditionalservices());
        customerRequestDetails.setRecipientName(pickupRequestEntity.getRecipientname());
        customerRequestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : pickupRequestEntity.getRecipientmobile());
        customerRequestDetails.setRecipientLocation(pickupRequestEntity.getRecipientformatedaddress());
        customerRequestDetails.setAdditionalNotes(pickupRequestEntity.getDescription());
        customerRequestDetails.setImageIds(packageEntity.getListOfPackageImages().stream().map(image -> image.getImageId()).collect(Collectors.toList()));
        customerRequestDetails.setTimeLine(requestHistoryJpaRepository.listRequestHistoryTimeLine(packageEntity.getPackageId()).stream().map(entity -> new PackageTimeLineDto(entity.getRequestStatus(), entity.getCreated())).collect(Collectors.toList()));
        customerRequestDetails.setRequestType("PICKUP");
        customerRequestDetails.setLatitude(pickupRequestEntity.getPickuplatitude());
        customerRequestDetails.setLongitude(pickupRequestEntity.getPickuplongitude());
        
        
        customerRequestDetails.setSenderId(pickupRequestEntity.getRequesterId());
        customerRequestDetails.setRecipientId(pickupRequestEntity.getRecipientId());
        
        return customerRequestDetails;
    }

    public DriverInfo toDriverInfo(RegistrationModel registrationModel) {
        if (registrationModel == null)
            return null;

        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(registrationModel.get_ID());

        if (activeVehicleEntity == null)
            return null;

        DriverInfo driverInfo = new DriverInfo();
        driverInfo.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
        driverInfo.setHubId(activeVehicleEntity.getVehicle().getBuildingId());
        driverInfo.setDriverId(activeVehicleEntity.getDriver().getId());
        driverInfo.setDriverName(activeVehicleEntity.getDriver().getUsers().getUsername());
        driverInfo.setDriverPhoneNumber(activeVehicleEntity.getDevices().getPhonenumber());
        driverInfo.setDriverRating(registrationModel.getRating());
        driverInfo.setDriverImageId(activeVehicleEntity.getDriver().getUsers().getImageId());
        driverInfo.setVehicleModel(activeVehicleEntity.getVehicle().getModel());
        driverInfo.setVehiclePlateNumber(activeVehicleEntity.getVehicle().getPlate());
        return driverInfo;
    }
}
