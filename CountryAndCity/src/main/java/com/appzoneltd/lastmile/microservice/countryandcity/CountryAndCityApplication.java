package com.appzoneltd.lastmile.microservice.countryandcity;

import com.appzoneltd.lastmile.microservice.countryandcity.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@EnableDiscoveryClient
//@EnableResourceServer
//@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "CountryAndCityApplication", configuration = RibbonConfiguration.class)
//@EnableJSONDoc
public class CountryAndCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryAndCityApplication.class, args);
    }
}
