package com.appzoneltd.lastmile.microservice.offloading.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by alaa.nabil on 8/6/2017.
 */
@Data
public class VehicleSummaryModel implements Serializable {
    private String driverName;
    private Long personalPhotoId;
    private String phoneNumber;
    private String vehicleType;
    private String hubName;
    private List<PackageSummaryModel> packageSummaryModels;
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleSummaryModel{");
        sb.append(", driverName='").append(driverName).append('\'');
        sb.append(", personalPhotoId=").append(personalPhotoId);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", vehicleType='").append(vehicleType).append('\'');
        sb.append(", packageSummaryModels=").append(packageSummaryModels);
        sb.append('}');
        return sb.toString();
    }
}
