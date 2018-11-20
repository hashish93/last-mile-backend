package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "temp_email_verfication", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "EmailTempEntity.countAll", query = "SELECT COUNT(x) FROM EmailTempEntity x")
})
public class EmailTempEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097664927149885654L;

	@Id
    @Column(name = "user_id", nullable = false)

	private Long userId;
	
	@Column(name = "new_email", nullable = false)
	private String newEmail;
	
	@Column(name = "verification_hash", nullable = false)
	private String verificationHash;
	
	
	public EmailTempEntity(Long userId, String newEmail, String verificationHash) {

		this.userId = userId;
		this.newEmail = newEmail;
		this.verificationHash = verificationHash;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public String getVerificationHash() {
		return verificationHash;
	}
	public void setVerificationHash(String verificationHash) {
		this.verificationHash = verificationHash;
	}

}
