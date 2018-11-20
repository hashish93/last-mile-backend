package com.appzoneltd.lastmile.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.appzoneltd.lastmile.authorizationserver.config.AuthorizationServerConfiguration;
import com.appzoneltd.lastmile.authorizationserver.config.RibbonConfiguration;
import com.appzoneltd.lastmile.authorizationserver.config.SecurityConfig;



@SpringBootApplication
@EnableDiscoveryClient
@Import({AuthorizationServerConfiguration.class,SecurityConfig.class})
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="AuthorizationServerApplication",configuration = RibbonConfiguration.class) 
public class AuthorizationServerApplication {

	@Bean
	public TokenStore tokenStore() {
			return new InMemoryTokenStore();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}
