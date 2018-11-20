package com.appzoneltd.lastmile.microservice.pickuptime;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.appzoneltd.lastmile.microservice.pickuptime.config.RibbonConfiguration;


/**
 * @author alaa.nabil
 *
 */
@SpringBootApplication
@EnableJSONDoc
@EnableDiscoveryClient
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="PickupTimeApplication",configuration = RibbonConfiguration.class)
public class PickupTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickupTimeApplication.class, args);
	}
}
