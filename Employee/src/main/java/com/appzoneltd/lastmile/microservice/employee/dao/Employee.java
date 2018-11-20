
package com.appzoneltd.lastmile.microservice.employee.dao;

import com.appzoneltd.lastmile.microservice.modelobjects.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Java bean for 'Employee' entity
 *
 * @author alaa.nabil
 *
 */
public class Employee extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438758736793591359L;
	@NotNull(message = "employee.photo.required")
	private Long personalPhotoId;
	@NotEmpty(message = "employee.nationalid.required")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*", message = "employee.nationalid.errorformat")
	private String nationalId;

	public Employee() {
	}

	public Employee(Long personalPhotoId, String nationalId) {
		super();
		this.personalPhotoId = personalPhotoId;
		this.nationalId = nationalId;
	}

	public Long getPersonalPhotoId() {
		return personalPhotoId;
	}

	public void setPersonalPhotoId(Long personalPhotoId) {
		this.personalPhotoId = personalPhotoId;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
