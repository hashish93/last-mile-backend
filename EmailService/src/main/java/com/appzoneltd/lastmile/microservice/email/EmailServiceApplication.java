package com.appzoneltd.lastmile.microservice.email;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.appzoneltd.lastmile.microservice.email.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.email.service.basic.BasicEmailSenderService;
import com.appzoneltd.lastmile.microservice.email.service.impl.EmailSenderServiceImpl;
import com.appzoneltd.lastmile.microservice.email.service.sender.util.factory.SenderUtilFactory;
import com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.rest.MailGunRESTBasedSenderUtil;

@SpringBootApplication
@PropertySources({@PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "EmailServiceApplication", configuration = RibbonConfiguration.class)
@EnableKafka
@EnableDiscoveryClient
public class EmailServiceApplication {

	@Value("${kafka.bootstrap.servers}")
	private String BOOTSTRAP_SERVERS;
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Long, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Long, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
		factory.getContainerProperties().setPollTimeout(Long.MAX_VALUE);
		return factory;
	}

	@Bean
	public ConsumerFactory<Long, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "EMAIL_GROUP");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}

	// KAFKA Producer Configurations

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> kafkaPros = new HashMap<>();
		kafkaPros.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		kafkaPros.put(ProducerConfig.ACKS_CONFIG, "all");
		kafkaPros.put(ProducerConfig.RETRIES_CONFIG, 0);
		kafkaPros.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		kafkaPros.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return kafkaPros;
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<String, Object>(producerFactory());
	}
	
	@Bean
	public SenderUtilFactory senderUtilFactory() {
		return new 
				SenderUtilFactory("com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.rest.SendGridSenderUtil");
	}
	
	@Bean
	public BasicEmailSenderService basicEmailSenderInterface() {
		return new EmailSenderServiceImpl(senderUtilFactory(),5);
	}
}
