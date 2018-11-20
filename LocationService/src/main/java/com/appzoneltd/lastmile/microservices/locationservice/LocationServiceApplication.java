package com.appzoneltd.lastmile.microservices.locationservice;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LocationServiceApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(LocationServiceApplication.class, args);
	}
}
