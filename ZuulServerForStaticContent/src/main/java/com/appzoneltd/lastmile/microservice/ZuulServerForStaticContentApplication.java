package com.appzoneltd.lastmile.microservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.appzoneltd.lastmile.filter.DownloadServerDynamicRoutingFilter;
import com.appzoneltd.lastmile.filter.HostAndPortSetterFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerForStaticContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerForStaticContentApplication.class, args);
		
	}

	@Bean
	public HostAndPortSetterFilter hostAndPortSetterFilter() {
		return new HostAndPortSetterFilter();
	}
	
	@Bean
	public DownloadServerDynamicRoutingFilter downloadServerDynamicRoutingFilter() {
		return new DownloadServerDynamicRoutingFilter();
	}

	@Bean
	public CorsFilter corsFilter() {

		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedMethod("HEAD");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("DELETE");
		corsConfig.addAllowedMethod("PATCH");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(urlBasedCorsConfigurationSource);

	}
}
