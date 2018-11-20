package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alaa.nabil on 2/15/2017.
 */
public class Plan implements Serializable{

    private Long id;
    private Date created;

    public Plan() {
    }

    public Plan(Long id, Date created) {
        this.id = id;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
