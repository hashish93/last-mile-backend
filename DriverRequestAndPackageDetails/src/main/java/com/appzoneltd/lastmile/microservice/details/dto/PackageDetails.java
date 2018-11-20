package com.appzoneltd.lastmile.microservice.details.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageDetails implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6009718053259725113L;
    private Long packageId;
    private Long requestId;
    private String requestType;
    private String nickname;
    private PackageType type;
    private String packageValue;
    private BigDecimal weight;
    private String description;
    private String wrappingLabel;
    private String boxing;
    private Set<Long> packagingLabelsIds;
    private String paymentType;
    private String paymentMethod;
    private Set<Long> imageIds;
    @JsonIgnore
    private Long requesterId;
    @JsonIgnore
    private Long recipientId;

    public PackageDetails() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWrappingLabel() {
        return wrappingLabel;
    }

    public void setWrappingLabel(String wrappingLabel) {
        this.wrappingLabel = wrappingLabel;
    }

    public String getBoxing() {
        return boxing;
    }

    public void setBoxing(String boxing) {
        this.boxing = boxing;
    }

    public Set<Long> getPackagingLabelsIds() {
        return packagingLabelsIds;
    }

    public void setPackagingLabelsIds(Set<Long> packagingLabelsIds) {
        this.packagingLabelsIds = packagingLabelsIds;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(Set<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
