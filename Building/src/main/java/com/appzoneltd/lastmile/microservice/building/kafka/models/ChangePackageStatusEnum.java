package com.appzoneltd.lastmile.microservice.building.kafka.models;

public enum ChangePackageStatusEnum {
    NEW("NEW"), AWAITING_PICKUP("AWAITING_PICKUP"), WAITING_FOR_CUSTOMER_ALTERNATIVE(
            "WAITING_FOR_CUSTOMER_ALTERNATIVE"), ACTION_NEEDED("ACTION_NEEDED"), IN_PICKUP_VERIFICATION(
            "IN_PICKUP_VERIFICATION"), PICKEDUP("PICKEDUP"), CANCELED("CANCELED"), SCHEDULED_FOR_PICKUP("SCHEDULED_FOR_PICKUP"), ASSIGNED("ASSIGNED");

    private final String packageStatus;

    private ChangePackageStatusEnum(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

}
