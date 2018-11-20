package com.appzoneltd.lastmile.microservice.customer.dao.entity;

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

    @Column(name = "iso", nullable = false, length = 2147483647)
    private String iso;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "nicename", nullable = false, length = 80)
    private String nicename;

    @Column(name = "iso3", length = 2147483647)
    private String iso3;

    @Column(name = "numcode")
    private Long numcode;

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

    public String getIso() {
        return this.iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicename() {
        return this.nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getIso3() {
        return this.iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Long getNumcode() {
        return this.numcode;
    }

    public void setNumcode(Long numcode) {
        this.numcode = numcode;
    }

    public Long getPhonecode() {
        return this.phonecode;
    }

    public void setPhonecode(Long phonecode) {
        this.phonecode = phonecode;
    }

}
