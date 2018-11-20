package com.appzoneltd.lastmile.microservice.building;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.building.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.building.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.building.config.TokenServiceConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({ OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class })
@PropertySources({ @PropertySource("classpath:application.yml"),
		@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name = "BuildingApplication", configuration = RibbonConfiguration.class)
@EnableJSONDoc
@EnableKafka
public class BuildingApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(BuildingApplication.class, args);
	}

}
