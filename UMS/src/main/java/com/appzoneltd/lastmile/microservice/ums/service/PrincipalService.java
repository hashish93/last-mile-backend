package com.appzoneltd.lastmile.microservice.ums.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ums.model.MyPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrincipalService {

	@Autowired
	private ObjectMapper objectMapper;
	public List<Long> getHubs(Principal principal) {
		List<Long> hubList = new ArrayList<>();
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			hubList = myPrincipal.getHubs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hubList;
		
	}

	public String getUserName(Principal principal) {
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			return myPrincipal.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String getUserType(Principal principal) {
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			return myPrincipal.getUserType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MyPrincipal getUserPrincipal(Principal principal) {
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			return myPrincipal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isSuperAdmin(Principal principal){
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			String userType=myPrincipal.getUserType();
			if("SUPER_ADMIN".equals(userType)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
