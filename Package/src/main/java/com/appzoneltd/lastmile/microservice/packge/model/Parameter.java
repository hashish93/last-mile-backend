package com.appzoneltd.lastmile.microservice.packge.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hashish on 8/16/2017.
 */

@Data
public class Parameter implements Serializable{

    private Long id;
    private String status;

}
