package com.appzoneltd.lastmile.microservice.enums;

public enum UserType {
    CUSTOMER("CUSTOMER", "Customer"), CORPORATE_DRIVER("CORPORATE_DRIVER", "CORPORATE_DRIVER"), OPERATION_BACKOFFICE("OPERATION_BACKOFFICE", "OPERATION_BACKOFFICE"),
    FREELANCER_DRIVER("FREELANCER_DRIVER", "FREELANCER_DRIVER") , SUPERVISOR("SUPERVISOR", "SUPERVISOR") , HUB_ADMIN("HUB_ADMIN", "HUB_ADMIN"),
    FREELANCER_ADMIN("FREELANCER_ADMIN", "FREELANCER_ADMIN") ,SUPER_ADMIN("SUPER_ADMIN", "SUPER_ADMIN");
    ;

    private final String value;
    private final String label;

    private UserType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
