package com.appzoneltd.lastmile.microservice.offloading.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author alaa.nabil
 */
@Entity
@Table(name = "vehicle", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "VehicleEntity.countAll", query = "SELECT COUNT(x) FROM VehicleEntity x")})
public class VehicleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "plate", nullable = false, length = 50)
    private String plate;

    @Column(name = "chassis", nullable = false, length = 100)
    private String chassis;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Column(name = "purpose", length = 50)
    private String purpose;

    @Column(name = "motor", length = 50)
    private String motor;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;


    public VehicleEntity() {
        super();
    }

    public Long getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getChassis() {
        return this.chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public BigDecimal getWeight() {
        return this.weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMotor() {
        return this.motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleEntity{");
        sb.append("vehicleId=").append(vehicleId);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", plate='").append(plate).append('\'');
        sb.append(", chassis='").append(chassis).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", purpose='").append(purpose).append('\'');
        sb.append(", motor='").append(motor).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", created=").append(created);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
