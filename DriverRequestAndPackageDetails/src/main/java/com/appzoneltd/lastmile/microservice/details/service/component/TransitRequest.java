package com.appzoneltd.lastmile.microservice.details.service.component;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by alaa.nabil on 5/2/2017.
 */
@Component(value = "transitRequest")
public class TransitRequest implements RequestChain {
    @Override
    public LatLng getGoogleLocationLatLng(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public String getFormattedAddress(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public Location getLocationDto(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public BigDecimal getPackageWeight(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public String getPackageType(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public JobOrder fillCouchbaseJobOrderFromPlanOrder(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public Object getNotificationModelWithRequestDetails(Long packageId, Long requestId, String requestType, ActiveVehicleEntity activeVehicleEntity) {
        return null;
    }

    @Override
    public PackageDetails toPackageDetails(Long requestId, String requestType, PackageEntity packageEntity, Long driverId) {
        return null;
    }

    @Override
    public com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(PlanOrderEntity planOrderEntity) {
        return null;
    }

    @Override
    public boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId) throws EntityNotFoundException, JsonProcessingException {
        return false;
    }

    @Override
    public Invoice generateInvoice(Long requestId, String requestType) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void confirmInvoice(Long requestId, String requestType, Long driverId) throws EntityNotFoundException, JsonProcessingException {

    }

    @Override
    public Boolean submitAndAddDocuments(Documents documents, Long activeVehicleId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {
        return null;
    }

    @Override
    public RequestDetails fillOrderDetails(Long requestId, String requestType) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException {

    }

    @Override
    public boolean isCompletedOrder(Long orderId, String orderType, TodaySummary todaySummary) {
        return false;
    }
}
