package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alaa.nabil on 10/18/2017.
 */
@Data
@Entity
@Table(name = "building_serving_area", schema = "lastmile_core")
public class BuildingServingArea {

    @Id
    @Column(name = "building_serving_id")
    private Long buildingServingId;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

}
