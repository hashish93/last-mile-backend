package com.appzoneltd.lastmile.microservice.downloadstaticcontent;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.appzoneltd.lastmile.microservice.downloadstaticcontent.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.service.DeleteStaticContentService;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJSONDoc
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="DownloadStaticContentApplication",configuration = RibbonConfiguration.class)
public class DownloadStaticContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloadStaticContentApplication.class, args);
	}

	@Bean(name="DeleteStaticContentService")
	public DeleteStaticContentService deleteStaticContentService() {
		return new DeleteStaticContentService();
	}

}
