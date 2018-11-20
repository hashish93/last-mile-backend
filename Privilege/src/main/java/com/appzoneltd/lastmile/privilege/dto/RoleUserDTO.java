package com.appzoneltd.lastmile.privilege.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleUserDTO {

	
	Set<PrivilegeResponseDTO> privilegeResponseDTOs;

	@JsonProperty("acceptedPrivileges")
	public Set<PrivilegeResponseDTO> getPrivilegeResponseDTOs() {
		return privilegeResponseDTOs;
	}

	public void setPrivilegeResponseDTOs(Set<PrivilegeResponseDTO> privilegeResponseDTOs) {
		this.privilegeResponseDTOs = privilegeResponseDTOs;
	}

	
}
