package com.appzoneltd.lastmile.microservice.packge;

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
import com.appzoneltd.lastmile.microservice.packge.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.packge.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.packge.config.TokenServiceConfiguration;

/**
 * @author Alaa Nabil
 * 
 *         Main Spring Boot Configrations that extends Another Core project
 *         configs to autowire beans
 * 
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({ OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class })
@PropertySources({ @PropertySource("classpath:application.yml"),
		@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name = "PackageApplication", configuration = RibbonConfiguration.class)
@EnableJSONDoc
@EnableKafka
public class PackageApplication extends CoreApplicationConfig {
	public static void main(String[] args) {
		SpringApplication.run(PackageApplication.class, args);
	}
}
