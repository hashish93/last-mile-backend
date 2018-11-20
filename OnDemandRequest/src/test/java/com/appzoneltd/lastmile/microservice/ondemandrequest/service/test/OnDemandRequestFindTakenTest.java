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
public class OnDemandRequestFindTakenTest extends AbstractTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void onDemandFindAllTest() throws Exception {
		String uri = "/getalltakenondemand";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0").param("ordertype", "DESC")).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();
		
		List<OnDemandRequest> onDemandRequests = mapFromJson(content, ArrayList.class);
		
		Assert.assertTrue("failure - expect to be true", onDemandRequests.size()>0);
		Assert.assertNotNull("failure - should be NOT Null", onDemandRequests);
		Assert.assertEquals("failure - should be 200",200,httpStatus);
	}
}