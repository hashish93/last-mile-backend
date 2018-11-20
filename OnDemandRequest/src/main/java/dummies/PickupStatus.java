/**
 * AppZone LTD
 * Author: Amir Serry
 * Project Name Request
 * May 23, 2016 3:09:15 PM
 */
package dummies;

/**
 * @author Amir Serry
 *
 */
public enum PickupStatus {
	
	Pending("Pending"),
	NoResponse("No Response"),
	NoVehicle(" No Vehicle"),
	Grabbed ("Grabbed");

	private final String PickupStatus;

	private PickupStatus(String PickupStatus) {
		this.PickupStatus = PickupStatus;
	}

	public String getPickupStatus() {
		return PickupStatus;
	}

}
