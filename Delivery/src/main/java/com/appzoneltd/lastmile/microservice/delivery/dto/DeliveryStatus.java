package com.appzoneltd.lastmile.microservice.delivery.dto;

/**
 * Created by alaa.nabil on 3/12/2017.
 */
public enum DeliveryStatus {
    NEW,IN_DELIVERY_VERIFICATION,SCHEDULED_FOR_DELIVERY,RESCHEDULED_FOR_DELIVERY, OUT_FOR_DELIVERY, ACTION_NEEDED, DELIVERED, CANCELED_DELIVERY, RETURNED, SCHEDULED_FOR_RETURN,ENDED;
}
