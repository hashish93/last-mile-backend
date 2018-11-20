package com.appzoneltd.lastmile.microservices.SizesConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.config.TokenServiceConfiguration;




@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class })
@RibbonClient(name="SizesConfigurationApplication",configuration = RibbonConfiguration.class) 
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
public class SizesConfigurationApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(SizesConfigurationApplication.class, args);
	}
	

}
