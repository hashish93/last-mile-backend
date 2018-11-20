package com.appzoneltd.lastmile.microservice.distributionplan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.distributionplan.config.RibbonConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@PropertySources({ @PropertySource("classpath:application.yml"),
		@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name = "DistributionPlanApplication", configuration = RibbonConfiguration.class)
public class DistributionPlanApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(DistributionPlanApplication.class, args);
	}
}
