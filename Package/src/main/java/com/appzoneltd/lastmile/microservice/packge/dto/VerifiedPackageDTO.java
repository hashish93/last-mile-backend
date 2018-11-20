package com.appzoneltd.lastmile.microservice.packge.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class VerifiedPackageDTO implements Serializable {

    private static final long serialVersionUID = 2697466012919544207L;
    private Long packageId;
    private Long packageTypeId;
    private String packageType;
    private String packageValue;
    private BigDecimal actualweight;
    private Set<Long> imageIds;
    private String description;
    private Date created;
    private Long version;

}
