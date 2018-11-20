package com.appzoneltd.lastmile.microservice.opensocket;

import com.appzoneltd.lastmile.microservice.opensocket.deployment.ConsumeTopic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * @author alaa.nabil
 *
 */
public class OpenSocketMain extends AbstractVerticle {
	public static final Logger LOGGER = LoggerFactory.getLogger(OpenSocketMain.class);

	@Override
	public void start() throws Exception {
		vertx.deployVerticle(new ConsumeTopic(), res -> {
			if (res.succeeded())
				LOGGER.info("SUCCESSFULLY DEPLOYED");
		});
	}

}
