package com.appzoneltd.lastmile.microservice.staticcontentinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.appzoneltd.lastmile.microservice.staticcontentinfo.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.service.StaticContentInfoService;


@SpringBootApplication
@EnableDiscoveryClient
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="StaticContentInfoApplication",configuration = RibbonConfiguration.class)
public class StaticContentInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticContentInfoApplication.class, args);
	}
	
	
	@Bean(name = "StaticContentInfoService")
	public StaticContentInfoService staticContentInfoService() {
		return new StaticContentInfoService();
	}
}
