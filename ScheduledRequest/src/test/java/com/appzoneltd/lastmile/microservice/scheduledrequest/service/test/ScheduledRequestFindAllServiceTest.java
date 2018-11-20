package com.appzoneltd.lastmile.microservice.scheduledrequest.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.core.Message;
import com.appzoneltd.lastmile.microservice.scheduledrequest.ScheduledRequestApplication;
import com.appzoneltd.lastmile.microservice.scheduledrequest.model.ScheduledRequest;
import com.appzoneltd.lastmile.test.AbstractTest;

/**
 * 
 * @author Alaa Nabil
 * 
 **/
@SpringApplicationConfiguration(classes = ScheduledRequestApplication.class)
@Transactional
public class ScheduledRequestFindAllServiceTest extends AbstractTest {

	/**
	 * Calling setUp method in super class to initialize application context as
	 * REST Service
	 * 
	 **/

	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Testing Find All Service With assuming that it returns no null values and
	 * status OK .
	 * 
	 **/
	@Test
	public void scheduledRequestFindAllWithPagingTest() throws Exception {
		String uri = "/findall";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0").param("page", "1").param("pagesize", "5").param("ordertype", "DESC"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();

		List<ScheduledRequest> scheduledRequests = mapFromJson(content, ArrayList.class);

		Assert.assertTrue("failure - should have a size", scheduledRequests.size() > 0);
		Assert.assertNotNull("failure - Should be NOT Null", scheduledRequests);
		Assert.assertEquals("failure - should be 200", 200, httpStatus);
	}

	/**
	 * Testing Find All Service With assuming that it returns no null values and
	 * status OK .
	 * 
	 **/
	@Test
	public void scheduledRequestFindAllTest() throws Exception {
		String uri = "/findall";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0").param("pagesize", "10")).andReturn();

		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();

		List<ScheduledRequest> scheduledRequests = mapFromJson(content, ArrayList.class);

		Assert.assertTrue("failure - expeted to be true", scheduledRequests.size() > 0);
		Assert.assertNotNull("failure - Should be NOT Null", scheduledRequests);
		Assert.assertEquals("failure - should be 200", 200, httpStatus);
	}

	/**
	 * Testing Find All Service With Feltering and assuming that it returns no
	 * null values and status OK .
	 * 
	 **/
	@Test
	public void scheduledRequestFindAllwithFelteringTest() throws Exception {
		String uri = "/findall";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.param("companyid", "0").param("page", "0").param("pagesize", "50").param("ordertype", "DESC")
				.param("requestid", "988142119")
				.param("requestermobile", "").param("fromrequestdate", "")
				.param("torequestdate", "").param("frompickupdate", "").param("topickupdate", ""))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		int httpStatus = result.getResponse().getStatus();

		List<ScheduledRequest> scheduledRequests = mapFromJson(content, ArrayList.class);

		Assert.assertTrue("failure - should have a size", scheduledRequests.size() > 0);
		Assert.assertNotNull("failure - Should be NOT Null", scheduledRequests);
		Assert.assertEquals("failure - should be 200", 200, httpStatus);
	}

	/**
	 * Count of all records in database assuming that no null values with http
	 * status ok.
	 * 
	 **/
	@Test
	public void scheduledRequestFindAllCountTest() throws Exception {
		String uri = "/findallcount";

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).param("companyid", "0"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		Message message = mapFromJson(content, Message.class);
		int httpStatus = result.getResponse().getStatus();

		Assert.assertNotNull("failure - Should be NOT Null", Integer.parseInt(message.getProperty()));
		// Assert.assertTrue("failure - Should be true",
		// Integer.parseInt(message.getProperty()) > 0);
		Assert.assertEquals("failure - should be 200", 200, httpStatus);
	}

	@After
	public void tearDown() {

	}

}
