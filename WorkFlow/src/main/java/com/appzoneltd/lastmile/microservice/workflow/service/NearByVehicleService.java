 package com.appzoneltd.lastmile.microservice.workflow.service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class NearByVehicleService {

	@Value("${services.url}")
	private String baseUrl;
	
	
	
	public String assignPackageAsNew(long packageId){
		String serviceURL=baseUrl+"/workFlowService/assignNewPackage";     
		/// Call RestCall
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    // Setting Attributes and Data
	    JSONObject jsonRequest= new JSONObject();
	    jsonRequest.put("id",packageId);
	    
	    HttpEntity<String> request = new HttpEntity<String>(jsonRequest.toString(),headers);
	    ResponseEntity<String> response = restTemplate.exchange(serviceURL, HttpMethod.POST, request ,String.class);
	    
	    System.out.println(">>> Response FROM SERVICE FOR PACKAGE AS NEW");
	    // return result 
	    return null; 
	}//end assignPackageAsNew
	

	
}
