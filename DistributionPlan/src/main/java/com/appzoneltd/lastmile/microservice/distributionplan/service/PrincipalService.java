package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.distributionplan.dto.MyPrincipal;
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
	
	public Long getUserId(Principal principal) {
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			return myPrincipal.getUserId();
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
	
	public boolean isSuperVisor(Principal principal){
		try {
			MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
			String userType=myPrincipal.getUserType();
			if("SUPERVISOR".equals(userType)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Long getHubIdIfFound(Principal principal){
		System.out.println("principal "+principal);
		if("HUB_ADMIN".equalsIgnoreCase(getUserType(principal)) ||  "OPERATION_BACKOFFICE".equalsIgnoreCase(getUserType(principal))){
			List <Long> hubIds =  getHubs(principal);
			if(hubIds !=null && hubIds.size()>0){
				System.out.println("hub id "+hubIds.get(0));
				return hubIds.get(0);
			}
		}
		return null;
	}
}
