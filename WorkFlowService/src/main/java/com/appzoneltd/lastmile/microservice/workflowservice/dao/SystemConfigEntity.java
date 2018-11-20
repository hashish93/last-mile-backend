package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "system_config", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "SystemConfigEntity.countAll", query = "SELECT COUNT(x) FROM SystemConfigEntity x")
})
public class SystemConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "config_id", nullable = false)
    private Long configId;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "displayname", length = 50)
    private String displayname;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "unit", length = 2147483647)
    private String unit;

    public SystemConfigEntity() {
        super();
    }

    public Long getConfigId() {
        return this.configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
