package com.appzoneltd.lastmile.microservice.countryandcity.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 3/8/2017.
 */
public class CountryCode implements Serializable {

    private Long id;
    private String name;
    private String code;
    private String iso;

    public CountryCode(Long id, String name, String code , String iso) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.iso = iso;
    }

    public Long getId() {
        return id;
    }

    public CountryCode setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public CountryCode setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryCode setName(String name) {
        this.name = name;
        return this;
    }

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}
}
