package com.appzoneltd.lastmile.microservice.packge.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Java bean for 'PackageDTO' entity
 *
 * @author Hashish & Khaled
 */

@Data
public class PackageDTO implements Serializable {

    private static final long serialVersionUID = 3897609208219501287L;

    private Long packageId;

    private String nickName;

    private Long packageTypeId;

    private String packageValue;

    private String packageType;

    private String shipmentService;

    private String subShipmentService;


    @Digits(integer = 19, fraction = 2, message = "package.weight.valid")
    private BigDecimal actualWeight;

    private Long shipmentServiceTypeId;

    private String status;

    private String description;

    private Date created;

    private Long version;

    @Size(min = 1, message = "package.image.required")
    private Set<Long> imageIds;

    private Set<Long> labelIds;

}
