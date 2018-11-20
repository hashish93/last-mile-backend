//package com.appzoneltd.lastmile.microservice.service;
//
//import java.util.ArrayList;
//import java.util.List;
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
//import com.appzoneltd.lastmile.microservice.building.dao.Building;
//import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
//import com.appzoneltd.lastmile.microservice.test.AbstractTest;
//import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
//
//@SpringApplicationConfiguration(classes = BuildingApplication.class)
//@Transactional
//public class BuildingFindAllServiceTest extends AbstractTest {
//
//	@Before
//	public void SetUp() {
//		super.setUp();
//	}
//
//	/**
//	 * Testing view All Building and it should return status 200 of Ok
//	 **/
//
//	@Test
//	public void viewAllBuilding() throws Exception {
//		String uri = "/findall";
//		EndPointParameter serviceParameters = new EndPointParameter(0, 27L,1,6,"ASC");
//		String inputJson = super.mapToJson(serviceParameters);
//		
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//
//		String content = result.getResponse().getContentAsString();
//		List<Building> buildings = mapFromJson(content, ArrayList.class);
//		System.out.println("=========>>>" + content);
//		int status = result.getResponse().getStatus();
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//		Assert.assertTrue("Should be true", buildings.size() > 0);
//	}
//
//	/**
//	 * Testing view All Building By Page and it should return status 200 of Ok
//	 **/
//
//	@Test
//	public void viewAllBuildingByPage() throws Exception {
//		String uri = "/findall";
//		EndPointParameter serviceParameters = new EndPointParameter(0, 27L,1,6,"ASC");
//		String inputJson = super.mapToJson(serviceParameters);
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//		String content = result.getResponse().getContentAsString();
//		int status = result.getResponse().getStatus();
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//	}
//
//	/**
//	 * Testing buildingCountAll and it should return status 200 of Ok
//	 **/
//
//	@Test
//	public void buildingCountAll() throws Exception {
//		String uri = "/countall";
//		EndPointParameter serviceParameters = new EndPointParameter(0,0);
//		String inputJson = super.mapToJson(serviceParameters);
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
//		String content = result.getResponse().getContentAsString();
//		int status = result.getResponse().getStatus();
//		Assert.assertEquals("failure - expected HTTP status", 200, status);
//	}
//
//	@After
//	public void tearDown() {
//
//	}
//}
