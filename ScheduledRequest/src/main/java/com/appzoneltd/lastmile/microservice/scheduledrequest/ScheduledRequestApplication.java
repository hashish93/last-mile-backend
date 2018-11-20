package com.appzoneltd.lastmile.microservice.scheduledrequest;





import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.core.CoreApplicationConfig;

/**
 * @author alaa.nabil
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@EnableJSONDoc
public class ScheduledRequestApplication extends CoreApplicationConfig{

	public static void main(String[] args)  {
		SpringApplication.run(ScheduledRequestApplication.class, args);
		
		
	}
}
