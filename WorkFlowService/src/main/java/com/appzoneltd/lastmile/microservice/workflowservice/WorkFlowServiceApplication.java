package com.appzoneltd.lastmile.microservice.workflowservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@EnableKafka
@EnableDiscoveryClient
public class WorkFlowServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkFlowServiceApplication.class, args);
	}

}
