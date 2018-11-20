package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars_models", schema = "lastmile_core")

@NamedQueries({
        @NamedQuery(name = "CarsModelsEntity.countAll", query = "SELECT COUNT(x) FROM CarsModelsEntity x")
})
public class CarsModelsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "model_name", length = 255)
    private String modelName;

    public CarsModelsEntity() {
        super();
    }

    public Long getModelId() {
        return this.modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(modelId);
        sb.append("]:");
        sb.append(brandId);
        sb.append("|");
        sb.append(modelName);
        return sb.toString();
    }

}
