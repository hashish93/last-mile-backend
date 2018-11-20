package com.appzoneltd.lastmile.microservice.returnworkflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;

@SpringBootApplication
@PropertySources({ @PropertySource("classpath:application.yml"),
		@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@EnableKafka
@EnableDiscoveryClient
@EnableResourceServer
public class ReturnWorkflowApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(ReturnWorkflowApplication.class, args);
	}

	
}
