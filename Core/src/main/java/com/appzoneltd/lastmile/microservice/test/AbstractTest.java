//package com.appzoneltd.lastmile.microservice.test;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * 
// * @author Alaa Nabil
// * 
// **/
//
//public abstract class AbstractTest {
//	protected Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	/** autowiring web application context */
//	@Autowired
//	protected WebApplicationContext applicationContext;
//
//	protected MockMvc mockMvc;
//
//	/** Setup the web context with mockmvc object */
//	protected void setUp() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
//	}
//
//	/** Method to map object to json file **/
//	protected String mapToJson(Object obj) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.writeValueAsString(obj);
//	}
//	/** Method to map json file to object **/
//	protected <T> T mapFromJson(String json, Class<T> clazz)
//			throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.readValue(json, clazz);
//	}
//}
