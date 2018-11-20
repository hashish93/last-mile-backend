//package com.appzoneltd.lastmile.microservice.service;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.appzoneltd.lastmile.core.Message;
//import com.appzoneltd.lastmile.microservice.building.BuildingApplication;
//import com.appzoneltd.lastmile.microservice.building.json.ServiceParameters;
//import com.appzoneltd.lastmile.microservice.building.model.Building;
//import com.appzoneltd.lastmile.test.AbstractTest;
//
//
//@SpringApplicationConfiguration(classes=BuildingApplication.class)
//@Transactional
//public class BuildingCUDServiceTest extends AbstractTest {
//
//	@Before
//	public void SetUp() {
//		super.setUp();
//	}
//
//	/**
//	 * Testing to create Building and it  should return status 201 .
//	 * **/
//	@Test
//	public void testCreateBuilding() throws Exception {
//
//		String uri = "/createbuilding";
//		Building building = new Building(12345123L, "APPZONESt",1L,2L,3L,0,1,1L,
//				"Unit Test for Testing", "wasel123", 12.12222222 , 12.12121212 ,0L,12L,"Stree","15 Street1");
//		
////         building.setBuildingId(12345123L);
////         building.setBuildingname("APPZONESt");
////         building.setCountryId(1L);
////         building.setCityId(2L);
////         building.setAreaId(3L);
////         building.setActive(1);
////         building.setIsDeleted(0);
////         building.setBranchId(1L);
////         building.setDescription("comment");
////         building.setWaselcode("waselcode123");
////         building.setLatitude(12.12222222);
////         building.setLongitude(12.12121212);
////         building.setCompanyId(0L);
////         building.setBuilding_type_id(12L);
////         building.setStreet("Street");
////         building.setBuildingnumber("15 Street1");
//
//		String inputJson = super.mapToJson(building);
//
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//
//	    String content = result.getResponse().getContentAsString();
//		int status = result.getResponse().getStatus();
//
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//	}
//
//	
//	/**
//	 * Testing Update Building data and it  should return status 200 of Ok
//	 * number of resulting rows is 1 . 
//	 * **/
//	
//	@Test
//	public void testEditBuilding() throws Exception {
//		
//		String uri = "/editbuilding";
//		Building building = new Building(40996629720420531L, "APPZONESt",1L,2L,3L,0,1,1L,
//				"Unit Test for Testing", "wasel123", 12.12222222 , 12.12121212 ,0L,12L,"Stree","15 Street1");
//		String inputJson = super.mapToJson(building);
//
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//
//		String content = result.getResponse().getContentAsString();
//		Message message = mapFromJson(content, Message.class);
//		int status = result.getResponse().getStatus();
//
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//		Assert.assertEquals("failure - expected number of rows 1 ", 1, Integer.parseInt(message.getProperty()));
//	}
//	
//
//	/**
//	 * Testing removeBuilding and it  should return status 200 of Ok  
//	 **/
//	
//	@Test
//	public void testRemoveBuilding() throws Exception {
//
//		String uri = "/deletebuilding";
//		ServiceParameters serviceParameters = new ServiceParameters(0, 40996629720420531L);
//		
//		String inputJson = super.mapToJson(serviceParameters);
//
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//
//		String content = result.getResponse().getContentAsString();
//		Message message = mapFromJson(content, Message.class);
//		int status = result.getResponse().getStatus();
//
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//		Assert.assertEquals("failure - expected number of rows 1 ", 1, Integer.parseInt(message.getProperty()));
//	}
//	
//	
//	@Test
//	public void testDeleteBuilding() throws Exception {
//
//		String uri = "/removebuilding";
//		ServiceParameters serviceParameters = new ServiceParameters(0L, 40996629720420531L);
//		String inputJson = super.mapToJson(serviceParameters);
//
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//
//
//		String content = result.getResponse().getContentAsString();
//		int status = result.getResponse().getStatus();
//
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//
//	}
//	
//
//	
//	@After
//	public void tearDown() {
//
//	}
//
//}
