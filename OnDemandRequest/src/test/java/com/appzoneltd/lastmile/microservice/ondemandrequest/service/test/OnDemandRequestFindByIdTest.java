package com.appzoneltd.lastmile.microservice.ondemandrequest.service.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.ondemandrequest.OnDemandRequestApplication;
import com.appzoneltd.lastmile.test.AbstractTest;

import dummies.OnDemandRequest;


@SpringApplicationConfiguration(classes = OnDemandRequestApplication.class)
@Transactional
public class OnDemandRequestFindByIdTest extends AbstractTest {
	// "/getondemandrequestbyid/{id}"

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void onDemandFindByIdTest() throws Exception {
		String uri = "/getondemandrequestbyid/987989298";

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).param("companyid", "0"))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();
		OnDemandRequest onDemandRequest = mapFromJson(content, OnDemandRequest.class);
		
		Assert.assertNotNull("failure - Should be Not Null", onDemandRequest);
		Assert.assertEquals("failure - Should be 200", 200, httpStatus);
		
	}
	
	@Test
	public void onDemandFindByIdErrorTest() throws Exception{
		String uri = "/getondemandrequestbyid/0000";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).param("companyid", "0"))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		int httpStatus = result.getResponse().getStatus();
		
		Assert.assertTrue("Failure - should be true", content.trim().length()==0);
		Assert.assertEquals("failure - should be 500", 500, httpStatus);
	}

}
