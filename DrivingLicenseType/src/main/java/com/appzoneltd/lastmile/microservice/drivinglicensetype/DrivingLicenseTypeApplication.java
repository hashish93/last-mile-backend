package com.appzoneltd.lastmile.microservice.drivinglicensetype;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import com.appzoneltd.lastmile.microservice.drivinglicensetype.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.drivinglicensetype.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.drivinglicensetype.config.TokenServiceConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@RibbonClient(name="DrivingLicenseTypeApplication",configuration = RibbonConfiguration.class) 
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@EnableJSONDoc
public class DrivingLicenseTypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivingLicenseTypeApplication.class, args);
	}
}
