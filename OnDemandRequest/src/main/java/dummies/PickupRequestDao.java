
package dummies;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.core.AbstractDao;
import com.appzoneltd.lastmile.microservice.ondemandrequest.dao.PickupRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.TodayPickupRequest;
import com.appzoneltd.lastmile.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import scala.collection.mutable.StringBuilder;

/**
 * @author Alaa Nabil
 *
 */
@Repository
public class PickupRequestDao extends AbstractDao implements PickupRequestDaoInfc {

	/**
	 * Method to save pickupRequest object to database
	 * 
	 * 
	 * @param companyId
	 * @param pickupRequest
	 * @return id of saved object
	 * @throws JsonProcessingException
	 */
	public Long savePickupRequest(long companyId, PickupRequest pickupRequest) throws JsonProcessingException {
		Long pickupRequestId = Utils.generateUUID();
		pickupRequest.setPickupRequestId(pickupRequestId);
		int status = save(PICKUP_REQUEST_CREATE_QUERY, companyId, pickupRequestId, pickupRequest.getRequestType(),
				pickupRequest.getRequesterId(), pickupRequest.getRequesterName(), pickupRequest.getRequesterMobile(),
				pickupRequest.getPickupGoogleLocation(), pickupRequest.getPickupWaselLocation(),
				pickupRequest.getPickupFormatedAddress(), pickupRequest.getPickupTime(), pickupRequest.getPickupDate(),
				pickupRequest.getPackageId(), pickupRequest.getRecipientId(), pickupRequest.getRecipientName(),
				pickupRequest.getRecipientMobile(), pickupRequest.getRecipientGoogleLocation(),
				pickupRequest.getRecipientAdditionalInfo(), pickupRequest.getRecipientWaselLocation(),
				pickupRequest.getRecipientFormatedAddress(), pickupRequest.getPickupServiceTypeId(),
				pickupRequest.getPickupServiceType(), pickupRequest.getAdditionalServices(),
				pickupRequest.getLabelingText(), pickupRequest.getPaymentType(), pickupRequest.getPaymentMethod(),
				pickupRequest.getPickupStatus(), pickupRequest.getPickupStatusId(), pickupRequest.getBranchId(),
				pickupRequest.getDescription());
		if (status == 1) {
			return pickupRequestId;
		}
		return null;
	}

	/**
	 * Method to view specific Pickup Request object from database
	 * 
	 * @throws ParseException
	 */
	public PickupRequest pickupRequestFindById(Long pickupRequestId, Long companyId) throws ParseException {
		PickupRequest pickupRequest = queryForObject(PICKUP_REQUEST_FIND_BY_ID_QUERY + pickupRequestId, companyId,
				new PickupRequest());
		return pickupRequest;
	}

	/**
	 * @param requestType
	 * @param companyId
	 * @return
	 * @throws ParseException
	 */
	public List<TodayPickupRequest> pickupRequestFindAllTodayPickups(String requestType, Long companyId) {
		StringBuilder sqlQuery = new StringBuilder(PICKUP_REQUEST_FIND_ALL_TODAY_PICKUPS);

		if (requestType != null && !requestType.equalsIgnoreCase("")) {
			sqlQuery.append("AND requesttype='");
			sqlQuery.append(requestType);
			sqlQuery.append("'");
		}

		List<TodayPickupRequest> todayPickupRequests = query(sqlQuery.toString(), companyId, new TodayPickupRequest());
		return todayPickupRequests;
	}

}
