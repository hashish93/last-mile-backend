package com.appzoneltd.lastmile.microservice.sms.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country_codes", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CountryCodesEntity.countAll", query = "SELECT COUNT(x) FROM CountryCodesEntity x")
})
public class CountryCodesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phonecode", nullable = false)
    private Long phonecode;

    public CountryCodesEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(Long phonecode) {
        this.phonecode = phonecode;
    }
}
