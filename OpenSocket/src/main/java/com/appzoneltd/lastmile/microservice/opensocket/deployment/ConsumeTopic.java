package com.appzoneltd.lastmile.microservice.opensocket.deployment;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.vertx.core.json.JsonObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.appzoneltd.lastmile.microservice.opensocket.Configurations;
import com.appzoneltd.lastmile.microservice.opensocket.entity.CustomerQuery;
import com.appzoneltd.lastmile.microservice.opensocket.entity.QueryDTO;
import com.appzoneltd.lastmile.microservice.opensocket.websocket.CustomerSearchTopicSocket;
import com.appzoneltd.lastmile.microservice.opensocket.websocket.SearchTopicSocket;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * @author alaa.nabil
 */
public class ConsumeTopic extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeTopic.class);
    private static final Properties PROPERTIES = Configurations.getKafkaProperties();
    private KafkaConsumer<String, String> consumer;
    private ExecutorService executorService;
    private final ObjectMapper mapper;

    public ConsumeTopic() {
        this.consumer = new KafkaConsumer<String, String>(PROPERTIES);
        this.consumer.subscribe(Arrays.asList(PROPERTIES.getProperty("topic")));
        this.executorService = Executors.newSingleThreadExecutor();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void start() throws Exception {
        executorService.execute(new Runnable() {

            private ConsumerRecords<String, String> records;

            public void run() {
                while (true) {
                    try {
                        records = consumer.poll(Long.MAX_VALUE);
                        for (ConsumerRecord<String, String> consumerRecord : records) {
                            if (!new JsonObject(consumerRecord.value()).getInteger("serverId").equals(Configurations.getServerId()))
                                continue;

                            if (consumerRecord.key() != null
                                    && "CUSTOMER_SEARCH_TOPIC".equalsIgnoreCase(consumerRecord.key())) {

                                vertx.deployVerticle(new CustomerSearchTopicSocket(
                                        mapper.readValue(consumerRecord.value(), CustomerQuery.class)), res -> {
                                    if (res.succeeded())
                                        LOGGER.info("CUSTOMER SEARCH TOPIC DEPLOYED SUCCESSFULLY");
                                });

                            } else {
                                vertx.deployVerticle(
                                        new SearchTopicSocket(mapper.readValue(consumerRecord.value(), QueryDTO.class)),
                                        res -> {
                                            if (res.succeeded())
                                                LOGGER.info("SEARCH TOPIC DEPLOYED SUCCESSFULLY");
                                        });
                            }

                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        });

        if (Configurations.isDevelopment()) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    consumer.close();
                    executorService.shutdown();
                }
            });
        }

    }

}
