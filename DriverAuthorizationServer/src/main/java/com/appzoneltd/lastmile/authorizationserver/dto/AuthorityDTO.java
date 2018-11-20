package com.appzoneltd.lastmile.authorizationserver.dto;

import lombok.Data;

@Data
public class AuthorityDTO {

	private Long id;
	private String authority;
	
	@Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }

    public int hashCode() {
        return getId().hashCode();
    }
	
	
}
