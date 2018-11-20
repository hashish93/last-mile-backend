//package com.appzoneltd.lastmile.microservice.opensocket;
//
//import com.appzoneltd.lastmile.microservice.opensocket.deployment.ConsumeTopic;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.Vertx;
//import io.vertx.core.logging.Logger;
//import io.vertx.core.logging.LoggerFactory;
//
//public class main extends AbstractVerticle {
//	public static final Logger LOGGER = LoggerFactory.getLogger(main.class);
//
//	public static void main(String[] args) {
//		Vertx vertx = Vertx.vertx();
//		vertx.deployVerticle(new ConsumeTopic(), res -> {
//			if (res.succeeded())
//				LOGGER.info("SUCCESSFULLY DEPLOYED");
//		});
//
//	}
//
//}
