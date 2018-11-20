package com.appzoneltd.lastmile.microservice.forgetpassword;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;

import com.appzoneltd.lastmile.microservice.forgetpassword.config.RibbonConfiguration;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJSONDoc
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="ForgetpasswordApplication",configuration = RibbonConfiguration.class)
@EnableKafka
public class ForgetpasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgetpasswordApplication.class, args);
	}
}
