package com.appzoneltd.lastmile.microservice;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.appzoneltd.lastmile.microservice.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.filter.FileFilter;
import com.appzoneltd.lastmile.microservice.filter.impl.ResizeImageFilter;
import com.appzoneltd.lastmile.microservice.filter.impl.SaveFileFilter;
import com.appzoneltd.lastmile.microservice.filter.impl.ValidateImageSizeAndFormatFilter;
import com.appzoneltd.lastmile.microservice.initializer.YamlFileApplicationContextInitializer;
import com.appzoneltd.lastmile.microservice.service.DeleteStaticContentService;
import com.appzoneltd.lastmile.microservice.service.FileUploadFilterChainManager;
import com.appzoneltd.lastmile.microservice.service.StaticContentInfoService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJSONDoc
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="StaticContentServerApplication",configuration = RibbonConfiguration.class)
public class StaticContentServerApplication {

	public static void main(String[] args) {

		YamlFileApplicationContextInitializer ini = new YamlFileApplicationContextInitializer();

		new SpringApplicationBuilder(StaticContentServerApplication.class).initializers(ini).run(args);
	}

	@Bean(initMethod = "initialize")
	public FileUploadFilterChainManager fileUploadFilterChainManager() {
		return new FileUploadFilterChainManager();
	}

	@Bean(name = "ValidateImageSizeAndFormatFilter")
	public FileFilter validateImageSizeAndFormatFilter() {
		return new ValidateImageSizeAndFormatFilter();
	}

	@Bean(name = "SaveFileFilter")
	public FileFilter saveFileFilter() {
		return new SaveFileFilter();
	}

	@Bean(name = "ResizeImageFilter")
	public FileFilter resizeImageFilter() {
		return new ResizeImageFilter();
	}
	
	@Bean(name = "StaticContentInfoService")
	public StaticContentInfoService staticContentInfoService() {
		return new StaticContentInfoService();
	}
	
	@Bean(name="DeleteStaticContentService")
	public DeleteStaticContentService deleteStaticContentService() {
		return new DeleteStaticContentService();
	}
	
}
