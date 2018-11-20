package com.appzoneltd.lastmile.microservices.syncengine.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class ReceiverConfig {

	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public Map consumerConfigs() {
		Map props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "sync-engine-kafka");
		return props;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ConsumerFactory consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setPollTimeout(Long.MAX_VALUE);
		return factory;
	}

}
