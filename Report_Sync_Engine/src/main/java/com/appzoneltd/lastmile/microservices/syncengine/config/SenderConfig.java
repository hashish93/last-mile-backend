package com.appzoneltd.lastmile.microservices.syncengine.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class SenderConfig {

	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Map producerConfigs() {
		Map props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		return props;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ProducerFactory producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public KafkaTemplate kafkaTemplate() {
		return new KafkaTemplate(producerFactory());
	}

}