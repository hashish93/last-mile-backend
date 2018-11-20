package com.appzoneltd.lastmile.microservice.employee.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.appzoneltd.lastmile.microservice.employee.enums.UserEntityStatus;

import lombok.Data;

@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = -2810737228747824276L;

	private Long userId;

	private String username;

	private String password;

	@NotEmpty(message = "user.firstname.required")
	@Pattern(regexp = "^$|^[a-zA-Z\u0621-\u064A\u0660-\u0669][a-zA-Z\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z\u0621-\u064A\u0660-\u0669]*", message = "user.firstname.errorformat")
	private String firstName;

	@NotEmpty(message = "user.secondname.required")
	@Pattern(regexp = "^$|^[a-zA-Z\u0621-\u064A\u0660-\u0669][a-zA-Z\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z\u0621-\u064A\u0660-\u0669]*", message = "user.secondname.errorformat")
	private String lastName;

	private Long countryCodeId;

	@NotBlank(message = "user.mobile.required")
	@Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
	private String phone;

	@NotBlank(message = "user.email.required")
	@Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9.]+@[a-zA-Z]+.[a-zA-Z]{2,5}$", message = "customer.email.errorformat")
	private String email;

	private UserEntityStatus userEntityStatus;

	@NotNull(message="user.userTypeId.required")
	private Long userTypeId;

	private String userType;
	
	private Boolean enabled;

	@NotNull(message = "user.photo.required")
	private Long personalPhotoId;

	@NotEmpty(message = "user.nationalid.required")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*", message = "employee.nationalid.errorformat")
	private String nationalId; 
	
	private Date created;

	@Size(max = 500, message = "user.description.size")
	private String description;

	private Long version;
	
	private List<Long> roles;
	
	private List<ActiveRole> activeRoles;
	
	private List<Long> hubs;
	
	private List<ActiveHub> activeHubs;
	
	private String hubNames;

}
