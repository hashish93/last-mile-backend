package com.appzoneltd.lastmile.microservice.vehicleregistration.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 2/22/2017.
 */
public class PushNotificationTokens implements Serializable {
    private String firebaseToken;

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
