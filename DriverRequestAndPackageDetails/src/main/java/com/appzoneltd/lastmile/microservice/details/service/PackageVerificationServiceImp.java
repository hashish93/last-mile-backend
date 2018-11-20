package com.appzoneltd.lastmile.microservice.details.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.UsersJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.CancelRequest;
import com.appzoneltd.lastmile.microservice.details.dto.Documents;
import com.appzoneltd.lastmile.microservice.details.dto.Invoice;
import com.appzoneltd.lastmile.microservice.details.dto.PackageDetails;
import com.appzoneltd.lastmile.microservice.details.dto.VehicleStatus;
import com.appzoneltd.lastmile.microservice.details.service.component.ConfirmationCodeError;
import com.appzoneltd.lastmile.microservice.details.service.component.OnDemandPickupRequest;
import com.appzoneltd.lastmile.microservice.details.service.component.RequestChain;
import com.appzoneltd.lastmile.microservice.details.service.utils.ActiveVehicleUtil;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PackageVerificationServiceImp implements PackageVerificationService {

//	private final UsersJpaRepository userRepository;
//	private final CouchbaseTemplate couchbaseTemplate;
	private final RegistrationRepository registrationRepository;
	//    private final ActiveVehicleJpaRepository activeVehicleRepository;
	private final RequestChain requestChain;
	private final OnDemandPickupRequest onDemandPickupRequest;

	private final ActiveVehicleUtil activeVehicleUtil;

	@Autowired
	public PackageVerificationServiceImp(
//			UsersJpaRepository userRepository, 
//			CouchbaseTemplate couchbaseTemplate,
			RegistrationRepository registrationRepository,
			ActiveVehicleUtil activeVehicleUtil
			, ActiveVehicleJpaRepository activeVehicleRepository, 
			@Qualifier(value = "pickupRequest") RequestChain requestChain, 
			OnDemandPickupRequest onDemandPickupRequest) {

//		this.userRepository = userRepository;
//		this.couchbaseTemplate = couchbaseTemplate;
		//        this.activeVehicleRepository = activeVehicleRepository;
		this.requestChain = requestChain;
		this.registrationRepository = registrationRepository;
		this.onDemandPickupRequest = onDemandPickupRequest;

		this.activeVehicleUtil = activeVehicleUtil;
	}

	@Override
	public boolean verifyPackageDetails(PackageDetails details, Principal principal)
			throws EntityNotFoundException, JsonProcessingException {

		//		Long activeVehicleId = getActiveVehicleId(getDriverId(principal.getName()), getNow());

		//        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);
		Long activeVehicleId = activeVehicleEntity.getId();

		if (VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())) {

			details.setRequestType("PICKUP");
			return onDemandPickupRequest.verifyDetails(details, activeVehicleId);

		} else {
			return requestChain.verifyDetails(details, activeVehicleId);
		}
	}

	@Override
	public Invoice generateInvoice(Long requestId, String requestType, Principal principal) throws EntityNotFoundException, JsonProcessingException {

		//    	Long activeVehicleId = getActiveVehicleId(getDriverId(principal.getName()), getNow());
		//        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);

		if (VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())) {
			return onDemandPickupRequest.generateInvoice(requestId);
		} else
			return requestChain.generateInvoice(requestId, requestType);
	}

	@Override
	public void confirmInvoice(Principal principal, Long requestId, String requestType)
			throws EntityNotFoundException, JsonProcessingException {

		//        Long driverId = getDriverId(principal.getName());
		//        Long activeVehicleId = getActiveVehicleId(driverId, getNow());
		//        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);

		if (VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())) {

			onDemandPickupRequest.confirmInvoice(requestId, activeVehicleEntity.getDriver().getId());

		} else
			requestChain.confirmInvoice(requestId, requestType, activeVehicleEntity.getDriver().getId());
	}

	@Override
	public Boolean addDocuments(Documents documents, Principal principal)
			throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {

		//        Long driverId = getDriverId(principal.getName());
		//        Long activeVehicleId = getActiveVehicleId(driverId, getNow());
		//        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);

		boolean result = false;

		result = VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())
				? onDemandPickupRequest.submitAndAddDocuments(documents, activeVehicleEntity.getId())
						: requestChain.submitAndAddDocuments(documents, activeVehicleEntity.getId());

				if (result)
					activeVehicleUtil.updateActiveVehicleStatus(activeVehicleEntity.getId(), VehicleStatus.AVAILABLE);
				return result;
	}

	@Override
	public void cancelRequest(CancelRequest cancelRequest, Principal principal) throws JsonProcessingException {

		//        Long driverId = getDriverId(principal.getName());
		//        Long activeVehicleId = getActiveVehicleId(driverId, getNow());
		//        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);
		Long activeVehicleId = activeVehicleEntity.getId();
		Long driverId = activeVehicleEntity.getDriver().getId();


		activeVehicleUtil.updateActiveVehicleStatus(activeVehicleId, VehicleStatus.AVAILABLE);

		cancelRequest.setDriverId(driverId);
		cancelRequest.setLocation(registrationRepository.findOne(activeVehicleId).getLocation());

		if (VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())) {

			cancelRequest.setRequestType("PICKUP");
			onDemandPickupRequest.cancelRequest(cancelRequest);

		} else {
			requestChain.cancelRequest(cancelRequest);
		}
	}

	//    private void updateActiveVehicleStatus(Long activeVehicleId, VehicleStatus vehicleStatus) {
	//        RegistrationModel model = registrationRepository.findOne(activeVehicleId);
	//        model.setVehicleStatus(vehicleStatus.name());
	//        registrationRepository.save(model);
	//    }

	//	private Date getNow() {
	//		Date now = new Date();
	//		now.setYear(1970);
	//		now.setMonth(1);
	//		now.setDate(1);
	//		now.setSeconds(1);
	//		return now;
	//	}
	//
	//	private Long getDriverId(String principal) {
	//		return userRepository.findByEmailOrPhone(principal, principal).getUserId();
	//	}
	//
	//	private Long getActiveVehicleId(Long driverId, Date now) {
	//		String id = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
	//				+ couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
	//				+ now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows()
	//				.get(0).value().getString("id");
	//
	//		return Long.parseLong(id);
	//	}
}
