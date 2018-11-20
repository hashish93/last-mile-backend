package com.appzoneltd.lastmile.microservice.delivery;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.delivery.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.delivery.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.delivery.config.TokenServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class})
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "DevicesApplication", configuration = RibbonConfiguration.class)
@EnableAsync
@EnableKafka
public class DeliveryApplication extends CoreApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

}
