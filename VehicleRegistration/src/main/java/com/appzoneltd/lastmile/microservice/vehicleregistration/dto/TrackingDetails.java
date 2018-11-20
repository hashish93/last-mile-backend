package com.appzoneltd.lastmile.microservice.vehicleregistration.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 2/22/2017.
 */
public class TrackingDetails implements Serializable {
    private Location location;
    private PushNotificationTokens pushNotificationTokens;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PushNotificationTokens getPushNotificationTokens() {
        return pushNotificationTokens;
    }

    public void setPushNotificationTokens(PushNotificationTokens pushNotificationTokens) {
        this.pushNotificationTokens = pushNotificationTokens;
    }
}
