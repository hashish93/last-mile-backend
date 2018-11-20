package com.appzoneltd.lastmile.microservice.vehicletype;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.vehicletype.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.vehicletype.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.vehicletype.config.TokenServiceConfiguration;


/**
 * @author alaa.nabil
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="VehicleTypeApplication",configuration = RibbonConfiguration.class)
@EnableJSONDoc
public class VehicleTypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleTypeApplication.class, args);
	}
}
