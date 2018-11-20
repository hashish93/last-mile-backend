package com.appzoneltd.lastmile.microservice.ondemandworkflow;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.config.TokenServiceConfiguration;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.INearByServiceDetectorManager;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.NearByServiceDetectorManager;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.NearByVehicleDetectorService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({ OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class })
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@EnableKafka
public class OnDemandWorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnDemandWorkflowApplication.class, args);
	}
	

    @Bean
    public Scheduler quartzFactory() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }
    
    @Bean 
    public List<NearByVehicleDetectorService> nearbyDetectorsList() {
    	
    	return new ArrayList<NearByVehicleDetectorService>();
    }
    
    @Bean 
    public INearByServiceDetectorManager nearByServiceDetectorManager() {
    	return new NearByServiceDetectorManager( );
    }

}
