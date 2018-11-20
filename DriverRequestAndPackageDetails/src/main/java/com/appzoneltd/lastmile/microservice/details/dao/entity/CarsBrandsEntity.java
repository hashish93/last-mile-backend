package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars_brands", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CarsBrandsEntity.countAll", query = "SELECT COUNT(x) FROM CarsBrandsEntity x")
})
public class CarsBrandsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "brand_name", length = 255)
    private String brandName;

    public CarsBrandsEntity() {
        super();
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(brandId);
        sb.append("]:");
        sb.append(brandName);
        return sb.toString();
    }

}
