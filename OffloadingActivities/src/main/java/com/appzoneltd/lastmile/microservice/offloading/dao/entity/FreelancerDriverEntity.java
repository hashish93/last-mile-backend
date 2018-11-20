package com.appzoneltd.lastmile.microservice.offloading.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "freelancer_driver", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "FreelancerDriverEntity.countAll", query = "SELECT COUNT(x) FROM FreelancerDriverEntity x")
})
public class FreelancerDriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand", nullable = false, length = 50)
    private Long brand;

    @Column(name = "model", nullable = false, length = 50)
    private Long model;

    @Column(name = "purpose", length = 50)
    private String purpose;

    @Column(name = "model_year")
    private String modelYear;


    public FreelancerDriverEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public FreelancerDriverEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrand() {
        return brand;
    }

    public FreelancerDriverEntity setBrand(Long brand) {
        this.brand = brand;
        return this;
    }

    public Long getModel() {
        return model;
    }

    public FreelancerDriverEntity setModel(Long model) {
        this.model = model;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public FreelancerDriverEntity setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public String getModelYear() {
        return modelYear;
    }

    public FreelancerDriverEntity setModelYear(String modelYear) {
        this.modelYear = modelYear;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FreelancerDriverEntity{");
        sb.append("id=").append(id);
        sb.append(", brand=").append(brand);
        sb.append(", model=").append(model);
        sb.append(", purpose='").append(purpose).append('\'');
        sb.append(", modelYear='").append(modelYear).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
