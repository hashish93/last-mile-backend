//package com.appzoneltd.lastmile.microservice.service;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.appzoneltd.lastmile.microservice.building.BuildingApplication;
//import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
//import com.appzoneltd.lastmile.microservice.test.AbstractTest;
//
//
//
//@SpringApplicationConfiguration(classes=BuildingApplication.class)
//@Transactional
//public class BuildingFindByIdServiceTest extends AbstractTest {
//	
//	@Before
//	public void SetUp() {
//		super.setUp();
//	}
//
//	/**
//	 * Testing viewBuildingById and it  should return status 200 of Ok  
//	 **/
//	
//	@Test
//	public void testBuildingFindById() throws Exception {
//		String uri = "/findbyid";
//		
//		EndPointParameter serviceParameters = new EndPointParameter(0, 40833859717642137L);
//		String inputJson = super.mapToJson(serviceParameters);
//		
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//		
//		String content = result.getResponse().getContentAsString();
//		int status = result.getResponse().getStatus();
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//	} 
//	
//	
//	@After
//	public void tearDown() {
//
//	}
//}
