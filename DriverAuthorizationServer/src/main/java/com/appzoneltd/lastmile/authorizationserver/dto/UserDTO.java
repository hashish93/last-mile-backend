package com.appzoneltd.lastmile.authorizationserver.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -2810737228747824276L;

	private Long userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
}
