package com.appzoneltd.lastmile.microservices.stats.dto;

import lombok.Data;

/**
 * Created by alaa.nabil on 10/18/2017.
 */
@Data
public class BuildingServingArea {
    private String latitude;
    private String longitude;

    public BuildingServingArea(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
