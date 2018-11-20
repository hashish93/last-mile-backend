/**
 * AppZone LTD
 * Author: Amir Serry
 * Project Name Request
 * May 23, 2016 1:16:25 PM
 */
package dummies;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.core.Model;

/**
 * @author Mahmoud Farahat
 *
 */
public class OnDemandRequest extends Model implements Serializable, RowMapper<OnDemandRequest> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long requestId; // 1.Request ID

	private int receivedFrom; // 2. Received from

	private String pickupFormatedAddress; // 3.Pickup Area

	private String packageType; // 4.Package Type

	private String packageDimension; // 5.Package Size

	private String pickupStatus; // 6.Pickup Status

	private String statusColor; // 7.Status Color

	private String pickupGoogleLocation; // 8.Google Location
	
	private Long  actualWeight ;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public int getReceivedFrom() {

		return receivedFrom;
	}

	public void setReceivedFrom(int receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getPickupFormatedAddress() {
		return pickupFormatedAddress;
	}

	public void setPickupFormatedAddress(String pickupFormatedAddress) {
		this.pickupFormatedAddress = pickupFormatedAddress;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageDimension() {
		return packageDimension;
	}

	public void setPackageDimension(String packageDimension) {
		this.packageDimension = packageDimension;
	}

	public String getPickupStatus() {
		return pickupStatus;
	}

	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public String getPickupGoogleLocation() {
		return pickupGoogleLocation;
	}

	public void setPickupGoogleLocation(String pickupGoogleLocation) {
		this.pickupGoogleLocation = pickupGoogleLocation;
	}

	public Long getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Long actualWeight) {
		this.actualWeight = actualWeight;
	}

	@Override
	public OnDemandRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		// --- Set data from ResultSet to Bean attributes

		OnDemandRequest OnDemandRequest = new OnDemandRequest();

		OnDemandRequest.setRequestId(rs.getLong("pickup_request_id")); // java.lang.Long
		if (rs.wasNull()) {
			OnDemandRequest.setRequestId(null);
		}
		; // not primitive number => keep null value if any
		OnDemandRequest.setReceivedFrom((int) rs.getDouble("recievedfrom")); // java.util.Date

		OnDemandRequest.setPickupFormatedAddress(rs.getString("pickupformatedaddress"));

		OnDemandRequest.setPackageType(rs.getString("packagetype")); // java.lang.String

		OnDemandRequest.setPackageDimension(rs.getString("packagedimension")); // java.lang.String

		OnDemandRequest.setPickupStatus(rs.getString("pickupstatus")); // java.lang.String

		OnDemandRequest.setStatusColor(rs.getString("color")); // java.lang.String

		OnDemandRequest.setPickupGoogleLocation(rs.getString("pickupgooglelocation"));
		
		OnDemandRequest.setActualWeight(rs.getLong("actualweight"));
		

		return OnDemandRequest;
	}

}
