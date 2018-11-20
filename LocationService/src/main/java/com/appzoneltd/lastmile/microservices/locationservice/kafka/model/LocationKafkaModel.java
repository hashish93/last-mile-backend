package com.appzoneltd.lastmile.microservices.locationservice.kafka.model;

import lombok.Data;

/**
 * Created by hashish on 5/9/2017.
 */
@Data
public class LocationKafkaModel {
    private Long id;
    private String latitude;
    private String longitude;
}
