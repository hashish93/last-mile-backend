package com.appzoneltd.lastmile.microservice;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@PropertySources({ @PropertySource("classpath:application.yml"),
		@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
public class FaqApplication extends CoreApplicationConfig{

	public static void main(String[] args) {
		SpringApplication.run(FaqApplication.class, args);
	}
}
