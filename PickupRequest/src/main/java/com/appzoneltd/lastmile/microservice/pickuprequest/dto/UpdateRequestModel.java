package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequest;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by alaa.nabil on 5/15/2017.
 */
public class UpdateRequestModel extends PickupRequest {

    private String nickName;
    private Long shipmentServiceTypeId;
    private String description;
    private String packageValue;
    @Size(min = 1, message = "package.image.required")
    private Set<Long> imageIds;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(Set<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public Long getShipmentServiceTypeId() {
        return shipmentServiceTypeId;
    }

    public void setShipmentServiceTypeId(Long shipmentServiceTypeId) {
        this.shipmentServiceTypeId = shipmentServiceTypeId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
