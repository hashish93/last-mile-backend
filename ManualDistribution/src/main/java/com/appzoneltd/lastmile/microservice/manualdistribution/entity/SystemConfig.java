/*
 * Created on 31 May 2016 ( Time 16:04:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(schema = "lastmile_core", name = "system_config")
public class SystemConfig implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8650442468712226435L;

    @Column(name = "config_id")
    @Id
    private Long configId;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "text_value")
    private String textValue;
    @Column(name = "displayname")
    private String displayname;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "version")
    private Long version;
    @Column(name = "unit")
    private String unit;
    @Column(name = "created")
    private Date created;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SystemConfig() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "configId=" + configId +
                ", value=" + value +
                ", textValue='" + textValue + '\'' +
                ", displayname='" + displayname + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", version=" + version +
                ", unit='" + unit + '\'' +
                ", created=" + created +
                '}';
    }
}