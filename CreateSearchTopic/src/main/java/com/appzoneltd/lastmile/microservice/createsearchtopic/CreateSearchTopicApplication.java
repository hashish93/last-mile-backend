package com.appzoneltd.lastmile.microservice.createsearchtopic;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.appzoneltd.lastmile.microservice.createsearchtopic.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.createsearchtopic.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.createsearchtopic.config.TokenServiceConfiguration;


/**
 * @author alaa.nabil
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class,TokenServiceConfiguration.class})
@EnableJSONDoc
@EnableCouchbaseRepositories
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@RibbonClient(name="CreateSearchTopicApplication",configuration = RibbonConfiguration.class)
public class CreateSearchTopicApplication {

	@Value("${kafka.bootstrap.servers}")
	private String BOOTSTRAP_SERVERS;

	public static void main(String[] args) {
		SpringApplication.run(CreateSearchTopicApplication.class, args);
	}

	@Bean
	public KafkaProducer<String, String> kafkaProducerConfig() {
		Properties kafkaPros = new Properties();
		kafkaPros.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		kafkaPros.put(ProducerConfig.ACKS_CONFIG, "all");
		kafkaPros.put(ProducerConfig.RETRIES_CONFIG, 0);
		kafkaPros.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		kafkaPros.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return new KafkaProducer<>(kafkaPros);
	}
	
}
