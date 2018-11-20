package com.appzoneltd.lastmile.privilege;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.privilege.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.privilege.config.RibbonConfiguration;
import com.appzoneltd.lastmile.privilege.config.TokenServiceConfiguration;





@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@RibbonClient(name="PrivilegeApplication",configuration = RibbonConfiguration.class) 
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
public class PrivilegeApplication extends CoreApplicationConfig{
	
	public static void main(String[] args) {
		SpringApplication.run(PrivilegeApplication.class, args);
	}

	
}
