package com.appzoneltd.lastmile.microservices.stats;

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

import com.appzoneltd.lastmile.microservices.stats.config.OAuth2ResourceServerConfig;
//import com.appzoneltd.lastmile.microservices.stats.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservices.stats.config.TokenServiceConfiguration;


@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({ OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@EnableJSONDoc
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@EnableKafka
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}
}
