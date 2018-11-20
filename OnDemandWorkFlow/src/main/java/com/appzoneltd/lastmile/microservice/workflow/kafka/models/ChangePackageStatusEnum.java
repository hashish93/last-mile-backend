package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

public enum ChangePackageStatusEnum {
	NEW("NEW", "waitAssingPackageAsNew"), AWAITING_PICKUP("AWAITING_PICKUP",
			"WaitForAssingPackageAsAWaitingPickup"), WAITING_FOR_CUSTOMER_ALTERNATIVE(
					"WAITING_FOR_CUSTOMER_ALTERNATIVE", "waitAssingPackageAsWaitingForCustomer",
					"WaitCustomerAlternative") {
				public String getWaitStateName(Boolean webUser) {
					return ((webUser==null)||!webUser) ? waitStateName[0] : waitStateName[1];
				}
			},
	ACTION_NEEDED("ACTION_NEEDED"), IN_PICKUP_VERIFICATION("IN_PICKUP_VERIFICATION"), PICKEDUP("PICKEDUP",
			"WaitUntilPackageStatusChangedToPickedUp"), CANCELED("CANCELED",
					"WaitUntilPackageStatusChangedToCancelled"), SCHEDULED_FOR_PICKUP("SCHEDULED_FOR_PICKUP",
							"waitChangingPackageToScheduledForPickup"), ASSIGNED("ASSIGNED",
									"WaitForAssingPackageAsAssigned");

	private final String packageStatus;
	final String[] waitStateName;

	private ChangePackageStatusEnum(String packageStatus, String... waitStateName) {
		this.packageStatus = packageStatus;
		this.waitStateName = waitStateName;
	}

	public String getPackageStatus() {
		return packageStatus;
	}

	/**
	 * get the wait state name for {@link ChangePackageStatusEnum} if available
	 * 
	 * @param webUser
	 *            weather this is a web-user or not, used only in
	 *            {@link ChangePackageStatusEnum#WAITING_FOR_CUSTOMER_ALTERNATIVE}
	 * @return the {@code String} value for the waiting state
	 * @throws UnsupportedOperationException
	 *             if the current {@link ChangePackageStatusEnum} does not
	 *             support a waiting state value
	 */
	public String getWaitStateName(Boolean webUser) throws UnsupportedOperationException {
		return waitStateName != null ? waitStateName[0] : throwUnsupportedOperationException();
	}

	private String throwUnsupportedOperationException() {
		throw new UnsupportedOperationException("no wait state for : " + toString());
	}

	protected String getWaitStateName(int index) {
		return waitStateName != null ? waitStateName[index] : null;
	}

}
