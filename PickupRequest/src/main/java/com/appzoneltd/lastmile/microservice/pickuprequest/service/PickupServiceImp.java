package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.*;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequestRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.*;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.*;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.UpdateRequestModel;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.*;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PickupServiceImp {
    private final RequestHistoryRepositoryImp requestHistoryRepository;
    private final PickupRequestRepositoryImp requestRepository;
    private final CancelationRequestRepositoryImp cancelationRequestRepositoryImp;
    private final VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository;
    private final UsersRepository usersRepository;
    private final PickupRequestJpaRepository pickupRequestJpaRepository;
    private final RequestHistoryJpaRepository requestHistoryJpaRepository;
    private final PackageJpaRepository packageJpaRepository;
    private final ShipmentServiceTypeJpaRepository shipmentServiceTypeJpaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PickupServiceImp(RequestHistoryRepositoryImp requestHistoryRepository,
                            PickupRequestRepositoryImp requestRepository,
                            CancelationRequestRepositoryImp cancelationRequestRepositoryImp,
                            VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository, UsersRepository usersRepository, PickupRequestJpaRepository pickupRequestJpaRepository, RequestHistoryJpaRepository requestHistoryJpaRepository, PackageJpaRepository packageJpaRepository, ShipmentServiceTypeJpaRepository shipmentServiceTypeJpaRepository) {
        this.requestHistoryRepository = requestHistoryRepository;
        this.requestRepository = requestRepository;
        this.cancelationRequestRepositoryImp = cancelationRequestRepositoryImp;
        this.verifiedPickupRequestRepository = verifiedPickupRequestRepository;
        this.usersRepository = usersRepository;
        this.pickupRequestJpaRepository = pickupRequestJpaRepository;
        this.requestHistoryJpaRepository = requestHistoryJpaRepository;
        this.packageJpaRepository = packageJpaRepository;
        this.shipmentServiceTypeJpaRepository = shipmentServiceTypeJpaRepository;
        objectMapper = new ObjectMapper();
    }

    public Long savePickupRequest(PickupRequest pickupRequest) {
        Long orderId = Utils.generateUUID();

        Long receipentId = findCustomerByMobileNumber(pickupRequest.getRecipientMobile());

        int status = requestRepository.save(orderId, requestRepository.getRequestTypeId(pickupRequest.getRequestType()),
                pickupRequest.getRequestTime(), pickupRequest.getRequesterId(), pickupRequest.getPickupLongitude(),
                pickupRequest.getPickupLatitude(), pickupRequest.getPickupWaselLocation(),
                pickupRequest.getPickupFormatedAddress(), pickupRequest.getTimeFrom(), pickupRequest.getTimeTo(),
                pickupRequest.getPickupDate(), receipentId, pickupRequest.getRecipientName(),
                pickupRequest.getRecipientMobile(), pickupRequest.getRecipientLongitude(),
                pickupRequest.getRecipientLatitude(), pickupRequest.getRecipientWaselLocation(),
                pickupRequest.getRecipientFormatedAddress(), pickupRequest.getRecipientAdditionalInfo(),
                pickupRequest.getAdditionalServices(), pickupRequest.getLabelingText(), null,
                pickupRequest.getPaymentType(), pickupRequest.getPaymentMethod(), pickupRequest.getDescription(), pickupRequest.getCountryCode());
        if (status == 1)
            status = requestHistoryRepository.save(Utils.generateUUID(), pickupRequest.getPackageId(), null, orderId,
                    "PICKUP");

        if (status == 1)
            return orderId;
        else
            requestRepository.delete(orderId);

        return null;
    }

    private Long findCustomerByMobileNumber(String mobileNumber) {
        UsersEntity usersEntity = usersRepository.findByPhoneAndUserTypeId(mobileNumber, 8L);
        if (usersEntity != null)
            return usersEntity.getUserId();
        return null;
    }

    public PickupRequestInfo pickupRequestFindById(Long pickupRequestId) {
        PickupRequestInfo pickupRequestInfo = requestRepository.queryforObject(pickupRequestId);
//        if (pickupRequestInfo.getOrderStatus().equals(OrderStatus.CANCELED))
//            pickupRequestInfo.setCancellationReason(
//                    cancelationRequestRepositoryImp.getCancellationReasonByRequestId(pickupRequestId));
        return pickupRequestInfo;
    }

    public List<MyPickupRequestDTO> getRequestForUser(Principal principal) {

        List<MyPickupRequestDTO> myPickupRequestDTOs = new ArrayList<>();
        myPickupRequestDTOs.addAll(getPickupRequestForUser(principal));
        myPickupRequestDTOs.addAll(getDeliveryRequestForUser(principal));
        myPickupRequestDTOs.addAll(getReturnRequestForUser(principal));

        return myPickupRequestDTOs;
    }

    public List<MyPickupRequestDTO> getPickupRequestForUser(Principal principal) {
        return requestRepository.findAllRequestByUserId(getUserId(principal.getName()));
    }

    public List<MyPickupRequestDTO> getDeliveryRequestForUser(Principal principal) {
        return requestRepository.findAllDeliveryRequestByUserId(getUserId(principal.getName()));
    }

    public List<MyPickupRequestDTO> getReturnRequestForUser(Principal principal) {
        return requestRepository.findAllReturnRequestByUserId(getUserId(principal.getName()));
    }

    private Long getUserId(String credentials) {
        MyPrincipal myPrincipal = null;
        try {

            myPrincipal = objectMapper.readValue(credentials, MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPrincipal != null ? myPrincipal.getUserId() : null;
    }

    public PackagePickupLocation getPackageLocation(Long packageId) {
        return requestRepository.getPackageLocation(packageId);
    }

    public PackagePickupRequestInfo getPackagePickupRequestInfo(Long packageId) {
        return requestRepository.getPackagePickupRequestInfo(packageId);
    }

    public boolean changePickupRequestStatus(Long packageId, String status, String cancelReason) {
        // Getting PickupRequestData
        PickupRequestData pickupRequestData = requestRepository.getPickupRequestByPackage(packageId);
        // check if Found
        if (pickupRequestData != null) {
            // Change pickUpRequest Status
            int result1 = 0;

            if (cancelReason == null) {
                result1 = requestRepository.updatePickupRequestStatus(pickupRequestData.getRequestId(), status);
            } else {
                result1 = requestRepository.updatePickupRequestStatusWithReason(pickupRequestData.getRequestId(),
                        status, cancelReason);
            }

            /// Register to request-History
            if (result1 == 1) {
                int result2 = requestHistoryRepository.save(Utils.generateUUID(), packageId, status,
                        pickupRequestData.getRequestId(), "PICKUP");
                // check if Second update is Executed
                if (result2 == 1) {
                    return true;
                } // end if
            } // end if
            // Default return of status
            return false;
        } // end if condition
        return false;
    }

    public boolean assignPackageToHub(Long packageId, Long hubId) {
        // Getting PickupRequestData
        PickupRequestData pickupRequestData = requestRepository.getPickupRequestByPackage(packageId);
        // check if Found
        if (pickupRequestData != null) {
            // Change pickUpRequest Status
            int result = requestRepository.assignRequestToHub(pickupRequestData.getRequestId(), hubId);
            /// Register to request-History
            if (result == 1) {
                return true;
            } // end Inner if
        } // end Outer IF
        // return result
        return false;
    }

    public Long getPackageHubId(Long packageId) {
        // Getting PickupRequestData
        PickupRequestData pickupRequestData = requestRepository.getPickupRequestByPackage(packageId);
        // return hubId
        return pickupRequestData.getHubId();
    }

    public Long getPackageCustomerId(Long packageId) {
        // Getting PickupRequestData
        PickupRequestData pickupRequestData = requestRepository.getPickupRequestByPackage(packageId);
        // return hubId
        return pickupRequestData.getRequesterId();
    }

    public PackageOrderInfoModel getPackageOrderInfo(Long packageId) {
        return requestRepository.getPackageOrderInfo(packageId);
    }
    // public List<TodayPickupRequest> pickupRequestFindAllTodayPickups(String
    // requestType) {
    // StringBuilder sqlQuery = new
    // StringBuilder(PICKUP_REQUEST_FIND_ALL_TODAY_PICKUPS);
    //
    // if (requestType != null && !requestType.equalsIgnoreCase("")) {
    // sqlQuery.append("AND requesttype='");
    // sqlQuery.append(requestType);
    // sqlQuery.append("'");
    // }
    //
    // List<TodayPickupRequest> todayPickupRequests = query(sqlQuery.toString(),
    // companyId, new TodayPickupRequest());
    // return todayPickupRequests;
    // }

    public boolean ratingDriverAndService(RequestRating requestRating) {
        boolean result = false;
        Long requestId = requestRepository.getRequestId(requestRating.getPackageId());
        int value1 = requestRepository.ServiceRating(requestId, RequestType.PICKUP.getRequestType(),
                requestRating.getServiceRate());
        int value2 = requestRepository.DriverRating(requestRating.getDriverId(), requestId,
                RequestType.PICKUP.getRequestType(), requestRating.getDriverRate());

        int value3 = requestRepository.updateDriverRating(calculateDriverRating(requestRating.getDriverId()),
                requestRating.getDriverId());

        if (value1 == 1 && value2 == 1) {
            result = true;
        }

        return result;

    }

    public double calculateDriverRating(Long driverId) {

        int numDriverRating = requestRepository.countDriverRating(driverId);
        double sumDriverRating = requestRepository.sumDriverRating(driverId);
        double ratingValue = sumDriverRating / numDriverRating;
        return ratingValue;
    }

    public Long requestIdForPackage(Long packageId)
            throws EntityNotFoundException {
        int result = 0;

        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryJpaRepository.findByPackageId(packageId);
        if (requestRepository.isAlreadyExistsWithoutStatus("lastmile_request.request_cancellation", "request_id",
                requestHistoryEntities.get(0).getRequestId())) {
            throw new EntityNotFoundException("Pickuprequest.cancel.request");
        }

        return requestHistoryEntities.get(0).getRequestId();
    }

    public Boolean checkTrackedPickupRequest(Long requestId) {
        Boolean isTracked = false;
        Integer count = requestRepository.queryCountOfTrackedPickupRequest(requestId);
        if (count != null && count > 0)
            isTracked = true;

        return isTracked;
    }

    public VerifiedPickupRequestEntity getVerifiedPickupRequest(Long pickupRequestId) throws EntityNotFoundException {
        VerifiedPickupRequestEntity verifiedPickupRequestEntity = verifiedPickupRequestRepository
                .findOne(pickupRequestId);
        if (verifiedPickupRequestEntity == null)
            throw new EntityNotFoundException("Request Not Found");
        return verifiedPickupRequestEntity;
    }

    public boolean scheduleOndemandPickupRequest(Long packageId, Long requestId, Date pickupDate, String timeFrom,
                                                 String timeTo) {
        PickupRequestInfo pickupRequestInfo = requestRepository.queryforObject(requestId);
        requestRepository.delete(pickupRequestInfo.getPickupRequestId());
        pickupRequestInfo.setRequestType("SCHEDULED");
        pickupRequestInfo.setPickupDate(pickupDate);
        pickupRequestInfo.setTimeFrom(timeFrom);
        pickupRequestInfo.setTimeTo(timeTo);
        pickupRequestInfo.setOrderStatus(OrderStatus.SCHEDULED_FOR_PICKUP);
        Long result = convertOndemandToScheduled(pickupRequestInfo);
        if (result != null)
            return true;
        return false;
    }

    public Long convertOndemandToScheduled(PickupRequest pickupRequest) {
        int status = requestRepository.save(pickupRequest.getPickupRequestId(),
                requestRepository.getRequestTypeId(pickupRequest.getRequestType()), pickupRequest.getRequestTime(),
                pickupRequest.getRequesterId(), pickupRequest.getPickupLongitude(), pickupRequest.getPickupLatitude(),
                pickupRequest.getPickupWaselLocation(), pickupRequest.getPickupFormatedAddress(),
                pickupRequest.getTimeFrom(), pickupRequest.getTimeTo(), pickupRequest.getPickupDate(),
                pickupRequest.getRecipientId(), pickupRequest.getRecipientName(), pickupRequest.getRecipientMobile(),
                pickupRequest.getRecipientLongitude(), pickupRequest.getRecipientLatitude(),
                pickupRequest.getRecipientWaselLocation(), pickupRequest.getRecipientFormatedAddress(),
                pickupRequest.getRecipientAdditionalInfo(), pickupRequest.getAdditionalServices(),
                pickupRequest.getLabelingText(), pickupRequest.getOrderStatus().name(), pickupRequest.getPaymentType(),
                pickupRequest.getPaymentMethod(), pickupRequest.getDescription());
        if (status == 1)
            status = requestHistoryRepository.save(Utils.generateUUID(), pickupRequest.getPackageId(),
                    pickupRequest.getOrderStatus().name(), pickupRequest.getPickupRequestId(), "PICKUP");

        if (status == 1)
            return pickupRequest.getPickupRequestId();
        else
            requestRepository.delete(pickupRequest.getPickupRequestId());

        return null;
    }

    public void updateRequest(UpdateRequestModel updateRequestModel) throws NotUpdatableRequest, EntityNotFoundException {
        List<RequestHistoryEntity> historyEntities = requestHistoryJpaRepository.findByRequestId(updateRequestModel.getPickupRequestId());
        boolean isUpdatable = true;

        for (RequestHistoryEntity requestHistoryEntity : historyEntities) {
            if ("DELIVERY".equalsIgnoreCase(requestHistoryEntity.getRequestType()) || "RETURN".equalsIgnoreCase(requestHistoryEntity.getRequestType()) || "TRANSIT".equalsIgnoreCase(requestHistoryEntity.getRequestType())) {
                isUpdatable = false;
                break;
            }
        }

        if (!isUpdatable)
            throw new NotUpdatableRequest("not.updatable.request");

        PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(updateRequestModel.getPickupRequestId());

        if (pickupRequestEntity == null)
            throw new EntityNotFoundException("Request Not Found");

        PackageEntity packageEntity = historyEntities.get(0).getPackageEntity();

        saveUpdatedRequest(updateRequestModel, pickupRequestEntity);
        saveUpdatedPckage(updateRequestModel, packageEntity);

        packageJpaRepository.save(packageEntity);
    }

    private void saveUpdatedRequest(UpdateRequestModel updateRequestModel, PickupRequestEntity pickupRequestEntity) {
        if (!"".equalsIgnoreCase(updateRequestModel.getTimeFrom()))
            pickupRequestEntity.setTimeFrom(updateRequestModel.getTimeFrom());
        if (!"".equalsIgnoreCase(updateRequestModel.getTimeTo()))
            pickupRequestEntity.setTimeTo(updateRequestModel.getTimeTo());
        if (updateRequestModel.getPickupDate() != null)
            pickupRequestEntity.setPickupdate(updateRequestModel.getPickupDate());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientName()))
            pickupRequestEntity.setRecipientname(updateRequestModel.getRecipientName());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientMobile())) {
            pickupRequestEntity.setRecipientmobile(updateRequestModel.getRecipientMobile());
            pickupRequestEntity.setRecipientId(findCustomerByMobileNumber(updateRequestModel.getRecipientMobile()));
        }
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientLongitude()))
            pickupRequestEntity.setRecipientlongitude(updateRequestModel.getRecipientLongitude());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientLatitude()))
            pickupRequestEntity.setRecipientlatitude(updateRequestModel.getRecipientLatitude());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientWaselLocation()))
            pickupRequestEntity.setRecipientwasellocation(updateRequestModel.getRecipientWaselLocation());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientFormatedAddress()))
            pickupRequestEntity.setRecipientformatedaddress(updateRequestModel.getRecipientFormatedAddress());
        if (!"".equalsIgnoreCase(updateRequestModel.getRecipientAdditionalInfo()))
            pickupRequestEntity.setRecipientadditionalinfo(updateRequestModel.getRecipientAdditionalInfo());
        if (updateRequestModel.getPickupServiceTypeId() != null)
            pickupRequestEntity.setPickupServiceTypeId(updateRequestModel.getPickupServiceTypeId());
        if (!"".equalsIgnoreCase(updateRequestModel.getAdditionalServices()))
            pickupRequestEntity.setAdditionalservices(updateRequestModel.getAdditionalServices());
        if (!"".equalsIgnoreCase(updateRequestModel.getLabelingText()))
            pickupRequestEntity.setLabelingtext(updateRequestModel.getLabelingText());
        if (!"".equalsIgnoreCase(updateRequestModel.getPaymentType()))
            pickupRequestEntity.setPaymenttype(updateRequestModel.getPaymentType());
        if (!"".equalsIgnoreCase(updateRequestModel.getPaymentMethod()))
            pickupRequestEntity.setPaymentmethod(updateRequestModel.getPaymentMethod());

        pickupRequestJpaRepository.save(pickupRequestEntity);
    }

    private void saveUpdatedPckage(UpdateRequestModel updateRequestModel, PackageEntity packageEntity) {
        if (!"".equalsIgnoreCase(updateRequestModel.getNickName()))
            packageEntity.setNickname(updateRequestModel.getNickName());
        if (!"".equalsIgnoreCase(updateRequestModel.getDescription()))
            packageEntity.setDescription(updateRequestModel.getDescription());
        if (updateRequestModel.getShipmentServiceTypeId() != null)
            packageEntity.setShipmentServiceType(shipmentServiceTypeJpaRepository.findOne(updateRequestModel.getShipmentServiceTypeId()));
        if (!"".equalsIgnoreCase(updateRequestModel.getPackageValue()))
            packageEntity.setPackageValue(updateRequestModel.getPackageValue());
        List<PackageImagesEntity> packageImagesEntities = new ArrayList<>();
        updateRequestModel.getImageIds().forEach(id -> packageImagesEntities.add(new PackageImagesEntity(packageEntity.getPackageId(), id)));
        if (!packageImagesEntities.isEmpty())
            packageEntity.setListOfPackageImages(packageImagesEntities);
    }
}
