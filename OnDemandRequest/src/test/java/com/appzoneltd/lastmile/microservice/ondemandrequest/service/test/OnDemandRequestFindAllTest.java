package com.appzoneltd.lastmile.microservice.ondemandrequest.service.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.appzoneltd.lastmile.microservice.ondemandrequest.OnDemandRequestApplication;
import com.appzoneltd.lastmile.test.AbstractTest;

import dummies.OnDemandRequest;

@SpringApplicationConfiguration(classes = OnDemandRequestApplication.class)
@Transactional
public class OnDemandRequestFindAllTest extends AbstractTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void onDemandFindAllTest() throws Exception {
		String uri = "/getallondemandrequest";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0").param("ordertype", "DESC")).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();
		
		List<OnDemandRequest> onDemandRequests = mapFromJson(content, ArrayList.class);
		
		Assert.assertTrue("failure - expect to be true", onDemandRequests.size()>0);
		Assert.assertNotNull("failure - should be NOT Null", onDemandRequests);
		Assert.assertEquals("failure - should be 200",200,httpStatus);
	}

	
	@Test
	public void onDemandFindAllCountTest() throws Exception{
		String uri = "/countallondemandrequest";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0")).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int count = mapFromJson(content, int.class);
		int httpStatus = result.getResponse().getStatus();
		
		Assert.assertNotNull("failure - Should be NOT Null", count);
		Assert.assertTrue("failure - Should be True",count>0);
		Assert.assertEquals("failure - Should be 200", 200, httpStatus);
	}
}
