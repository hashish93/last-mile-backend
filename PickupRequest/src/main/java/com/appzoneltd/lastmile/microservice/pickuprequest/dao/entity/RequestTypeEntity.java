package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by hashish on 5/21/2017.
 */


@Data
@Entity
@Table(name = "pickup_request_type", schema = "lastmile_request")
@NamedQueries({ @NamedQuery(name = "RequestTypeEntity.countAll", query = "SELECT COUNT(x) FROM RequestTypeEntity x") })
public class RequestTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pickup_request_type_id", nullable = false)
    private Long requestTypeId;

    @Column(name = "type", nullable = false, length = 50)
    private String requestType;

}

