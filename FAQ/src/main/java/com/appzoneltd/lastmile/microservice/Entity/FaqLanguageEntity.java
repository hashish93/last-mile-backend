	package com.appzoneltd.lastmile.microservice.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by hashish on 4/25/2017.
 */
//@Data
@Entity
@Table(name = "faq_language", schema = "lastmile_core")
public class FaqLanguageEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String languageName;
    
    @Column(name="code")
    private String languageCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
    
}
