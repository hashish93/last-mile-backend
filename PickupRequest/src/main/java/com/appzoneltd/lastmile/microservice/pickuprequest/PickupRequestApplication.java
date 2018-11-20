package com.appzoneltd.lastmile.microservice.pickuprequest;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.pickuprequest.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.pickuprequest.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.pickuprequest.config.TokenServiceConfiguration;
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

/**
 * @author alaa.nabil
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class})
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "PickupRequestApplication", configuration = RibbonConfiguration.class)
@EnableJSONDoc
@EnableKafka
//@EnableCassandraRepositories({"com.appzone.lastmile.repository"})
public class PickupRequestApplication extends CoreApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(PickupRequestApplication.class, args);
    }
}
