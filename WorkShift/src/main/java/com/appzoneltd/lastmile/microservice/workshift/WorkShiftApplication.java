package com.appzoneltd.lastmile.microservice.workshift;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.workshift.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.workshift.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.workshift.config.TokenServiceConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({ OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class })
@EnableJSONDoc
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="WorkShiftApplication",configuration = RibbonConfiguration.class)

public class WorkShiftApplication extends CoreApplicationConfig {

	public static void main(String[] args) {
		SpringApplication.run(WorkShiftApplication.class, args);
	}

	// @Bean
	// public JettyServerCustomizer jettyServerCustomizer() {
	// return new JettyServerCustomizer() {
	// @Override
	// public void customize(Server server) {
	// String maxThreads="200";
	// String minThreads="10";
	// String idleTimeout="60000";
	// final QueuedThreadPool threadPool =
	// server.getBean(QueuedThreadPool.class);
	// threadPool.setMaxThreads(Integer.valueOf(maxThreads));
	// threadPool.setMinThreads(Integer.valueOf(minThreads));
	// threadPool.setIdleTimeout(Integer.valueOf(idleTimeout));
	// }
	// };
	// }
	//
	// public void customizeJetty(
	// JettyEmbeddedServletContainerFactory containerFactory) {
	// containerFactory.addServerCustomizers(jettyServerCustomizer());
	// containerFactory.setPort(8899);
	// }
	//
	// @Override
	// public void customize(ConfigurableEmbeddedServletContainer container) {
	// if (container instanceof JettyEmbeddedServletContainerFactory) {
	// customizeJetty((JettyEmbeddedServletContainerFactory) container);
	// }
	// }
	//
}
