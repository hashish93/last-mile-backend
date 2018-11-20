package com.appzoneltd.lastmile.microservice.vehicleregistration;

import com.appzoneltd.lastmile.microservice.vehicleregistration.controller.UpdateLocationWebSocketController;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class})
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "VehicleRegistrationApplication", configuration = RibbonConfiguration.class)
@EnableWebSocket
@EnableAsync
public class VehicleRegistrationApplication implements WebSocketConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRegistrationApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandlerDecorator(locationWebSocketHandler()), "/updatelocation")
                .setAllowedOrigins("*")
//				.addInterceptors(new HttpSessionHandshakeInterceptor())
                .setHandshakeHandler(new DefaultHandshakeHandler(new JettyRequestUpgradeStrategy(
                        new WebSocketPolicy(WebSocketBehavior.SERVER))));
    }

//	public WebSocketServerFactory webSocketServerFactory() {
//		WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
//		policy.setIdleTimeout(Long.MAX_VALUE);
//		// policy.setInputBufferSize(1000);
//		// policy.setMaxTextMessageBufferSize(64000);
//		// policy.setMaxTextMessageSize(100000);
//		return new WebSocketServerFactory(policy);
//	}

    @Bean
    public UpdateLocationWebSocketController locationWebSocketHandler() {
        return new UpdateLocationWebSocketController();
    }
}
